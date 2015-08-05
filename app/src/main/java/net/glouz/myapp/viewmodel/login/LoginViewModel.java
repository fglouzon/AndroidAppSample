package net.glouz.myapp.viewmodel.login;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.business.authentication.manager.AuthenticationManager;
import net.glouz.myapp.model.api.webapi.AuthenticationWebapi;
import net.glouz.myapp.view.activity.MainActivity;
import net.glouz.myapp.viewmodel.login.view.LoginViewInterface;

import eu.inloop.viewmodel.AbstractViewModel;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by glouzonf on 30/07/2015.
 */
public class LoginViewModel extends AbstractViewModel<LoginViewInterface> {


    private SpiceManager mSpiceManager;
    private AuthenticationWebapi mAuthenticationWebapi;
    private AuthenticationManager mAuthenticationManager;

    @Override
    public void bindView(@NonNull LoginViewInterface view) {
        super.bindView(view);

        mSpiceManager = SampleApplication.getInstance().getSpiceManager();
        mAuthenticationWebapi = new AuthenticationWebapi(mSpiceManager);
        mAuthenticationManager = new AuthenticationManager(mAuthenticationWebapi);
    }

    //we use RXjava to observe the 2 edittexts
    //https://github.com/ReactiveX/RxJava/wiki/Subject
    public Observable validateLoginInputDataValidate(BehaviorSubject login, BehaviorSubject password) {
        return mAuthenticationManager != null ? mAuthenticationManager.validateLoginInputDataValidate(login, password) : null;
    }

    public void login (String login, String password){
        getView().showLoading(true);
        mAuthenticationManager.login(login, password);
    }

    public void goToMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void onModelRemoved() {
        super.onModelRemoved();
        //use this to cancel any planned requests
    }

}
