package com.flash.cn.beans;

/**
 * Bean Create Failure Exception.
 *
 * @author kay
 * @version v1.0
 */
public class BeanCreateFailureException extends BeanException {

    /**
     *
     */
    public BeanCreateFailureException() {
        super();
    }

    /**
     *
     * @param cause
     */
    public BeanCreateFailureException(Throwable cause) {
        super(cause);
    }
}