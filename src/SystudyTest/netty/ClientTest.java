package SystudyTest.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

public class ClientTest {

    private final String host;
    private final int port;

    public ClientTest(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true) // 设置保持长连接
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 添加字符串编码器
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        // 添加心跳检测
                        pipeline.addLast(new IdleStateHandler(10, 10, 0));
                        // 添加客户端自定义的空闲状态处理器
                        pipeline.addLast(new ClientIdleEventHandler());
                    }
                });

        // 启动客户端连接操作
        ChannelFuture f = bootstrap.connect(host, port).sync();
        System.out.println("客户端连接成功，连接地址：" + f.channel().remoteAddress());

        // 发送消息
        f.channel().writeAndFlush("Hello Server!");

        // 等待直到客户端关闭
        f.channel().closeFuture().await();
    }
}

/**
 * 客户端空闲状态处理器
 */
class ClientIdleEventHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.WRITER_IDLE) {
                System.out.println("发送心跳...");
                // 如果规定的时间间隔内没有写入任何数据，就发送心跳
                ctx.writeAndFlush("心跳消息");
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    public static void main(String[] args) {
        try {
            new ClientTest("127.0.0.1", 8080).run();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}