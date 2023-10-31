package xyz.zzzxb.client.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * zzzxb
 * 2023/10/31
 */
@Data
public class AccountInfo {
    private String host;
    private int port;
    private String spId;
    private String sk;

    public AccountInfo address(String host, int port) {
        this.host = host;
        this.port = port;
        return this;
    }

    public AccountInfo account(String spId, String sk) {
        this.spId = spId;
        this.sk = sk;
        return this;
    }
}
