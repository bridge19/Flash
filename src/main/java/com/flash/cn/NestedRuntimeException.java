package com.flash.cn;

/**
 * @author kay
 * @version v1.0
 */
public class NestedRuntimeException extends RuntimeException {

    /**
     *
     */
    public NestedRuntimeException() {
        super();
    }

    /**
     *
     * @param cause
     */
    public NestedRuntimeException(Throwable cause) {
        super(cause);
    }
}