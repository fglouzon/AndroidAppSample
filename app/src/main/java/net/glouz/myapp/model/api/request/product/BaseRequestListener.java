package net.glouz.myapp.model.api.request.product;

import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.event.ErrorEvent;

import de.greenrobot.event.EventBus;

/**
 * Handles generic request listener logic.
 * 
 * @author glouzonf
 *
 * @param <T>
 */
public class BaseRequestListener<T> implements RequestListener<T> {

	protected EventBus mEventBus = EventBus.getDefault();
	private ErrorEvent mErrorEvent = null;

	@Override
	public void onRequestFailure(SpiceException spiceException) {
		if (spiceException != null) {

			if (spiceException instanceof NoNetworkException) {
				mErrorEvent = new ErrorEvent(SampleApplication.getInstance()
						.getString(R.string.connectivity_error));
				mEventBus.post(mErrorEvent);
				return;
			}
		}
	}

	@Override
	public void onRequestSuccess(T resultSuccess) {

	}

}
