package net.glouz.myapp.model.api.request.authentication.login;

import net.glouz.myapp.model.api.request.BaseRequest;
import net.glouz.myapp.model.api.request.authentication.AuthenticationRequestsInterface;
import net.glouz.myapp.model.models.User;

import roboguice.util.temp.Ln;

/**
 * @author glouzonf
 */
public class LoginRequest extends BaseRequest<User, AuthenticationRequestsInterface> {

	private String mUsername;

	public LoginRequest(String username) {
		super(User.class, AuthenticationRequestsInterface.class);
		mUsername = username;
	}

	@Override
	public User loadDataFromNetwork() {
		Ln.e("Call web service Login ");
		return getService().login(mUsername);
	}
}
