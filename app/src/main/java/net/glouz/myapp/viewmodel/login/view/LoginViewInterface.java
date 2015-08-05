package net.glouz.myapp.viewmodel.login.view;

import net.glouz.myapp.model.models.User;

import eu.inloop.viewmodel.IView;

/**
 * Created by glouzonf on 30/07/2015.
 */
public interface LoginViewInterface extends IView {
    void showLoading(boolean show);
    void showError();
    void loginSuccessful(User user);
}
