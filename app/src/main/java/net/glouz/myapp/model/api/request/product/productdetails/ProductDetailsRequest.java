package net.glouz.myapp.model.api.request.product.productdetails;

import net.glouz.myapp.model.api.request.BaseRequest;
import net.glouz.myapp.model.api.request.product.ProductRequestsInterface;
import net.glouz.myapp.model.models.product.ProductDetails;

import roboguice.util.temp.Ln;

/**
 * @author glouzonf
 */
public class ProductDetailsRequest extends BaseRequest<ProductDetails, ProductRequestsInterface> {

	private int mProductId;

	public ProductDetailsRequest(int productId) {
		super(ProductDetails.class, ProductRequestsInterface.class);

		mProductId = productId;
	}

	@Override
	public ProductDetails loadDataFromNetwork() {
		Ln.e("Call web service Product Details ");
		return getService().getProductDetails(mProductId);
	}
}
