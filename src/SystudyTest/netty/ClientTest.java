package SystudyTest.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;

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
                        pipeline.addLast(new CustomChannelOutBoundHandler());
                    }
                });

        // 启动客户端连接操作
        ChannelFuture f = bootstrap.connect(host, port).sync();
        System.out.println("客户端连接成功，连接地址：" + f.channel().remoteAddress());
        // 发送10个数据
        int i = 0;
        while (i < 5) {
            i++;
            f.channel().writeAndFlush(Long.valueOf(10000L));
        }
    }

    public static void main(String[] args) {
        ClientTest client = new ClientTest("127.0.0.1", 8080);
        try {
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CustomChannelOutBoundHandler extends MessageToByteEncoder<Long> {

    /**
     *
     * @param ctx 上下文对象
     * @param msg 等待编码的数据
     * @param out 编码后的数据为二进制字节流格式，都存储到out中交给Netty，netty负责把ByteBuf数据交给socket缓冲区
     *            socket缓冲区会把数据交给网络IO通信发给服务端
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("编码器发送数据");
        out.writeLong(msg);
    }
}