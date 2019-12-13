package com.glacier.core.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * HTTP结果封装
 *
 * @author glacier
 * @date 2019-10-14 15:53
 */
@Getter
@Setter
@ToString
public class HttpResult {

    private int code = 200;
    private String msg;
    private Object data;

    public static HttpResult error(String msg) {
        return error(500, msg);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok() {
        return new HttpResult();
    }

    public static HttpResult ok(Object data) {
        return ok(null, data);
    }

    public static HttpResult ok(String msg, Object data) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
