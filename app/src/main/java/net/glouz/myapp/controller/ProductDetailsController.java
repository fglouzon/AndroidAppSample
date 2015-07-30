package net.glouz.myapp.controller;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.business.product.manager.ProductManager;
import net.glouz.myapp.model.api.webapi.ProductWebapi;
import net.glouz.myapp.model.models.product.Product;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class ProductDetailsController extends BaseController {

    private ProductWebapi mProductWebapi;
    private ProductManager mProductManager;

    public ProductDetailsController(SpiceManager spiceManager) {
        mProductWebapi = new ProductWebapi(spiceManager);
        mProductManager = new ProductManager(mProductWebapi);
    }

    /**
     * retrieve the products bag saved in shared prefs, updates it and saves it again.
     * @param product
     */
    public void saveProductsBag (Product product){
        mProductManager.saveProductsBag(product);
    }

    /**
     * get a product details.
     *
     * @param productId
     */
    public void getProductDetails(int productId) {
        mProductManager.getProductDetails(productId);
    }

}
