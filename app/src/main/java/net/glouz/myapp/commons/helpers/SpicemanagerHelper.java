package net.glouz.myapp.commons.helpers;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.model.api.service.RequestServiceSampleApp;

/**
 * Created by glouzonf on 16/05/2015.
 */
public class SpicemanagerHelper {

    private static SpicemanagerHelper sSpicemanagerHelper;

    private SpiceManager mSpiceManager = new SpiceManager(RequestServiceSampleApp.class);

    public static SpicemanagerHelper getInstance() {
        if (sSpicemanagerHelper == null) {
            sSpicemanagerHelper = new SpicemanagerHelper();
        }

        return sSpicemanagerHelper;
    }

    private SpicemanagerHelper() {
    }

    public SpiceManager getSpicemanager(){
        return mSpiceManager;
    }
}
