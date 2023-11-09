package xyz.zzzxb.client.util;

import io.netty.buffer.ByteBuf;
import xyz.zzzxb.client.pojo.HeaderInfo;

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

    public static String readString(ByteBuf buf, Integer len) {
        byte[] bytes = new byte[len];
        buf.readBytes(bytes);
        return new String(bytes, Charset.forName("GBK"));
    }

    public static HeaderInfo readHeader(ByteBuf buf) {
        HeaderInfo headerInfo = new HeaderInfo();
        headerInfo.setTotalLength(buf.readInt());
        headerInfo.setCommandId(buf.readInt());
        headerInfo.setSequenceId(buf.readInt());
        return headerInfo;
    }

    public static String formatMsgId( long msgId ) {

        long mm = msgId, dd = msgId, HH = msgId, MM = msgId, SS = msgId, gw = msgId, sq = msgId;

        mm >>>= 60;
        dd = dd & 0x0F80000000000000l;
        dd >>>= 55;
        HH = HH & 0x007C000000000000l;
        HH >>>= 50;
        MM = MM & 0x0003F00000000000l;
        MM >>>= 44;
        SS = SS & 0x00000FC000000000l;
        SS >>>= 38;
        gw = gw & 0x0000003FFFFF0000l;
        gw >>>= 16;
        sq = sq & 0x000000000000FFFFl;

        return String.format( "%1$02d%2$02d%3$02d%4$02d%5$02d%6$07d%7$05d", mm, dd, HH, MM, SS, gw, sq );

    }
}