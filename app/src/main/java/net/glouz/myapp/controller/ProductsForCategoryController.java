package net.glouz.myapp.controller;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.business.product.manager.ProductManager;
import net.glouz.myapp.model.api.webapi.ProductWebapi;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class ProductsForCategoryController extends BaseController {

    private ProductWebapi mProductWebapi;
    private ProductManager mProductManager;

    public ProductsForCategoryController(SpiceManager spiceManager) {
        mProductWebapi = new ProductWebapi(spiceManager);
        mProductManager = new ProductManager(mProductWebapi);
    }

    /**
     * get products by categories.
     *
     * @param categoryId
     */
    public void getProductsForCategories(String categoryId) {
        mProductManager.getProductsForCategory(categoryId);
    }

    /**
     * get a product details.
     *
     * @param productId
     */
    public void getProductDetails(int productId) {
        mProductManager.getProductDetails(productId);
    }

    /**
     * cancel the get product destails request if any being executing or pending.
     */
    public void cancelGetProductDetails() {
        mProductManager.cancelGetProductDetails();
    }
}
