package net.glouz.myapp.controller;

import android.util.Log;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.business.product.manager.ProductManager;
import net.glouz.myapp.model.api.webapi.ProductWebapi;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class NavigationDrawerController extends BaseController {

    private ProductWebapi mProductWebapi;
    private ProductManager mProductManager;

    public NavigationDrawerController(SpiceManager spiceManager) {
        mProductWebapi = new ProductWebapi(spiceManager);
        mProductManager = new ProductManager(mProductWebapi);
    }

    /**
     * get the products categories (women/men).
     */
    public void getProductsCategories() {
        Log.e("NDC PC", "...");
        mProductManager.getProductsCategories(false);
    }
}
