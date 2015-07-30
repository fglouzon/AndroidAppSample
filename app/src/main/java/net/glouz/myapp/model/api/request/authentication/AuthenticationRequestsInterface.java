package net.glouz.myapp.model.api.request.authentication;

import net.glouz.myapp.model.models.User;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by glouzonf on 12/05/2015.
 */
public interface AuthenticationRequestsInterface {

    /**
     * api call mock for login.
     * http://echo.jsontest.com/username/glouzf/id_user/123456789/token/9c5354e2-351c-11e5-a151-feff819cdc9f/timestamp/1438083325/name/john/surname/connor
     *
     * @return
     */
    @GET("/username/{username}/id_user/123456789/token/9c5354e2-351c-11e5-a151-feff819cdc9f/timestamp/1438083325/name/john/surname/connor")
    User login(@Path("username") String username);

}
