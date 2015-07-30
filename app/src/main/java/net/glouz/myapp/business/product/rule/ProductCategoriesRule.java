package net.glouz.myapp.business.product.rule;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.commons.utils.ModelUtils;
import net.glouz.myapp.model.api.webapi.ProductWebapi;
import net.glouz.myapp.model.models.product.Product;
import net.glouz.myapp.model.models.product.ProductCategories;
import net.glouz.myapp.model.storage.sharedprefs.SharedPrefsStorageProvider;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by glouzonf on 15/05/2015.
 * <p>
 * Get the products categories for women and men and other products rules, ideally should be split in smaller rule files.
 * The 2 requests are made using rxjava, this would allow us to use some transformers if required in between requests,
 * for formatting, sorting, ranging, or merging data if required.
 *
 * We execute the 2 requests in parallel, observing on "Schedulers.newThread()",
 * we could have them executed sequentially observing on "AndroidSchedulers.mainThread()"
 * <p>
 * Post event with the list of categories when completed.
 */
public class ProductCategoriesRule extends BaseRule {

    public static final String TAG = ProductCategoriesRule.class.getSimpleName();

    public static final String PRODUCTS_WISHLIST = "products_wishlist";
    public static final String PRODUCTS_BAG = "products_bag";

    private Observable<List<ProductCategories>> mObservable;
    private Observable<ProductCategories> mProductCategoriesObservableWomen;
    private Observable<ProductCategories> mProductCategoriesObservableMen;

    /**
     * return the list of products categories for women and men.
     *
     * @param mProductWebapi
     * @return
     */
    public Observable<List<ProductCategories>> getProductsCategories(ProductWebapi mProductWebapi) {

        mProductCategoriesObservableWomen = Observable.create(
                (Subscriber<? super ProductCategories> subscriberWomen) -> {
                    if (subscriberWomen.isUnsubscribed()) {
                        return;
                    }
                    mProductWebapi.getWomenProductsCategories(subscriberWomen);
                }
        ).observeOn(Schedulers.newThread());

        mProductCategoriesObservableMen = Observable.create(
                (Subscriber<? super ProductCategories> subscriberMen) -> {
                    if (subscriberMen.isUnsubscribed()) {
                        return;
                    }
                    mProductWebapi.getMenProductsCategories(subscriberMen);
                }
        ).observeOn(Schedulers.newThread());

        mObservable = Observable.zip(mProductCategoriesObservableWomen, mProductCategoriesObservableMen, (value1, value2) -> {
            Log.i("Observable.zip 1", value1.toString());
            Log.i("Observable.zip 2", value2.toString());

            return Arrays.asList(value1, value2);
        });

        mObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        return mObservable;
    }

    /**
     * retrieve the products bag or wishist (PRODUCTS_WISHLIST, PRODUCTS_BAG) saved in shared prefs, updates it and saves it again.
     *
     * @param product
     */
    public void saveProductToSharedPrefs (String productTypeTosave, Product product, boolean add) {
        if (!StringUtils.isEmpty(productTypeTosave) && product != null) {
            ObjectMapper mapper = ModelUtils.getInstance().getObjectMapper();
            List<Product> wishlistArray = null;
            String wishlist = null;

            if (productTypeTosave.equals(PRODUCTS_WISHLIST)){
                wishlist = SharedPrefsStorageProvider
                        .getPreferencesString(SharedPrefsStorageProvider.PRODUCT_WISHLIST_KEY);
            } else if (productTypeTosave.equals(PRODUCTS_BAG)){
                wishlist = SharedPrefsStorageProvider
                        .getPreferencesString(SharedPrefsStorageProvider.PRODUCT_BAG_KEY);
            }


            if (wishlist == null || wishlist.isEmpty()) {
                wishlistArray = new ArrayList<>();
            } else {
                try {
                    wishlistArray = mapper.readValue(wishlist, Product.ProductList.class);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, SampleApplication.getInstance().getString(R.string.error_deserializing_model));
                }
            }

            //for now we can only add a product to the bag
            if (productTypeTosave.equals(PRODUCTS_BAG)) {
                wishlistArray.add(product);
            } else {
                if (!add) {
                    if (wishlistArray.size() == 1) {
                        wishlistArray.remove(0);
                    } else if (wishlistArray.size() > 1) {
                        for (Product p : wishlistArray) {

                            if (product.productId == p.productId) {
                                wishlistArray.remove(p);
                                break;
                            }
                        }
                    }
                } else {
                    wishlistArray.add(product);
                }
            }

            if (productTypeTosave.equals(PRODUCTS_WISHLIST)){
                SharedPrefsStorageProvider.savePreferences(SharedPrefsStorageProvider.PRODUCT_WISHLIST_KEY,
                        wishlistArray.toString());
            } else if (productTypeTosave.equals(PRODUCTS_BAG)){
                SharedPrefsStorageProvider.savePreferences(SharedPrefsStorageProvider.PRODUCT_BAG_KEY,
                        wishlistArray.toString());
            }

        }
    }

}