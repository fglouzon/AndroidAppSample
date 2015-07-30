package net.glouz.myapp.controller;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.business.product.manager.ProductManager;
import net.glouz.myapp.model.api.webapi.ProductWebapi;
import net.glouz.myapp.model.models.product.Product;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class MainActivityController extends BaseController {

    private ProductWebapi mProductWebapi;
    private ProductManager mProductManager;

    public MainActivityController(SpiceManager spiceManager) {
        mProductWebapi = new ProductWebapi(spiceManager);
        mProductManager = new ProductManager(mProductWebapi);
    }

    /**
     * retrieve the products wishlist saved in shared prefs, updates it and saves it again.
     *
     * @param add     //add or remove
     * @param product
     */
    public void saveProductsWishlist(boolean add, Product product) {
        mProductManager.saveProductsWishlist(add, product);
    }
}