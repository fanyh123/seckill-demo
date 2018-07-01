package com.yangyang.seckill.result;

public class Result<T> {
    private int code;

    private String msg;

    private T data;

    private Result(CodeMsg codeMsg) {

        if (codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }


    /**
     * 成功时候返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {

        return new Result<T>(data);
    }

    /**
     * 失败时候返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {

        return new Result<T>(codeMsg);
    }

    private Result(T data) {

        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public T getData() {
        return data;
    }

}
