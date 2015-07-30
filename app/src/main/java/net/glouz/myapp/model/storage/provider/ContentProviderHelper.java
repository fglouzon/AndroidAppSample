package net.glouz.myapp.model.storage.provider;

import com.octo.android.robospice.persistence.ormlite.RoboSpiceContentProvider;

import net.glouz.myapp.model.api.service.RequestServiceBase;
import net.glouz.myapp.model.models.product.Product;
import net.glouz.myapp.model.models.product.ProductCategories;
import net.glouz.myapp.model.models.product.ProductCategory;
import net.glouz.myapp.model.models.product.ProductsByCategory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by glouzonf on 28/05/2015.
 */
public class ContentProviderHelper extends RoboSpiceContentProvider {

    @Override
    public List<Class<?>> getExposedClasses() {
        return Arrays.<Class<?>>asList(ProductCategories.class, ProductCategory.class, ProductsByCategory.class, Product.class);
    }

    @Override
    public String getDatabaseName() {
        return RequestServiceBase.DATABASE_NAME;
    }

    @Override
    public int getDatabaseVersion() {
        return RequestServiceBase.DATABASE_VERSION;
    }



}
