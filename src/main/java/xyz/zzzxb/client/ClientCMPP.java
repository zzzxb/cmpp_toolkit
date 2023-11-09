package xyz.zzzxb.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import xyz.zzzxb.client.handler.ChannelCMPP;
import xyz.zzzxb.client.pojo.AccountInfo;
import xyz.zzzxb.client.pojo.Message;


/**
 * zzzxb
 * 2023/10/13
 */
@Slf4j
public class ClientCMPP {
    private final static Message message = new Message();

    public static void main(String[] args) {
        message.setAccountInfo(new AccountInfo()
                .address("10.10.183.196", 27001)
                .account("umccms", "umcc2022"))
                .setPhone("15136858562")
                .setContent("【联动优势】短信内容");

        new ClientCMPP().start();
    }

    public void start() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);
//        b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 6);
        b.handler(new ChannelCMPP(message));

        try {
            ChannelFuture f = b.connect(message.getAccountInfo().getHost(), message.getAccountInfo().getPort()).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("线程异常中断", e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
