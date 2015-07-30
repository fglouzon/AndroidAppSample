package net.glouz.myapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.R;
import net.glouz.myapp.business.authentication.manager.AuthenticationManager;
import net.glouz.myapp.commons.utils.BehaviorUtils;
import net.glouz.myapp.model.api.webapi.AuthenticationWebapi;
import net.glouz.myapp.model.models.User;
import net.glouz.myapp.view.activity.MainActivity;

import butterknife.ButterKnife;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class LoginController extends BaseController {

    private AuthenticationWebapi mAuthenticationWebapi;
    private AuthenticationManager mAuthenticationManager;

    //views
    private ProgressBar mProgressBar;
    private TextInputLayout mLoginTextinput;
    private EditText mLoginEdittext;
    private TextInputLayout mPasswordTextinput;
    private EditText mPasswordEdittext;
    private Button mLoginButton;
    private TextView mLoginLoadSuccesTextView;

    private BehaviorSubject<String> mLogin;
    private BehaviorSubject<String> mPassword;

    public LoginController(View rootView, SpiceManager spiceManager) {
        mProgressBar = ButterKnife.findById(rootView, R.id.progressBarLogin);

        mLoginTextinput = ButterKnife.findById(rootView, R.id.loginTextinput);
        mPasswordTextinput = ButterKnife.findById(rootView, R.id.passwordTextinput);

        mLoginEdittext = ButterKnife.findById(rootView, R.id.loginEdittext);
        mPasswordEdittext = ButterKnife.findById(rootView, R.id.passwordEdittext);
        mLoginButton = ButterKnife.findById(rootView, R.id.loginButton);

        mLoginLoadSuccesTextView = ButterKnife.findById(rootView, R.id.loginLoadSuccesTextView);

        mAuthenticationWebapi = new AuthenticationWebapi(spiceManager);
        mAuthenticationManager = new AuthenticationManager(mAuthenticationWebapi);

        initFieldsValidation();
        mLoginButton.setOnClickListener(v -> login());
    }

    public void login() {
        mProgressBar.setVisibility(View.VISIBLE);
        mAuthenticationManager.login(mLoginEdittext.getText().toString(), mPasswordEdittext.getText().toString());
    }

    public void loginSuccessfull(User user) {
        mProgressBar.setVisibility(View.INVISIBLE);

        mLoginLoadSuccesTextView.setVisibility(View.VISIBLE);
        mLoginLoadSuccesTextView.setText(user.toString());
    }

    public void goToMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    //we use RXjava to observe the 2 edittexts
    //https://github.com/ReactiveX/RxJava/wiki/Subject
    private void initFieldsValidation() {
        mLogin = BehaviorUtils.getBehaviorSubject(mLoginEdittext);
        mPassword = BehaviorUtils.getBehaviorSubject(mPasswordEdittext);



        //in this case we check that the login and password fields are not empty
        //to activate the enable the login button
        mAuthenticationManager.validateLoginInputDataValidate(mLogin, mPassword).subscribe(new Action1<boolean[]>() {
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
