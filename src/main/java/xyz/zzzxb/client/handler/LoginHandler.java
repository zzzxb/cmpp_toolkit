package xyz.zzzxb.client.handler;

import cn.tofucat.toolkit.util.CipherUtils;
import cn.tofucat.toolkit.util.DateUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.zzzxb.client.enums.CommandId;
import xyz.zzzxb.client.enums.LoginStatus;
import xyz.zzzxb.client.pojo.Message;
import xyz.zzzxb.client.util.TCPUtils;

import java.nio.charset.StandardCharsets;

/**
 * zzzxb
 * 2023/10/16
 */
@Slf4j
@AllArgsConstructor
public class LoginHandler extends ChannelInboundHandlerAdapter {
    private final Message message;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String spId = message.getAccountInfo().getSpId();
        String sk = message.getAccountInfo().getSk();
        String timestamp = DateUtils.currentDate("MMddHHmmss");
        String auth = spId + "\0\0\0\0\0\0\0\0\0" + sk + timestamp;

        ByteBuf buf = Unpooled.buffer();
        // 请求头 4 字节 - 消息总长度
        buf.writeInt(39);
        // 请求头 4 字节 - 命令或响应类型
        buf.writeInt(CommandId.CMPP_CONNECT.getCode());
        // 请求头 4 字节 - 消息流水号
        buf.writeZero(4);
        // 消息体 6 字节 - SP_ID
        TCPUtils.fixedLength(buf, spId, 6);
        // 消息体 16 字节 - 鉴权码
        buf.writeBytes(CipherUtils.buildDigest(auth.getBytes(StandardCharsets.UTF_8), CipherUtils.Encrypt.MD5));
        // 消息体 1 字节 - 版本号
        buf.writeByte(0x20);
        // 消息体 4 字节 - 时间戳
        buf.writeInt(Integer.parseInt(timestamp));

        ctx.writeAndFlush(buf);
        log.info("log req -> spId: [{}] sk: [{}] collection [{}:{}]", spId, sk,
                message.getAccountInfo().getHost(), message.getAccountInfo().getPort());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;

        if (!message.getAtomicBoolean().get()) {
            // 请求头 12 字节 - 丢弃不检查了
            buf.skipBytes(12);
            // 消息体 4 字节 - 登录状态
            byte status = buf.readByte();
            message.getAtomicBoolean().compareAndSet(false, status == LoginStatus.SUCCESS.getCode());
            log.info("log resp -> status: {}, desc: {}", status, LoginStatus.getDesc(status));
            ctx.fireChannelActive();
        }

        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("登录异常", cause);
        ctx.close();
    }
}
