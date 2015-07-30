package net.glouz.myapp.commons.utils;

import android.util.Log;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;

import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import roboguice.util.temp.Ln;

/**
 * Created by glouzonf on 12/05/2015.
 */
//TODO inject context for unit test
public class NetworkUtils extends BaseUtils {

    /**
     * show/hide logs on dev/prod mode
     *
     * @param builder
     */
    public static void showNetworkLogs(Builder builder) {

        if (SampleApplication.getInstance().getResources()
                .getBoolean(R.bool.dev_mode_app)) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL);
            Ln.getConfig().setLoggingLevel(Log.VERBOSE);
        } else {
            builder.setLogLevel(RestAdapter.LogLevel.NONE);
            Ln.getConfig().setLoggingLevel(Log.ERROR);
        }

    }

}
