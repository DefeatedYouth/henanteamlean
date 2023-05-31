package com.team.common.request;

import java.io.Serializable;

/**
 * <br>

 * @author qt
 */
public abstract class BaseRequest implements Serializable {
    private static final long serialVersionUID = 2813099853581668668L;

    /**
     * 入参校验
     * @return
     *      true:通过；
     *      false:不通过
     */
    public abstract boolean verifyParam();

}
