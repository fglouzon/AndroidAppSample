package net.glouz.myapp.model.api.webapi;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;

import net.glouz.myapp.model.api.request.authentication.login.LoginRequest;
import net.glouz.myapp.model.api.request.authentication.login.LoginRequestListener;

/**
 * Created by glouzaille on 06/05/2015.
 */
public class AuthenticationWebapi extends BaseWebapi {

    //requests results will be cached for 10 minutes
//    private static final long CACHE_DURATION = 10 * DurationInMillis.ONE_MINUTE;

    //request keycache if needed
    private static final String LOGIN_REQUEST_TAG = "login_request_tag";

    private SpiceManager mSpiceManager;

    private LoginRequest mLoginRequest;
    private LoginRequestListener mLoginRequestListener;

    /**
     * We get the spicemanager service from the current activity.
     * The service handle the requests execution even on screen rotation.
     * "mSpiceManager.getFromCacheAndLoadFromNetworkIfExpired" will check if we have some cached data
     * and if the cache duration is still valid. If so we'll get the cached data, else it will execute
     * the request.
     *
     * @param spiceManager
     */
    public AuthenticationWebapi(SpiceManager spiceManager) {
        this.mSpiceManager = spiceManager;
    }


    /**
     * Login web api call.
     *
     */
    public void login(String username) {

        mLoginRequest = new LoginRequest(username);
        mLoginRequestListener = new LoginRequestListener();

        if (mSpiceManager != null) {
            //we tag the request with the productCategoryType so we can differentiate them
            mSpiceManager.getFromCacheAndLoadFromNetworkIfExpired(mLoginRequest, LOGIN_REQUEST_TAG,
                    DurationInMillis.ALWAYS_EXPIRED, mLoginRequestListener);
        }
    }

}
