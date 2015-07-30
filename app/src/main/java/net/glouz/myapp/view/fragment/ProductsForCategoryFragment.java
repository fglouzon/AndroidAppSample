package net.glouz.myapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.squareup.leakcanary.RefWatcher;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.commons.utils.VisualUtils;
import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.model.adapter.ProductsGridviewAdapter;
import net.glouz.myapp.model.models.product.Product;
import net.glouz.myapp.model.storage.sharedprefs.SharedPrefsStorageProvider;
import net.glouz.myapp.controller.ProductsForCategoryController;
import net.glouz.myapp.view.activity.ProductDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class ProductsForCategoryFragment extends BaseFragment {

    public static final String TAG = ProductsForCategoryFragment.class.getSimpleName();

    @InjectView(R.id.productsProgressBar)
    public ProgressBar mProductsProgressBar;

    @InjectView(R.id.productsGridView)
    public GridView mproductsGridView;
    private ProductsGridviewAdapter mProductsGridviewAdapter;
    private List<Product> mProductsByCategory;

    private ProductsForCategoryController mProductsForCategoryPresenter;

    private String mCurrentCategoryId;
    private String mNewSelectionCategoryId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.products_list_fragment, container, false);
        return mRootView;
    }

    @Override
    boolean butterknifeInject() {
        return true;
    }

    @Override
    void initialiseObjects() {
        mProductsForCategoryPresenter = new ProductsForCategoryController(getSpiceManager());
    }

    @Override
    void initialiseViews() {
        initProductsGridview();
    }

    @Override
    void updateViews() {

    }

    @Override
    void updateData() {
    }

    private void initProductsGridview() {

        mProductsByCategory = new ArrayList<>();
        mProductsGridviewAdapter = new ProductsGridviewAdapter(getActivity(), mProductsByCategory);
        mproductsGridView.setAdapter(mProductsGridviewAdapter);
    }

    public void onEvent(ProductEvent productEvent) {
        if (productEvent.categoryTypeSwitchedFromNavigationDrawer) {
            mNewSelectionCategoryId = productEvent.newSelectedCategoryId;
            getProductsForCategory();
        } else if (productEvent.productsByCategoriesFetchedAndCached) {
            mProductsProgressBar.setVisibility(View.INVISIBLE);

            if (productEvent.productsByCategoriesList != null) {
                if (productEvent.productsByCategoriesList.listings != null &&
                        !productEvent.productsByCategoriesList.listings.isEmpty()) {
                    mProductsByCategory = new ArrayList<>(productEvent.productsByCategoriesList.listings);
                    mProductsGridviewAdapter.clear();
                    mProductsGridviewAdapter.addAll(mProductsByCategory);
                    mProductsGridviewAdapter.notifyDataSetChanged();
                }
            }
        } else if (productEvent.newCategorySelectionFromNavigationDrawer) {
            mNewSelectionCategoryId = productEvent.newSelectedCategoryId;
            getProductsForCategory();
        } else if (productEvent.productDetailsRetrievedSuccefully) {
            if (productEvent.productDetails != null){
                showProductDetails(productEvent.productDetails.productId);
            }
        } else if (productEvent.getProductDetails) {
            if (productEvent.product != null) {
                VisualUtils.getInstance().showProg(getActivity(), getActivity().getCurrentFocus(), true);
                mProductsForCategoryPresenter.getProductDetails(productEvent.product.productId);
            }
        } else if (productEvent.cancelGetProductDetails) {
            mProductsForCategoryPresenter.cancelGetProductDetails();
        }
    }

    private void getProductsForCategory() {
        if (!mNewSelectionCategoryId.isEmpty() && !mNewSelectionCategoryId.equals(mCurrentCategoryId)) {
            mProductsProgressBar.setVisibility(View.VISIBLE);
            mProductsGridviewAdapter.clear();
            mProductsGridviewAdapter.notifyDataSetChanged();
            mProductsForCategoryPresenter.getProductsForCategories(mNewSelectionCategoryId);
            mCurrentCategoryId = mNewSelectionCategoryId;
        }
    }

    //TODO
    //put in data manager product details logic
    //we save locally the id of the product
    //get the cached details of the product from robospice
    //if cache expired robospice will try to fech the latest data
    private void showProductDetails(int productId) {
        VisualUtils.getInstance().hideProg();

        SharedPrefsStorageProvider.savePreferences(SharedPrefsStorageProvider.ID_PRODUCT_KEY, productId);

        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        startActivity(intent);
    }
}