package net.glouz.myapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.glouz.myapp.R;
import net.glouz.myapp.controller.LoginController;
import net.glouz.myapp.event.AuthenticationEvent;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class LoginFragment extends BaseFragment {

    public static final String TAG = LoginFragment.class.getSimpleName();

    private LoginController mLoginController;
    private View mRootView;

    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        Bundle args = new Bundle();
        loginFragment.setArguments(args);
        return loginFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.login_fragment, container, false);
        return mRootView;
    }

    @Override
    void initialiseObjects() {
        mLoginController = new LoginController(mRootView, getSpiceManager());
    }


    @Override
    boolean butterknifeInject() {
        return false;
    }

    @Override
    void initialiseViews() {

    }

    @Override
    void updateViews() {

    }

    @Override
    void updateData() {
    }

    public void onEvent(AuthenticationEvent authenticationEvent) {
        mLoginController.loginSuccessfull(authenticationEvent.user);
    }
}