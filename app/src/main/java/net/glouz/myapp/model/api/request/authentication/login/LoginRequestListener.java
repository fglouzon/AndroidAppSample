package net.glouz.myapp.model.api.request.authentication.login;

import com.octo.android.robospice.persistence.exception.SpiceException;

import net.glouz.myapp.event.AuthenticationEvent;
import net.glouz.myapp.model.api.request.product.BaseRequestListener;
import net.glouz.myapp.model.models.User;

import de.greenrobot.event.EventBus;
import roboguice.util.temp.Ln;

/**
 * Created by glouzonf on 13/05/2015.
 */
public class LoginRequestListener extends BaseRequestListener<User> {

        private static final String TAG = LoginRequestListener.class.getSimpleName();

        @Override
        public void onRequestFailure(SpiceException spiceException) {
                super.onRequestFailure(spiceException);
                Ln.e(TAG + "Login request failed");

        }

        @Override
        public void onRequestSuccess(final User user) {
                Ln.e(TAG + "Login request success");

                AuthenticationEvent authenticationEvent = new AuthenticationEvent();
                authenticationEvent.loginSuccess = true;
                authenticationEvent.user = user;
                EventBus.getDefault().post(authenticationEvent);
        }

}