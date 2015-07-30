package net.glouz.myapp.business.product.manager;


import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.business.BaseManager;
import net.glouz.myapp.business.product.rule.ProductCategoriesRule;
import net.glouz.myapp.commons.utils.ModelUtils;
import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.model.api.webapi.ProductWebapi;
import net.glouz.myapp.model.models.product.Product;
import net.glouz.myapp.model.models.product.ProductCategories;
import net.glouz.myapp.model.storage.sharedprefs.SharedPrefsStorageProvider;

import java.io.IOException;
import java.util.List;

import de.greenrobot.event.EventBus;
import rx.Observable;

/**
 * Created by glouzonf on 14/05/2015.
 */
public class ProductManager extends BaseManager {

    public static final String TAG = ProductManager.class.getSimpleName();

    private ProductWebapi mProductWebapi;
    private ProductCategoriesRule mProductCategoriesRule;

    public ProductManager(ProductWebapi productWebapi) {
        mProductWebapi = productWebapi;
    }

    /**
     * return the list of products categories for women and men.
     *
     * @return
     */
    public void getProductsCategories() {
        mProductCategoriesRule = new ProductCategoriesRule();
        Observable<List<ProductCategories>> productCategoriesObservable=  mProductCategoriesRule.getProductsCategories(mProductWebapi);

        productCategoriesObservable.subscribe(
                productCategories -> {
                    Log.i("observable proceeding", "...");
                    ProductEvent productEvent = new ProductEvent();
                    productEvent.productCategoriesList = productCategories;
                    productEvent.productsCategoriesFetchedAndCached = true;
                    EventBus.getDefault().post(productEvent);
                },
                onError -> Log.e("observable error", "..."),
                () -> Log.e("observable completed", "done"));
    }

    /**
     * get products for a category.
     *
     * @param categoryId
     */
    public void getProductsForCategory(String categoryId) {
        mProductWebapi.getProductsForCategory(categoryId);
    }


    /**
     * get a product details.
     *
     * @param productId
     */
    public void getProductDetails(int productId) {
        mProductWebapi.getProductDetails(productId);
    }

    /**
     * retrieve the products wishlist saved in shared prefs, updates it and saves it again.
     *
     * @param add     //add or remove
     * @param product
     */
    public void saveProductsWishlist(boolean add, Product product) {
        mProductCategoriesRule = new ProductCategoriesRule();
        mProductCategoriesRule.saveProductToSharedPrefs(ProductCategoriesRule.PRODUCTS_WISHLIST, product, add);
    }

    /**
     * retrieve the products bag saved in shared prefs, updates it and saves it again.
     *
     * @param product
     */
    public void saveProductsBag(Product product) {
        mProductCategoriesRule = new ProductCategoriesRule();
        mProductCategoriesRule.saveProductToSharedPrefs(ProductCategoriesRule.PRODUCTS_BAG, product, true);

        ProductEvent productEvent = new ProductEvent();
        productEvent.productsBagSaved = true;

        if (getProductsBasket() != null) {
            productEvent.totalBagItems = getProductsBasket().size();
        }

        EventBus.getDefault().post(productEvent);
    }

    /**
     * get the wishlist products.
     */
    public List<Product>  getProductsWishlist() {
        List<Product> wishlistArray = null;
        ObjectMapper mapper = ModelUtils.getInstance().getObjectMapper();

        String wishlist = SharedPrefsStorageProvider
                .getPreferencesString(SharedPrefsStorageProvider.PRODUCT_WISHLIST_KEY);

        if (wishlist != null || !wishlist.isEmpty()) {

            try {
                wishlistArray = mapper.readValue(wishlist, Product.ProductList.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, SampleApplication.getInstance().getString(R.string.error_deserializing_model));
            }
        }

        return wishlistArray;
    }

    /**
     * get the products the bag.
     */
    public List<Product> getProductsBasket() {
        List<Product> productsArray = null;
        ObjectMapper mapper = ModelUtils.getInstance().getObjectMapper();

        String productsBag = SharedPrefsStorageProvider
                .getPreferencesString(SharedPrefsStorageProvider.PRODUCT_BAG_KEY);

        if (productsBag != null || !productsBag.isEmpty()) {

            try {
            productsArray = mapper.readValue(productsBag, Product.ProductList.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, SampleApplication.getInstance().getString(R.string.error_deserializing_model));
            }
        }

        return productsArray;
    }

    /**
     * get the total number of products in the bag.
     */
    public static String  getTotalNumberProductsInBag() {
        List<Product> productsArray = null;
        ObjectMapper mapper = ModelUtils.getInstance().getObjectMapper();
        String size = "0";

        String productsBag = SharedPrefsStorageProvider
                .getPreferencesString(SharedPrefsStorageProvider.PRODUCT_BAG_KEY);

        if (productsBag != null && !productsBag.isEmpty()) {

            try {
                productsArray = mapper.readValue(productsBag, Product.ProductList.class);

                if (productsArray != null && !productsArray.isEmpty()) {
                    size = String.valueOf(productsArray.size());
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, SampleApplication.getInstance().getString(R.string.error_deserializing_model));
            }
        }

        return size;
    }

    /**
     * clear the wishlist records.
     */
    public void clearSavedProductsWishlist() {
        SharedPrefsStorageProvider.savePreferences(SharedPrefsStorageProvider.PRODUCT_WISHLIST_KEY, "");
    }

    /**
     * clear the basket records.
     */
    public void clearSavedProductsBasket() {
        SharedPrefsStorageProvider.savePreferences(SharedPrefsStorageProvider.PRODUCT_BAG_KEY, "");
    }

    /**
     * cancel the get product destails request if any being executing or pending.
     */
    public void cancelGetProductDetails (){
        mProductWebapi.cancelGetProductDetails();
    }
}
