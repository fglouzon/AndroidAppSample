package net.glouz.myapp.commons.utils;

import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.model.api.webapi.ProductWebapi;
import net.glouz.myapp.model.models.product.ProductCategories;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import roboguice.util.temp.Ln;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

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

    public static Observable isCacheExpired(SpiceManager spiceManager, Class<?> clazz, Object cacheKey) {

//        Observable cacheDateInit = Observable.create(
//                (Subscriber<? super Date> subscriber) -> {
//                    if (subscriber.isUnsubscribed()) {
//                        return;
//                    }
//
//                    try {
//                        Date dateInit = spiceManager.getDateOfDataInCache(ProductCategories.class, SampleApplication.getInstance().getString(R.string.products_category_women)).get();
//                        Log.e("TAG", "date cache init " + dateInit);
//
//                        subscriber.onNext(dateInit);
//
//                        subscriber.onCompleted();
//                    } catch (CacheCreationException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//        ).map(new Func1<Date, Boolean>() {
//            @Override
//            public Boolean call(Date date) {
//
//                Date dateExpiry = new Date(date.getTime() + (ProductWebapi.CACHE_DURATION));
//                Log.e("TAG", "date cache expiry " + dateExpiry);
//
//
//                return true;
//            }
//        }).subscribeOn(Schedulers.newThread());


        Observable<Boolean> mObservable1 = Observable.create(
                (Subscriber<? super Boolean> subscriber) -> {
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }

                    try {
                        Date dateInit = spiceManager.getDateOfDataInCache(ProductCategories.class, SampleApplication.getInstance().getString(R.string.products_category_women)).get();
                        Date dateExpiry = null;
                        Date currentDate = null;

                        if (dateInit != null){
                            dateExpiry = new Date(dateInit.getTime() + (ProductWebapi.CACHE_DURATION));
                            currentDate = new Date();

                            Log.e("TAG", "date cache init " + dateInit);
                            Log.e("TAG", "date cache expiry " + dateExpiry);
                        }

                        if (dateInit == null || currentDate.after(dateExpiry)) {
                            subscriber.onNext(true);
                        } else {
                            subscriber.onNext(false);
                        }

                        subscriber.onCompleted();
                    } catch (CacheCreationException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
        ).subscribeOn(Schedulers.newThread());
//
//
//        mObservable1.subscribe(
//                cacheExpired -> {
//                    Log.i("observable proceeding", "" + cacheExpired);
//                    Log.e("TAG", "cache expired " + cacheExpired);
//                },
//                onError -> Log.e("observable error", "..."),
//                () -> Log.e("observable completed", "done"));

        return mObservable1;
    }

}
