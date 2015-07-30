package net.glouz.myapp.model.api.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Base request class. Handles network connectivity issues Sets the retry policy
 * to 0 by default
 * 
 * @author glouzonf
 * 
 * @param <T>
 * @param <V>
 */
public class BaseRequest<T, V> extends RetrofitSpiceRequest<T, V> {

	public BaseRequest(Class<T> clazz, Class<V> retrofitedInterfaceClass) {
		super(clazz, retrofitedInterfaceClass);
		setRetryPolicy(null);
	}

	@Override
	public T loadDataFromNetwork() throws Exception {
		return null;
	}

}
