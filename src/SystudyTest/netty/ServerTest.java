package SystudyTest.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import io.netty.channel.ChannelHandler.Sharable;

import static io.netty.channel.ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE;

/**
 * @Date 2024/7/28
 * @Created by ZhuBo
 */
public class ServerTest {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group, work).channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 128).option(ChannelOption.SO_REUSEADDR, true)
            .childHandler(new ChannelInitializer<SocketChannel>() {

                /**
                 * 实现自定义初始化的Channel的逻辑
                 */
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();

                    // 添加字符串解码器和编码器
                    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                    // `readerIdleTimeSeconds` | 读超时。即当在指定的时间间隔内没有从`Channel`读取到数据时，会触发一个`READER_IDLE`的`IdleStateEvent`事件 |
                    // `writerIdleTimeSeconds` | 写超时。即当在指定的时间间隔内没有数据写入到`Channel`时，会触发一个`WRITER_IDLE`的`IdleStateEvent`事件 |
                    // `allIdleTimeSeconds` | 读/写超时。即当在指定的时间间隔内没有读或写操作时，会触发一个`ALL_IDLE`的`IdleStateEvent`事件 |
                    // 心跳检测
                    pipeline.addFirst("idleStateHandler", new IdleStateHandler(0, 10, 0));
                    // 超过心跳的处理
                    pipeline.addAfter("idleStateHandler", "idleEventHandler", new IdleTimeoutHandler());
                }

            })
            // 设置channel的参数
            .childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.TCP_NODELAY, true);
        ChannelFuture bind = serverBootstrap.bind("127.0.0.1", 8080);
        try {
            // 等待服务端监听端口关闭
            bind.sync().addListener(FIRE_EXCEPTION_ON_FAILURE);
        } catch (InterruptedException e) {
            // 开启错误
            e.printStackTrace();
        }
    }

}

/**
 * 心跳检测
 */
@Sharable
class IdleTimeoutHandler extends ChannelDuplexHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接被激活");
        super.channelActive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState e = ((IdleStateEvent)evt).state();
            // IdleState.READER_IDLE：表示在指定的时间间隔内没有读取到任何数据。
            // IdleState.WRITER_IDLE：表示在指定的时间间隔内没有写入任何数据。
            // IdleState.ALL_IDLE：表示在指定的时间间隔内既没有读取到任何数据，也没有写入任何数据。
            if (e == IdleState.READER_IDLE) {
                // 在调用close()时，未调用channelInactive()可能会导致一些问题。首先，如果未调用channelInactive()，
                // 处理器可能无法及时处理Channel变为非活动状态的情况，从而导致资源泄漏或其他不可预料的问题。其次，
                // 未调用channelInactive()可能会导致处理器无法正确地处理连接断开的情况，从而无法及时释放相关资源或执行必要的清理操作。
                // 为了避免这些问题，建议在调用close()之前，先调用channelInactive()方法来通知处理器当前的Channel已经变为非活动状态。
                // 这样可以确保处理器能够及时处理非活动状态，并执行必要的清理操作。
                // 方法会触发ChannelInactive事件，执行下一个的ChannelInboundHandler的channelInactive方法
                ctx.fireChannelInactive();
                ctx.close();
            }
        } else {
            // 调用父类的userEventTriggered方法
            super.userEventTriggered(ctx, evt);
        }
    }
}
