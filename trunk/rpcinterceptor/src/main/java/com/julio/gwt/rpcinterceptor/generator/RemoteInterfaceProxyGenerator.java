package com.julio.gwt.rpcinterceptor.generator;


import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.GeneratorContextExt;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.dev.javac.rebind.RebindResult;
import com.google.gwt.user.rebind.rpc.ProxyCreator;

public class RemoteInterfaceProxyGenerator extends com.google.gwt.user.rebind.rpc.ServiceInterfaceProxyGenerator {

	/** The interceptor property name. */
	static String INTERCEPTOR_PROPERTY_NAME = "com.julio.interceptor.class";

	/** Cached context to be accessible across the methods. */
	protected GeneratorContext generatorContext;

	/**
	 * Instantiates a new my interface proxy generator.
	 */
	public RemoteInterfaceProxyGenerator() {
		super();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public RebindResult generateIncrementally(TreeLogger logger,
			GeneratorContextExt ctx, String requestedClass)
			throws UnableToCompleteException {

		cacheContext(ctx);

		TreeLogger loggerBranched = logger.branch(TreeLogger.DEBUG, "[*" + this.getClass().getSimpleName() + "*] Incremental Generator: Creating custom proxy for " + requestedClass, null);
		return super.generateIncrementally(loggerBranched, ctx, requestedClass);
	}

	/**
	 * Cache context.
	 *
	 * @param ctx the context
	 */
	private void cacheContext(GeneratorContext ctx) {
		if (this.generatorContext == null) {
			this.generatorContext = ctx;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProxyCreator createProxyCreator(JClassType remoteService) {
		return new RemoteInterfaceProxyCreator(remoteService, this.generatorContext);
	}

}
