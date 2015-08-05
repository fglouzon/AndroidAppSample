package net.glouz.myapp.model.api.request.product.productscategories;

import com.octo.android.robospice.persistence.exception.SpiceException;

import net.glouz.myapp.model.api.request.product.BaseRequestListener;
import net.glouz.myapp.model.models.product.ProductCategories;

import roboguice.util.temp.Ln;
import rx.Subscriber;

/**
 * Created by glouzonf on 13/05/2015.
 */
public class ProductsCategoriesRequestListener extends BaseRequestListener<ProductCategories> {

    private static final String TAG = ProductsCategoriesRequestListener.class.getSimpleName();
    private Subscriber<? super ProductCategories> mSubscriber;

    public ProductsCategoriesRequestListener(Subscriber<? super ProductCategories> subscriber) {
        mSubscriber = subscriber;
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        super.onRequestFailure(spiceException);
        Ln.e(TAG + "Product categories request failed");
    }

    @Override
    public void onRequestSuccess(final ProductCategories productCategories) {
        Ln.e(TAG + "Product categories request success");

        if (productCategories != null) {
            Ln.e(productCategories.toString());
        }

        mSubscriber.onNext(productCategories);
        mSubscriber.onCompleted();
    }

}
