package net.glouz.myapp.controller;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.business.product.manager.ProductManager;
import net.glouz.myapp.model.api.webapi.ProductWebapi;
import net.glouz.myapp.model.models.product.Product;

import java.util.List;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class ProductsBasketController extends BaseController {

    private ProductWebapi mProductWebapi;
    private ProductManager mProductManager;

    public ProductsBasketController(SpiceManager spiceManager) {
        mProductWebapi = new ProductWebapi(spiceManager);
        mProductManager = new ProductManager(mProductWebapi);
    }

    /**
     * get a the list of products saved in the basket.
     *
     */
    public List<Product> getProductsBasket() {
        return mProductManager.getProductsBasket();
    }
}
