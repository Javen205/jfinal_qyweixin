package com.jfinal.qyweixin.sdk.utils;

/**
 * 支付异常
 * @author L.cm
 *
 */
public class PaymentException extends Exception {
	private static final long serialVersionUID = 6615472990468215919L;

	public PaymentException() {
		super();
	}

	public PaymentException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentException(String message) {
		super(message);
	}

	public PaymentException(Throwable cause) {
		super(cause);
	}
	
}
