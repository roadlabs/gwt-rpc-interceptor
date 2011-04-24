package com.googlecode.julio.gwt.rpcinterceptor.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.user.client.rpc.impl.RpcStatsContext;
import com.google.gwt.user.client.rpc.impl.Serializer;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomRpcServiceProxy.
 */
public class RpcServiceInterceptor extends RemoteServiceProxy {


	/**
	 * Instantiates a new rpc service interceptor.
	 *
	 * @param moduleBaseURL the module base url
	 * @param remoteServiceRelativePath the remote service relative path
	 * @param serializationPolicyName the serialization policy name
	 * @param serializer the serializer
	 */
	public RpcServiceInterceptor(String moduleBaseURL, String remoteServiceRelativePath,
			String serializationPolicyName, Serializer serializer) {
		super(moduleBaseURL, remoteServiceRelativePath, serializationPolicyName, serializer);
	}


	/**
	 * Do create request callback.
	 *
	 * @param <T> the generic type
	 * @param responseReader the response reader
	 * @param methodName the method name
	 * @param statsContext the stats context
	 * @param callback the callback
	 * @return the request callback
	 * {@inheritDoc}
	 */
	@Override
	protected <T> RequestCallback doCreateRequestCallback(
			ResponseReader responseReader, String methodName,
			RpcStatsContext statsContext, AsyncCallback<T> callback) {

		final RequestCallback doCreateRequestCallback = super.doCreateRequestCallback(responseReader, methodName, statsContext, callback);
		RequestCallback wrapped = new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (preprocessRawResponse(response)) {
					doCreateRequestCallback.onResponseReceived(request, response);
				}
			}
			@Override
			public void onError(Request request, Throwable exception) {
				doCreateRequestCallback.onError(request, exception);
			}
		};
		return wrapped;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected <T> Request doInvoke(ResponseReader responseReader,
			String methodName, RpcStatsContext statsContext,
			String requestData, AsyncCallback<T> callback) {

		boolean veto = false;

		veto = vetoRequest(getServiceEntryPoint(), methodName, requestData);

		if (veto) {

			callback.onFailure(new RpcCallVetoException());
			return null;

		} else {
			return super.doInvoke(responseReader, methodName, statsContext, requestData, callback);
		}


	}

	// ---------------------------------------------------------------------
	// ---------------------------------------------------------------------


	/**
	 * Preprocess raw response.
	 *
	 * @param response the response
	 */
	protected boolean preprocessRawResponse(Response response) {
		return true;
	}

	/**
	 * Veto request.
	 *
	 * @param serviceEntryPoint the service entry point
	 * @param methodName the method name
	 * @param requestData the request data
	 * @return true, if successful
	 */
	protected boolean vetoRequest (String serviceEntryPoint, String methodName, String requestData) {
		return false;
	}

}
