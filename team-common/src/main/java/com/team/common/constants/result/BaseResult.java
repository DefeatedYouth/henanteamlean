package com.team.common.constants.result;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public final class BaseResult<T> implements Serializable{

    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("消息响应码")
    private Integer code;
    @ApiModelProperty("附加信息")
    private String message;
    @ApiModelProperty("数据内容")
    private T data;
    //总页数
    @ApiModelProperty("总页数")
    private Long pages;

    public BaseResult() {
    }

    public BaseResult(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult(true, 1, "", data);
    }

    public static <T> BaseResult<T> fail() {
        return new BaseResult(false, 0, "", null);
    }

    public static <T> BaseResult<T> fail(Integer code, String message) {
        return new BaseResult(false, code, message, null);
    }

    public static <T> BaseResult<T> fail(Integer code, String message, T data) {
        return new BaseResult(false, code, message, data);
    }

    public String toString() {
        return "{success=" + this.success + ", code=" + this.code + ", message='" + this.message + '\'' + ", data=" + this.data + '}';
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }
}
