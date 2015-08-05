package net.glouz.myapp;

import android.app.Application;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.commons.utils.ContentProvidersUtils;
import net.glouz.myapp.model.api.service.RequestServiceMockRESTapiSampleApp;

/**
 * @author glouzonf
 */
public class SampleApplication extends Application {

//    public static RefWatcher getRefWatcher(Context context) {
//        SampleApplication application = (SampleApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }
//
//    private RefWatcher refWatcher;

    private SpiceManager mSpiceManager;

    private static SampleApplication sApplication;

    public static SampleApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        refWatcher = LeakCanary.install(this);

        mSpiceManager = new SpiceManager(RequestServiceMockRESTapiSampleApp.class);
        mSpiceManager.start(this);

        sApplication = this;

        ContentProvidersUtils.getListOfContentProviders();
    }

    public SpiceManager getSpiceManager() {
        return mSpiceManager;
    }
}