package xyz.zzzxb.client.pojo;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * zzzxb
 * 2023/10/31
 */
@Data
public class Message {
    private AccountInfo accountInfo;
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private List<String> phones;
    private String content;

    public Message setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
        return this;
    }

    public Message setPhone(String phone) {
        this.phones = Collections.singletonList(phone);
        return this;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }
}
