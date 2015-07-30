package net.glouz.myapp;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import net.glouz.myapp.commons.utils.ContentProvidersUtils;

/**
 * @author glouzonf
 */
public class SampleApplication extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        SampleApplication application = (SampleApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    private static SampleApplication sApplication;

    public static SampleApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);

        sApplication = this;

        ContentProvidersUtils.getListOfContentProviders();
    }

}