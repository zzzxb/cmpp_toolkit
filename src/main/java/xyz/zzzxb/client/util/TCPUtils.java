package xyz.zzzxb.client.util;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * zzzxb
 * 2023/8/30
 */
public class TCPUtils {

    public static void writeString(ByteBuf buf, String content) {
        buf.writeBytes(content.getBytes(Charset.forName("GBK")));
    }

    public static void fixedLength(ByteBuf buf, String content, int length) {
        byte[] bytes = content.getBytes(Charset.forName("GBK"));
        if (bytes.length < length) {
            buf.writeBytes(bytes);
            buf.writeZero(length - bytes.length);
        }else if (bytes.length > length) {
            buf.writeBytes(bytes, 0, length);
        }else {
            buf.writeBytes(bytes);
        }
    }
}