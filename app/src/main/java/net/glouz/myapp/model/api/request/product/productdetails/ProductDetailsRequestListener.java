package net.glouz.myapp.model.api.request.product.productdetails;

import com.octo.android.robospice.persistence.exception.SpiceException;

import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.model.api.request.product.BaseRequestListener;
import net.glouz.myapp.model.models.product.ProductDetails;

import de.greenrobot.event.EventBus;
import roboguice.util.temp.Ln;

/**
 * Created by glouzonf on 13/05/2015.
 */
public class ProductDetailsRequestListener extends BaseRequestListener<ProductDetails> {

    private static final String TAG = ProductDetailsRequestListener.class.getSimpleName();

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        super.onRequestFailure(spiceException);
        Ln.e(TAG + "Product Details request failed");

    }

    @Override
    public void onRequestSuccess(final ProductDetails productDetails) {
        Ln.e(TAG + "Product Details request success");

        ProductEvent productEvent = new ProductEvent();
        productEvent.productDetailsRetrievedSuccefully = true;
        productEvent.productDetails = productDetails;
        EventBus.getDefault().post(productEvent);
    }

}
