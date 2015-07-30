package net.glouz.myapp.model.api.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.event.ErrorEvent;
import net.glouz.myapp.model.models.apierror.ApiError;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import de.greenrobot.event.EventBus;
import retrofit.RetrofitError;

/**
 * handles network exceptions (socket timeouts, ...) and HTTP errors.
 *
 * @author glouzonf
 */

public class ApiErrorProcessor {

    public static final String TAG = ApiErrorProcessor.class.getSimpleName();

    public static final int FORBIDDEN = 403;
    public static final int INTERNAL_SERVER_ERROR = 500;

    //example of specific api errors
    public static final int ACCOUNT_NOT_FOUND = 602;
    public static final int IMAGE_UPLOAD_TEMPORARY_NOT_POSSIBLE = 2530;

    public static final String HTTP_NETWORK_TIMEOUT_ERROR = "Timeout";

    public static ErrorEvent postGenericHttpErrorEvent(
            RetrofitError retrofitError) {

        EventBus mEventBus = EventBus.getDefault();
        ErrorEvent mErrorEvent = null;
        Context mContext = SampleApplication.getInstance();

        int statusCode;

        if (retrofitError != null) {

            if (mContext != null) {

                if (retrofitError.getResponse() != null) {
                    statusCode = retrofitError.getResponse().getStatus();

                    switch (statusCode) {
                        case FORBIDDEN:
                            mErrorEvent = getErrorDescription(statusCode,
                                    mContext.getString(R.string.http_error_403));
                            mEventBus.post(mErrorEvent);
                            break;
                        case INTERNAL_SERVER_ERROR:
                            mErrorEvent = getErrorDescription(statusCode,
                                    mContext.getString(R.string.http_error_500));
                            mEventBus.post(mErrorEvent);
                            break;
                        default:
                            mErrorEvent = new ErrorEvent(mContext.getString(R.string.http_error_generic));
                            mEventBus.post(mErrorEvent);
                            break;
                    }
                }

            }
        }

        return mErrorEvent;
    }

    public static ApiError getApiCustomError(RetrofitError retrofitError) {

        Context mContext = SampleApplication.getInstance();
        ApiError apiError = null;
        String responseBody;

        if (retrofitError != null) {
            if (retrofitError.getResponse() != null
                    && retrofitError.getResponse().getBody() != null) {
                try {
                    responseBody = IOUtils.toString(retrofitError.getResponse()
                            .getBody().in(), CharEncoding.UTF_8);

                    if (!StringUtils.isEmpty(responseBody)) {
                        Gson gson = new Gson();

                        try {
                            apiError = gson.fromJson(responseBody,
                                    ApiError.class);
                        } catch (JsonParseException jsonParseException) {
                            Log.i(TAG,
                                    mContext.getResources()
                                            .getString(
                                                    R.string.app_internal_error_server_response_parsing));
                        }
                    }
                } catch (IOException e) {
                    Log.i(TAG, e.getMessage());
                }
            }
        }

        return apiError;
    }

    /**
     * check if the generic http error contains a specific api error.
     *
     * @param apiError
     * @return
     */
    public static ErrorEvent postApiErrorEvent(ApiError apiError) {

        EventBus mEventBus = EventBus.getDefault();
        ErrorEvent mErrorEvent = null;
        Context mContext = SampleApplication.getInstance();

        int statusCode;

        if (apiError != null) {

            if (mContext != null) {

                if (!StringUtils.isEmpty(apiError.description)) {

                    if (apiError.apiErrorDetails != null) {
                        if (apiError.apiErrorDetails != null
                                && !StringUtils
                                .isEmpty(apiError.apiErrorDetails.errors)) {
                            apiError.description = apiError.apiErrorDetails.errors;
                        }
                    }

                    statusCode = apiError.code;

                    switch (statusCode) {

                        case ACCOUNT_NOT_FOUND:
                            mErrorEvent = getErrorDescription(
                                    apiError.code,
                                    mContext.getString(R.string.api_error_602));
                            mEventBus.post(mErrorEvent);

                        case IMAGE_UPLOAD_TEMPORARY_NOT_POSSIBLE:
                            mErrorEvent = getErrorDescription(
                                    apiError.code,
                                    mContext.getString(R.string.api_error_2530));
                            mEventBus.post(mErrorEvent);
                            break;

                        default:
                            mErrorEvent = new ErrorEvent(mContext.getString(R.string.http_error_generic));
                            mEventBus.post(mErrorEvent);
                            break;
                    }
                }

            }
        }

        return mErrorEvent;
    }

    /**
     * handles network exceptions (SocketTimeoutException, ...).
     *
     * @param errorType
     * @return
     */
    public static ErrorEvent postNetworkErrorEvent(String errorType) {

        EventBus mEventBus = EventBus.getDefault();
        ErrorEvent mErrorEvent = null;

        if (HTTP_NETWORK_TIMEOUT_ERROR.equals(errorType)) {
            mErrorEvent = new ErrorEvent(HTTP_NETWORK_TIMEOUT_ERROR);
        }

        mEventBus.post(mErrorEvent);
        return mErrorEvent;
    }

    /**
     * handles generic network exceptions.
     *
     * @return
     */
    public static ErrorEvent postNetworkErrorEvent() {

        EventBus mEventBus = EventBus.getDefault();
        Context mContext = SampleApplication.getInstance();
        ErrorEvent mErrorEvent;

        mErrorEvent = new ErrorEvent(
                mContext.getString(R.string.http_error_generic));
        mEventBus.post(mErrorEvent);
        return mErrorEvent;
    }

    /**
     * get the associated copy for the error.
     *
     * @param code
     * @param description
     * @return
     */
    private static ErrorEvent getErrorDescription(int code, String description) {
        ErrorEvent mErrorEvent = null;

        if (!StringUtils.isEmpty(description)) {

            if (!description.equals(SampleApplication.getInstance().getString(R.string.http_error_generic))) {
                mErrorEvent = new ErrorEvent(code, description);
            } else {
                mErrorEvent = new ErrorEvent(code, SampleApplication.getInstance().getString(R.string.http_error_generic));
            }
        }

        return mErrorEvent;
    }


}
