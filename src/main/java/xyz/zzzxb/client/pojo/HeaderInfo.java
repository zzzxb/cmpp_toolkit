package xyz.zzzxb.client.pojo;

import lombok.Data;

/**
 * zzzxb
 * 2023/11/8
 */
@Data
public class HeaderInfo {
    /**
     * 消息总长度(含消息头及消息体)
     */
    private Integer totalLength;
    /**
     * 命令或响应类型
     */
    private Integer commandId;
    /**
     * 消息流水号,顺序累加,步长为 1,循环使用 (一对请求和应答消息的流水号必须相 同
     */
    private Integer sequenceId;
}
