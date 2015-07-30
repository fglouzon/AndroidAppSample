package net.glouz.myapp.model.api.request.product.productscategories;

import net.glouz.myapp.model.api.request.BaseRequest;
import net.glouz.myapp.model.api.request.product.ProductRequestsInterface;
import net.glouz.myapp.model.models.product.ProductCategories;

import roboguice.util.temp.Ln;

/**
 * @author glouzonf
 */
public class ProductsCategoriesRequest extends BaseRequest<ProductCategories, ProductRequestsInterface> {

	private String mProductCategoryType;

	public ProductsCategoriesRequest(String productCategoryType) {
		super(ProductCategories.class, ProductRequestsInterface.class);

		mProductCategoryType = productCategoryType;
	}

	@Override
	public ProductCategories loadDataFromNetwork() {
		Ln.e("Call web service products categories ");
		return getService().getProductsCategories(mProductCategoryType);
	}
}
