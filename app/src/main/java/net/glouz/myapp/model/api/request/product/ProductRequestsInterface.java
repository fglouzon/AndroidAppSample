package net.glouz.myapp.model.api.request.product;

import net.glouz.myapp.model.models.product.ProductCategories;
import net.glouz.myapp.model.models.product.ProductDetails;
import net.glouz.myapp.model.models.product.ProductsByCategory;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by glouzonf on 12/05/2015.
 */
public interface ProductRequestsInterface {

    /**
     * get products categories for women or men.
     *
     * @param productCategoryType
     * @return
     */
    @GET("/{productCategoryType}")
    ProductCategories getProductsCategories(@Path("productCategoryType") String productCategoryType);

    /**
     * get products by categories.
     *
     * @param categoryId
     * @return
     */
    @GET("/anycat_products.json")
    ProductsByCategory getProductsByCategory(@Query("catid") String categoryId);

    /**
     * get product details.
     *
     * @param productId
     * @return
     */
    @GET("/anyproduct_details.json")
    ProductDetails getProductDetails(@Query("catid") int productId);
}
