package com.glacier.common.core.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * HTTP结果封装
 *
 * @author glacier
 * @date 2019-10-14 15:53
 */
@Getter
@Setter
@ToString
public class HttpResult<T> implements Serializable {

    private static final long serialVersionUID = -8984794300938868661L;
    private int code = 200;
    private String msg;
    private T data;

    public static <T> HttpResult<T> error(String msg) {
        return error(500, msg);
    }

    public static <T> HttpResult<T> error(int code, String msg) {
        return error(code, msg, null);
    }

    public static <T> HttpResult<T> error(int code, String msg, T data) {
        HttpResult<T> r = new HttpResult<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> HttpResult<T> ok() {
        return ok(null, null);
    }

    public static <T> HttpResult<T> ok(T data) {
        return ok(null, data);
    }

    public static <T> HttpResult<T> ok(String msg, T data) {
        HttpResult<T> r = new HttpResult<>();
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
