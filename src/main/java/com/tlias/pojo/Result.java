package com.tlias.pojo;


public class Result {
    private Integer code;//响应码，1代表成功，0代表失败
    private String message;//响应信息，不管成功或失败，都展示一些信息
    private Object data;// 具体返回的数据

    //无参构造
    public Result() {
    }

    //有参构造
    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 获取
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "Result{code = " + code + ", message = " + message + ", data = " + data + "}";
    }

    //操作成功但不需要返回数据
    public static Result success(){
        return new Result(200, "success", null);
    }

    //操作成功但需要返回数据
    public static Result success(Object data){
        return new Result(200, "success", data);
    }

    //操作失败，不返回数据，但是展示错误的信息
    public static Result error(String message){
        return new Result(500, message, null);
    }
}
