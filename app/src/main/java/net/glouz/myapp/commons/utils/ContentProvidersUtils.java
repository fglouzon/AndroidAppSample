package net.glouz.myapp.commons.utils;

import android.content.pm.ProviderInfo;
import android.util.Log;

import net.glouz.myapp.SampleApplication;

import java.util.List;

/**
 * Created by glouzaille on 06/05/2015.
 */
public class ContentProvidersUtils {

    public static void getListOfContentProviders() {
        List<ProviderInfo> providers = SampleApplication.getInstance().getPackageManager()
                .queryContentProviders(SampleApplication.getInstance().getPackageName(), android.os.Process.myUid(), 0);

        if (providers != null && !providers.isEmpty()){
            for (ProviderInfo provider : providers) {
                if (provider != null && provider.name != null ) {
                    Log.e("MY PROVIDERS", provider.name);
                }

            }
        }
    }
}
