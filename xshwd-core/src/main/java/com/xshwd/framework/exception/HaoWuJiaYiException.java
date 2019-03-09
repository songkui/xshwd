package com.xshwd.framework.exception;

import com.xshwd.framework.web.ResponseCode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @Auther: SK ON  2018/10/31 14:48
 * @Description:
 */
public class HaoWuJiaYiException extends RuntimeException {
    private static final long serialVersionUID = 8182815460722287938L;
    private ResponseCode responseCode;
    private String message;

    /**
     * 创建好物加一异常
     *
     * @param responseCode 异常状态码
     */
    public HaoWuJiaYiException(final ResponseCode responseCode) {
        super(String.valueOf(responseCode.code()));
        this.responseCode = responseCode;
        this.message = responseCode.desc();
    }

    public HaoWuJiaYiException(final ResponseCode responseCode, Object... objects) {
        super(String.valueOf(responseCode.code()));
        this.responseCode = responseCode;
        message = ArrayUtils.isEmpty(objects) || StringUtils.isBlank(responseCode.template())
                ? responseCode.desc()  : String.format(this.responseCode.template(), objects);
    }

    /**
     * 创建好物加一异常
     *
     * @param msg 异常信息
     */
    public HaoWuJiaYiException(final String msg) {
        super(msg);
    }

    /**
     * 创建好物加一异常
     *
     * @param msg 异常信息
     * @param ex  异常根源
     */
    public HaoWuJiaYiException(final String msg, final Throwable ex) {
        super(msg, ex);
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(message).orElse(super.getMessage());
    }
}
