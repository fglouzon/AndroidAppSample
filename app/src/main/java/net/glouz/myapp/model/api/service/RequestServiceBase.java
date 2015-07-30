package net.glouz.myapp.model.api.service;

import android.app.Application;

import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.ormlite.InDatabaseObjectPersisterFactoryWithContentProvider;
import com.octo.android.robospice.persistence.ormlite.RoboSpiceDatabaseHelper;
import com.octo.android.robospice.retrofit.RetrofitJackson2SpiceService;
import com.squareup.okhttp.OkHttpClient;

import net.glouz.myapp.R;
import net.glouz.myapp.commons.utils.NetworkUtils;
import net.glouz.myapp.model.api.network.SampleAppErrorHandler;
import net.glouz.myapp.model.api.request.product.ProductRequestsInterface;
import net.glouz.myapp.model.models.User;
import net.glouz.myapp.model.models.product.Product;
import net.glouz.myapp.model.models.product.ProductCategories;
import net.glouz.myapp.model.models.product.ProductCategory;
import net.glouz.myapp.model.models.product.ProductDetails;
import net.glouz.myapp.model.models.product.ProductsByCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter.Builder;
import retrofit.client.OkClient;

/**
 * Robospice retrofit gson service, shows/hide log
 * and configure the okhhtp client for timeout for example.
 * <p>
 * Created by glouzaille on 06/05/2015.
 */
//public class RequestServiceBase extends RetrofitGsonSpiceService {
public class RequestServiceBase extends RetrofitJackson2SpiceService {

    public static final String DATABASE_NAME = "sample_database";
    public static final int DATABASE_VERSION = 1;

    protected Builder mBuilder;
    private RoboSpiceDatabaseHelper mDatabaseHelper;
    private InDatabaseObjectPersisterFactoryWithContentProvider mInDatabaseObjectPersisterFactoryWithContentProvider;

    @Override
    public CacheManager createCacheManager(Application application) {
        CacheManager cacheManager = new CacheManager();
        List<Class<?>> classCollection = new ArrayList<>();

        // add persisted classes to class collection, if they have a contract annotation,
        // contentprovider observers will be notified
        // of any change.
        classCollection.add(ProductCategories.class);
        classCollection.add(ProductCategory.class);
        classCollection.add(ProductsByCategory.class);
        classCollection.add(Product.class);
        classCollection.add(ProductDetails.class);
        classCollection.add(User.class);

        // init
        mDatabaseHelper = new RoboSpiceDatabaseHelper(application, DATABASE_NAME, DATABASE_VERSION);
        mInDatabaseObjectPersisterFactoryWithContentProvider = new InDatabaseObjectPersisterFactoryWithContentProvider(application, mDatabaseHelper, classCollection);
        cacheManager.addPersister(mInDatabaseObjectPersisterFactoryWithContentProvider);
        return cacheManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(ProductRequestsInterface.class);
    }

    @Override
    protected String getServerUrl() {
        return getResources().getString(R.string.api_endpoint);
    }

    @Override
    protected Builder createRestAdapterBuilder() {

        mBuilder = super.createRestAdapterBuilder();

//        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

//        mBuilder.setConverter(new GsonConverter(gson));

        mBuilder.setConverter(createConverter());

        NetworkUtils.showNetworkLogs(mBuilder);

        mBuilder.setErrorHandler(new SampleAppErrorHandler());

        OkHttpClient okHttpClient = new OkHttpClient();
        int readTimeout = getResources().getInteger(R.integer.read_timeout);
        int connectTimeout = getResources().getInteger(R.integer.connection_timeout);

        okHttpClient.setReadTimeout(readTimeout, TimeUnit.MILLISECONDS);
        okHttpClient.setConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS);

        mBuilder.setClient(new OkClient(okHttpClient));

        return mBuilder;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDatabaseHelper != null && mDatabaseHelper.isOpen()) {
            mDatabaseHelper.close();
        }
    }
}