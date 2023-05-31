package com.team.common.constants.result;

public abstract class BaseController {

    public BaseController() {
    }

    public static <T> BaseResult<T> success(T data) {
        return BaseResult.success(data);
    }

    public static <T> BaseResult<T> fail() {
        return BaseResult.fail();
    }

    public static <T> BaseResult<T> fail(int code, String message) {
        return BaseResult.fail(code, message);
    }

}
