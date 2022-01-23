package com.iloveleiyuxin.websitmanager.common.exception;

/**
 * <ul>
 * Each project created by me include this exception class,
 * even though it has never been thrown or caught.
 *
 * Because LeiYuXin, my girlfriend, fell in love with others is an exception, otherwise it is a SERIOUS exception.
 * </ul>
 * Thrown when LeiYuXin fallen in love with everyone except me
 * because I can hardly live without LeiYuXin.(Without LeiYuXin, I could not develop anything in Java)
 *
 * LeiYuXin fallen in love includes these situations:
 * <ul>
 * <li>Take photos with others(excludes besties)
 * <li>Post Wechat moments or Qzone in some special ways
 * <li>Go to nightclubs
 * <li>My message with no reply more than 10 messages or longer than 2 hrs
 * <li>Can not contact on LeiYuXin except in her busy time
 * </ul>
 *
 * {@code LeiYuXinFallenInLoveException} objects may be constructed by the
 * virtual machine as if {@linkplain Throwable#Throwable(String,
 * Throwable, boolean, boolean) suppression were disabled and/or the
 * stack trace was not writable}.
 *
 * @author  LeiYuXin's Boyfriend
 * @since   JDK1.0
 */
public class LeiYuXinFallenInLoveException extends RuntimeException {
    private static final long serialVersionUID = 200101015201314L;

    /**
     * Constructs a {@code LeiYuXinFallenInLoveException} with no detail message.
     */
    public LeiYuXinFallenInLoveException() {
        super();
    }

    /**
     * Constructs a {@code LeiYuXinFallenInLoveException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public LeiYuXinFallenInLoveException(String s) {
        super(s);
    }
}