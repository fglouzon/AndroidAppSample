package net.glouz.myapp.event;

import net.glouz.myapp.model.models.product.Product;
import net.glouz.myapp.model.models.product.ProductCategories;
import net.glouz.myapp.model.models.product.ProductDetails;
import net.glouz.myapp.model.models.product.ProductsByCategory;

import java.util.List;

/**
 * Created by glouzonf on 15/05/2015.
 */
public class ProductEvent {

    public boolean productsCategoriesFetchedAndCached;
    public List<ProductCategories> productCategoriesList;

    public boolean productsByCategoriesFetchedAndCached;
    public ProductsByCategory productsByCategoriesList;

    //switching from women to men category or vice versa
    //in the drawer
    public boolean categoryTypeSwitchedFromNavigationDrawer;

    public boolean newCategorySelectionFromNavigationDrawer;
    public String newSelectedCategoryId;

    public boolean addToWishlist;
    public boolean removeFromWishlist;
    public Product product;

    public boolean getProductDetails;
    public boolean cancelGetProductDetails;
    public boolean productDetailsRetrievedSuccefully;
    public ProductDetails productDetails;

    public boolean productsBagSaved;
    public int totalBagItems;
}
