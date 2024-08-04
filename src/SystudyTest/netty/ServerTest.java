package SystudyTest.netty;

import static io.netty.channel.ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE;

import java.util.List;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

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
                        pipeline.addLast(new CustomChannelDeocder());
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                if (msg instanceof Long) {
                                    Long data = (Long) msg;
                                    System.out.println(data);
                                }
                            }
                        });
                    }
                })
                // 设置channel的参数
                .childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.TCP_NODELAY, true);
        ChannelFuture bind = serverBootstrap.bind("127.0.0.1", 8080);
        try {
            // 等待服务端监听端口关闭
            System.out.println("netty server start。。"); // 打印启动信息
            bind.sync().addListener(FIRE_EXCEPTION_ON_FAILURE);
        } catch (InterruptedException e) {
            // 开启错误
            e.printStackTrace();
        }
    }

}

class CustomChannelDeocder extends ByteToMessageDecoder {

    /**
     *
     * @param ctx 上下文对象
     * @param in  网络IO传输过来的数据，存储到ByteBuf类型的in中
     * @param out 解码处理完后，把每一个"Message"都存储到out这一集合中，便于后续Netty遍历该out集合进行执行pipeline流水线的Handler操作
     *            Netty是以消息Message为单位进行处理数据的，有多少Message，就执行多少次pipeline
     *            ---->
     *            如果字节数不超过滑动窗口，socket缓冲区，ByteBuf缓冲区，客户端只发一次数据即可，为什么服务端能划分出多个Message呢？
     *            原因很简单：每调用一次decode进行处理ByteBuf，那么就会封装成一个Message。
     *            如果服务端一次没有处理完ByteBuf，那么会进行第二次调用decode方法的处理，则第二次处理ByteBuf，则又会生成一个新的Message
     *            以此类推，直到ByteBuf中的数据都处理完。
     *            调用n次decode方法，处理ByteBuf n次，存储n个Message到out集合中
     *            ---->
     *            啥时候意味着ByteBuf中的数据都处理完？
     *            其实还是指针的移动，思考一下之前NIO的各种指针，当读指针到达limit指针边界后就意味着ByteBuf处理完毕
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("收到自定义消息：");
        long reciveLong = in.readLong();
        out.add(reciveLong);
    }

}