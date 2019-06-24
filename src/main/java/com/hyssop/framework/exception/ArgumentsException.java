/*
 * $Id: ArgumentsException.java 10467 2012-03-09 11:44:22Z li.sun $
 * Copyright (c) 2011 Qunar.com. All Rights Reserved.
 */

package com.hyssop.framework.exception;


/**
 * 参数异常类 在客户端输入的任何参数问题，导致无法继续使，可以直接抛出ArgumentsException 的runtime异常
 *
 * @author sunli
 */
public class ArgumentsException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -2055904654764512098L;

    @SuppressWarnings("unused")
    private ArgumentsException() {

    }

    public ArgumentsException(String message) {
        super(message);
    }

    /**
     * 重写fillInStackTrace，在Service抛出ArgumentsException时，不会传递堆栈信息 目的是提高性能和减小传输
     * 另外，从需求上讲，也并不需要堆栈信息，因为堆栈在这里来讲，都是确定的。
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
