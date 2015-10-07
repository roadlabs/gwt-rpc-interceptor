# New features comming soon #
  * Rules based preprocessing.

# About #

Main target:

  * Preprocess RPC response deserialization (client)
  * Preprocess RPC request (client)

...

# Quick start #

Add following lines to your code:

```

  <inherits name="com.googlecode.julio.gwt.rpcinterceptor.RpcInterceptor"/>

  <set-configuration-property name="com.goolecode.julio.interceptor.class" 
                              value="com.googlecode.julio.myapp.client.MyProxy" />
  <!-- com.googlecode.julio.myapp.client.MyProxy should extend RpcServiceInterceptor -->
 
  <generate-with
		class="com.googlecode.julio.gwt.rpcinterceptor.generator.RemoteInterfaceProxyGenerator">
		<when-type-assignable class="com.google.gwt.user.client.rpc.RemoteService" />
  </generate-with>


```


**com.googlecode.julio.myapp.client.MyProxy** :

```
public class MyProxy extends RpcServiceInterceptor {

	public MyProxy(String moduleBaseURL,
				   String remoteServiceRelativePath,
				   String serializationPolicyName,
				   Serializer serializer) {

		super(moduleBaseURL,
			  remoteServiceRelativePath,
			  serializationPolicyName,
			  serializer);
	}

	//-----------------------------------------------------------
	// interesting part
	//-----------------------------------------------------------

	@Override
	protected boolean preprocessRawResponse(Response response) {
		// do whatever with response and return false if you want
                // to bypass response processing
		return super.preprocessRawResponse(response);
	}

	@Override
	protected boolean vetoRequest(String serviceEntryPoint, String methodName, String requestData) {
		// return true if you want to fall into onFailure() hook
		// in your RPC async callback (cause will be RpcCallVetoException)
		return super.vetoRequest(serviceEntryPoint, methodName, requestData);
	}

}
```

### See project in action on [choolio.com](http://choolio.com) portal. ###