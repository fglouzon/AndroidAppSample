package net.glouz.myapp.model.api.service;

import net.glouz.myapp.R;

/**
 * Created by glouzaille on 06/05/2015.
 */
public class RequestServiceSampleApp extends RequestServiceBase {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected String getServerUrl() {
        return getResources().getString(R.string.api_endpoint);
    }

}
