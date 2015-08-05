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

    public boolean productsCategoriesFetchedAndCachedFromFirstScreen;
    public boolean productsCategoriesFetchedFromCached;
    public List<ProductCategories> productCategoriesList;

    public boolean productsByCategoriesFetchedAndCached;
    public ProductsByCategory productsByCategoriesList;

    //the cache hasnt expired so the data didn't change in the content provider
    //and we need to reload the data
    public boolean restartLoaderProductCategories;

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
