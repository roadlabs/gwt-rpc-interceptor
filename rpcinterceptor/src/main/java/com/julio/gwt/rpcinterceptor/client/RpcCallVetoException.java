package com.julio.gwt.rpcinterceptor.client;

/**
 * The Class RpcCallVetoException.
 */
public class RpcCallVetoException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7710232804793357516L;

	/**
	 * Instantiates a new rpc call veto exception.
	 */
	public RpcCallVetoException() {
	}

	/**
	 * Instantiates a new rpc call veto exception.
	 *
	 * @param reaso the reaso
	 */
	public RpcCallVetoException(String reaso) {
		super(reaso);
	}

	/**
	 * Instantiates a new rpc call veto exception.
	 *
	 * @param cause the cause
	 */
	public RpcCallVetoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new rpc call veto exception.
	 *
	 * @param reason the reason
	 * @param cause the cause
	 */
	public RpcCallVetoException(String reason, Throwable cause) {
		super(reason, cause);
	}

}
