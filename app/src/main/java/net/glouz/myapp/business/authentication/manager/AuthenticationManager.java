package net.glouz.myapp.business.authentication.manager;

import net.glouz.myapp.business.BaseManager;
import net.glouz.myapp.business.authentication.rule.LoginValidateInputData;
import net.glouz.myapp.model.api.webapi.AuthenticationWebapi;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by glouzonf on 14/05/2015.
 */
public class AuthenticationManager extends BaseManager{

    public static final String TAG = AuthenticationManager.class.getSimpleName();

    private AuthenticationWebapi mAuthenticationWebapi;

    public AuthenticationManager(AuthenticationWebapi authenticationWebapi) {
        mAuthenticationWebapi = authenticationWebapi;
    }

    /**
     * validate login input data.
     *
     */
    public Observable validateLoginInputDataValidate(BehaviorSubject<String> username, BehaviorSubject<String> password) {
        return LoginValidateInputData.validate(username, password);
    }

    /**
     * login.
     *
     */
    public void login(String username, String password) {
        mAuthenticationWebapi.login(username);
    }

}
