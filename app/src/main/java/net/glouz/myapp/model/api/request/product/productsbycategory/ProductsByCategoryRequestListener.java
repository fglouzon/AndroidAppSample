package net.glouz.myapp.model.api.request.product.productsbycategory;

import com.octo.android.robospice.persistence.exception.SpiceException;

import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.model.api.request.product.BaseRequestListener;
import net.glouz.myapp.model.models.product.ProductsByCategory;

import de.greenrobot.event.EventBus;
import roboguice.util.temp.Ln;

/**
 * Created by glouzonf on 13/05/2015.
 */
public class ProductsByCategoryRequestListener extends BaseRequestListener<ProductsByCategory> {

    private static final String TAG = ProductsByCategoryRequestListener.class.getSimpleName();

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        super.onRequestFailure(spiceException);
        Ln.e(TAG + "Product by categories request failed");
    }

    @Override
    public void onRequestSuccess(final ProductsByCategory productsByCategory) {
        Ln.e(TAG + "Product by categories request success");
        if (productsByCategory != null) {
            Ln.e(productsByCategory.toString());
        }

        ProductEvent productEvent = new ProductEvent();
        productEvent.productsByCategoriesFetchedAndCached = true;
        productEvent.productsByCategoriesList = productsByCategory;
        EventBus.getDefault().post(productEvent);
    }

}
