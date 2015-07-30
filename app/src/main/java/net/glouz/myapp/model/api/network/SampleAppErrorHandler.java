package net.glouz.myapp.model.api.network;

import com.octo.android.robospice.exception.NoNetworkException;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.commons.utils.VisualUtils;
import net.glouz.myapp.event.ErrorEvent;
import net.glouz.myapp.model.models.apierror.ApiError;

import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

import de.greenrobot.event.EventBus;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 *
 * Checks for network connectivity availability.
 * Handles the generic HTTP and specific API errors.
 *
 * Created by glouzonf on 12/05/2015.
 */
public class SampleAppErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError retrofitError) {

        EventBus mEventBus = EventBus.getDefault();
        ErrorEvent mErrorEvent;

        ApiError mApiError;

        VisualUtils.getInstance().hideProg();

        if (retrofitError != null) {
            if (retrofitError.getCause() != null) {
                //no network connection
                if (retrofitError.getCause() instanceof NoNetworkException) {
                    mErrorEvent = new ErrorEvent(SampleApplication.getInstance()
                            .getString(R.string.connectivity_error));
                    mEventBus.post(mErrorEvent);
                    return retrofitError;
                } else if (retrofitError.getCause() instanceof
                        InterruptedIOException || retrofitError.getCause() instanceof
                        SocketTimeoutException) {
                    //timeout exception
                    ApiErrorProcessor
                            .postNetworkErrorEvent(ApiErrorProcessor.HTTP_NETWORK_TIMEOUT_ERROR);
                    return retrofitError;
                } else if (retrofitError.getKind() == RetrofitError.Kind.NETWORK) {
                    //any other network exceptions
                    ApiErrorProcessor.postNetworkErrorEvent();
                    return retrofitError;
                }
            } else {
                //http errors
                mApiError = ApiErrorProcessor
                        .getApiCustomError(retrofitError);

                // generic HTTP errors
                if (mApiError == null) {
                    ApiErrorProcessor.postGenericHttpErrorEvent(retrofitError);
                    return retrofitError;
                } else {
                    //app specific HTTP errors or HTTP errors which require custom logic
                    ApiErrorProcessor.postApiErrorEvent(mApiError);
                    return retrofitError;
                }
            }
        }
        return retrofitError;
    }
}
