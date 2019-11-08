package com.glacier.core.exception;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-14 15:48
 */
public class EbootException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public EbootException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public EbootException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public EbootException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public EbootException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
