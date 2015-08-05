package net.glouz.myapp.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.glouz.myapp.R;
import net.glouz.myapp.commons.utils.BehaviorUtils;
import net.glouz.myapp.event.AuthenticationEvent;
import net.glouz.myapp.model.models.User;
import net.glouz.myapp.viewmodel.login.LoginViewModel;
import net.glouz.myapp.viewmodel.login.view.LoginViewInterface;

import butterknife.InjectView;
import rx.Observable;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class LoginFragment extends BaseFragmentViewModel<LoginViewInterface, LoginViewModel> implements LoginViewInterface{

    public static final String TAG = LoginFragment.class.getSimpleName();

    @InjectView(R.id.progressBarLogin)
    ProgressBar mProgressBar;
    @InjectView(R.id.loginTextinput)
    TextInputLayout mLoginTextinput;
    @InjectView(R.id.loginEdittext)
    EditText mLoginEdittext;
    @InjectView(R.id.passwordTextinput)
    TextInputLayout mPasswordTextinput;
    @InjectView(R.id.passwordEdittext)
    EditText mPasswordEdittext;
    @InjectView(R.id.loginButton)
    Button mLoginButton;
    @InjectView(R.id.loginLoadSuccesTextView)
    TextView mLoginLoadSuccesTextView;

    private BehaviorSubject<String> mLogin;
    private BehaviorSubject<String> mPassword;

    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        Bundle args = new Bundle();
        loginFragment.setArguments(args);
        return loginFragment;
    }

    @Override
    public Class<LoginViewModel> getViewModelClass() {
        return LoginViewModel.class;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayoutId(R.layout.login_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    void initialiseObjects() {
    }

    @Override
    void initialiseViews() {
        initFieldsValidation();
        mLoginButton.setOnClickListener(v -> getViewModel().login(mLoginEdittext.getText().toString(), mPasswordEdittext.getText().toString()));
    }

    @Override
    void updateViews() {

    }

    @Override
    void updateData() {
    }

    public void onEvent(AuthenticationEvent authenticationEvent) {
        loginSuccessful(authenticationEvent.user);
    }

    private void initFieldsValidation() {
        mLogin = BehaviorUtils.getBehaviorSubject(mLoginEdittext);
        mPassword = BehaviorUtils.getBehaviorSubject(mPasswordEdittext);

        //in this case we check that the login and password fields are not empty
        //to activate the enable the login button

        Observable observable = getViewModel().validateLoginInputDataValidate(mLogin, mPassword);

        if (observable != null){
            observable.subscribe(new Action1<boolean[]>() {
                @Override
                public void call(boolean[] results) {
                    if (!(results[0] && results[1])) {
//                    if (!results[0]) {
//                        mLoginTextinput.setError("login error");
//                    } else {
//                        mLoginTextinput.setError(null);
//                    }
//
//                    if (!results[1]) {
//                        mPasswordTextinput.setError("password error");
//                    } else {
//                        mPasswordTextinput.setError(null);
//                    }

                        mLoginButton.setEnabled(false);
                        mLoginLoadSuccesTextView.setText("");
                    } else {
                        mLoginTextinput.setError(null);
                        mPasswordTextinput.setError(null);

                        mLoginButton.setEnabled(true);
                    }
                }


            }, throwable -> Log.e("Error login validation", "error"));
        }
    }

    @Override
    public void showLoading(boolean show) {
        if (show){
            mProgressBar.setVisibility(View.VISIBLE);
            mLoginLoadSuccesTextView.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mLoginLoadSuccesTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void loginSuccessful(User user) {
        showLoading(false);
        mLoginLoadSuccesTextView.setText(user.toString());
//        getViewModel().goToMainActivity(getActivity());
    }
}