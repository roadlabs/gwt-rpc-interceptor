package com.googlecode.julio.gwt.rpcinterceptor.generator;

import java.util.List;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;
import com.google.gwt.user.rebind.rpc.ProxyCreator;
import com.googlecode.julio.gwt.rpcinterceptor.client.RpcServiceInterceptor;

/**
 * The Class RemoteInterfaceProxyCreator.
 */
public class RemoteInterfaceProxyCreator extends ProxyCreator {

	/** The generator context. */
	private final GeneratorContext generatorContext;

	/**
	 * Instantiates a new custom rpc proxy creator.
	 *
	 * @param type
	 * @param generatorContext
	 */
	public RemoteInterfaceProxyCreator(JClassType type, GeneratorContext generatorContext) {
		super(type);
		this.generatorContext = generatorContext;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<? extends RemoteServiceProxy> getProxySupertype() {

		Class<? extends RemoteServiceProxy> interceptorClass = RpcServiceInterceptor.class;
		Class<? extends RemoteServiceProxy> configuredInterceptor = getInterceptorClassOrNull();

		if (configuredInterceptor != null) {
			interceptorClass = configuredInterceptor;
		}

		return interceptorClass;
	}

	/**
	 * Gets the interceptor class or null.
	 *
	 * @return the interceptor class or null
	 */
	@SuppressWarnings("unchecked")
	private Class<? extends RemoteServiceProxy> getInterceptorClassOrNull() {

		Class<? extends RemoteServiceProxy> configuredInterceptorClass = null;

		try {

			PropertyOracle configurationProperties = generatorContext.getPropertyOracle();
			List<String> interceptorValue = configurationProperties.getConfigurationProperty(RemoteInterfaceProxyGenerator.INTERCEPTOR_PROPERTY_NAME).getValues();
			String forClass = interceptorValue.get(0);
			configuredInterceptorClass = (Class<? extends RemoteServiceProxy>) Class.forName(forClass);

		} catch (Exception e) {
			// nop
		}

		return configuredInterceptorClass;
	}

}
