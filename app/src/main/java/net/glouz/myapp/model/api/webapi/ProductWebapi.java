package net.glouz.myapp.model.api.webapi;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.model.api.request.product.productdetails.ProductDetailsRequest;
import net.glouz.myapp.model.api.request.product.productdetails.ProductDetailsRequestListener;
import net.glouz.myapp.model.api.request.product.productsbycategory.ProductsByCategoryRequest;
import net.glouz.myapp.model.api.request.product.productsbycategory.ProductsByCategoryRequestListener;
import net.glouz.myapp.model.api.request.product.productscategories.ProductsCategoriesRequest;
import net.glouz.myapp.model.api.request.product.productscategories.ProductsCategoriesRequestListener;
import net.glouz.myapp.model.models.product.ProductCategories;

import org.apache.commons.lang3.StringUtils;

import rx.Subscriber;

/**
 * Created by glouzaille on 06/05/2015.
 */
public class ProductWebapi extends BaseWebapi {

    //requests results will be cached for 10 minutes
    private static final long CACHE_DURATION = 10 * DurationInMillis.ONE_MINUTE;

    //request keycache if needed
    //private static final String PRODUCT_DETAILS_REQUEST = "product_details_request";

    private SpiceManager mSpiceManager;

    //products details
    private ProductsByCategoryRequest mProductsByCategoryRequest;
    private ProductsByCategoryRequestListener mProductsByCategoryRequestListener;

    //products by categories
    private ProductDetailsRequest mProductDetailsRequest;
    private ProductDetailsRequestListener mProductDetailsRequestListener;

    /**
     * We get the spicemanager service from the current activity.
     * The service handle the requests execution even on screen rotation.
     * "mSpiceManager.getFromCacheAndLoadFromNetworkIfExpired" will check if we have some cached data
     * and if the cache duration is still valid. If so we'll get the cached data, else it will execute
     * the request.
     *
     * @param spiceManager
     */
    public ProductWebapi(SpiceManager spiceManager) {
        this.mSpiceManager = spiceManager;
    }


    /**
     * Get men products categories web api call.
     *
     */
    public void getMenProductsCategories(Subscriber<? super ProductCategories> subscriber) {
        ProductsCategoriesRequest menProductsCategoriesRequest = new ProductsCategoriesRequest(SampleApplication.getInstance().getString(R.string.products_category_men));
        ProductsCategoriesRequestListener menProductsCategoriesRequestListener = new ProductsCategoriesRequestListener(subscriber);
        getProductsCategories(menProductsCategoriesRequest, menProductsCategoriesRequestListener, SampleApplication.getInstance().getString(R.string.products_category_men));
    }

    /**
     * Get women products categories web api call.
     *
     */
    public void getWomenProductsCategories(Subscriber<? super ProductCategories> subscriber) {
        ProductsCategoriesRequest womenProductsCategoriesRequest = new ProductsCategoriesRequest(SampleApplication.getInstance().getString(R.string.products_category_women));
        ProductsCategoriesRequestListener womenProductsCategoriesRequestListener = new ProductsCategoriesRequestListener(subscriber);
        getProductsCategories(womenProductsCategoriesRequest, womenProductsCategoriesRequestListener, SampleApplication.getInstance().getString(R.string.products_category_women));
    }

    /**
     * Get products categories web api call.
     * @param productsCategoriesRequest
     * @param productsCategoriesRequestListener
     */
    public void getProductsCategories(ProductsCategoriesRequest productsCategoriesRequest, ProductsCategoriesRequestListener productsCategoriesRequestListener, String requestTag) {

        if (mSpiceManager != null) {
            //we tag the request with the productCategoryType so we can differentiate them
            mSpiceManager.getFromCacheAndLoadFromNetworkIfExpired(productsCategoriesRequest, requestTag,
                    CACHE_DURATION, productsCategoriesRequestListener);
        }
    }

    /**
     * Get products by category web api call.
     * We set the category id as the cache key of the request.
     */
    public void getProductsForCategory(String categoryId) {

        if (!StringUtils.isEmpty(categoryId)) {
            mProductsByCategoryRequest = new ProductsByCategoryRequest(categoryId);
            mProductsByCategoryRequestListener = new ProductsByCategoryRequestListener();

            if (mSpiceManager != null) {
                mSpiceManager.getFromCacheAndLoadFromNetworkIfExpired(mProductsByCategoryRequest, categoryId,
                        CACHE_DURATION, mProductsByCategoryRequestListener);
            }
        }
    }

    /**
     * Get product details web api call.
     * we use the product id as the cache key of the request.
     */
    public void getProductDetails(int productId) {
        mProductDetailsRequest = new ProductDetailsRequest(productId);
        mProductDetailsRequestListener = new ProductDetailsRequestListener();

        if (mSpiceManager != null) {
            mSpiceManager.getFromCacheAndLoadFromNetworkIfExpired(mProductDetailsRequest, productId,
                    CACHE_DURATION, mProductDetailsRequestListener);
        }
    }

    /**
     * cancel the get product destails request if any being executing or pending.
     */
    public void cancelGetProductDetails(){
        if (mSpiceManager != null && mProductDetailsRequest != null) {
                mSpiceManager.cancel(mProductDetailsRequest);
        }
    }

}
