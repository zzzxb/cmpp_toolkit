package xyz.zzzxb.client.enums;

import lombok.Getter;

/**
 * zzzxb
 * 2023/8/29
 */
@Getter
public enum LoginStatus {
    SUCCESS(0, "登录成功"),
    MESSAGE_STRUCT_FAIL(1, "消息结构错"),
    IP_SOURCE_FAIL(2, "非法源原地址"),
    AUTHORIZATION(3, "认证错误"),
    VERSION_FAIL(4, "版本太高"),
    OTHER_FAIL(5, "其他错误"),
    ;

    private final Integer code;
    private final String desc;

    LoginStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(int code) {
        switch (code) {
            case 0:
                return SUCCESS.desc;
            case 1:
                return MESSAGE_STRUCT_FAIL.desc;
            case 2:
                return IP_SOURCE_FAIL.desc;
            case 3:
                return AUTHORIZATION.desc;
            case 4:
                return VERSION_FAIL.desc;
            default:
                return OTHER_FAIL.desc;
        }
    }
}
