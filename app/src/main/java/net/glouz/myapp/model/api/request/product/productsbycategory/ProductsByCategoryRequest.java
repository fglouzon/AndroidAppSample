package net.glouz.myapp.model.api.request.product.productsbycategory;

import net.glouz.myapp.model.api.request.BaseRequest;
import net.glouz.myapp.model.api.request.product.ProductRequestsInterface;
import net.glouz.myapp.model.models.product.ProductsByCategory;

import roboguice.util.temp.Ln;

/**
 * @author glouzonf
 */
public class ProductsByCategoryRequest extends BaseRequest<ProductsByCategory, ProductRequestsInterface> {

	private String mCategoryId;

	public ProductsByCategoryRequest(String categoryId) {
		super(ProductsByCategory.class, ProductRequestsInterface.class);

		mCategoryId = categoryId;
	}

	@Override
	public ProductsByCategory loadDataFromNetwork() {
		Ln.e("Call web service Products by categories");
		return getService().getProductsByCategory(mCategoryId);
	}
}
