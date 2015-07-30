package net.glouz.myapp.view.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;
import com.viewpagerindicator.LinePageIndicator;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.model.adapter.ViewPagerAdapter;
import net.glouz.myapp.model.models.product.Product;
import net.glouz.myapp.model.models.product.ProductDetails;
import net.glouz.myapp.model.storage.sharedprefs.SharedPrefsStorageProvider;
import net.glouz.myapp.controller.ProductDetailsController;

import org.apache.commons.lang3.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class ProductDetailsFragment extends BaseFragment {

    public static final String TAG = ProductDetailsFragment.class.getSimpleName();

    @InjectView(R.id.brandProductDetails)
    public TextView mBrandProductDetails;
    @InjectView(R.id.pagerProductDetails)
    public ViewPager mviewPager;
    @InjectView(R.id.indicatorProductDetails)
    public LinePageIndicator mIndicator;
    @InjectView(R.id.descriptionProductDetails)
    public TextView mDescriptionProductDetails;
    @InjectView(R.id.addToCartProductDetails)
    public Button mAddToCartProductDetails;


    private final static int MARGIN_PAGER = -100;
    private PagerAdapter mAdapter;

    private ProductDetailsController mProductDetailsPresenter;
    private int mProductId;
    private ProductDetails mProductDetails;
    private String[] images;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);

        mRootView = inflater.inflate(R.layout.product_details_fragment, container, false);
        return mRootView;
    }

    @Override
    boolean butterknifeInject() {
        return true;
    }

    @Override
    void initialiseObjects() {
        mProductDetailsPresenter = new ProductDetailsController(getSpiceManager());
        mProductId = SharedPrefsStorageProvider.getPreferencesInt(SharedPrefsStorageProvider.ID_PRODUCT_KEY);
    }

    @Override
    void initialiseViews() {
        mviewPager.setPageMargin(MARGIN_PAGER);
    }

    @Override
    void updateViews() {

        if (mProductDetails != null) {
            if (mProductDetails.productImageUrls != null && !mProductDetails.productImageUrls.isEmpty()) {
                images = mProductDetails.productImageUrls.toArray(new String[mProductDetails.productImageUrls.size()]);
                mAdapter = new ViewPagerAdapter(getActivity(), images);

            }
            mviewPager.setAdapter(mAdapter);
            mIndicator.setViewPager(mviewPager);

            if (!StringUtils.isEmpty(mProductDetails.brand)) {
                mBrandProductDetails.setText(mProductDetails.brand);
            }

            if (!StringUtils.isEmpty(mProductDetails.description)) {
                mDescriptionProductDetails.setText(mProductDetails.description);
            }

            if (!StringUtils.isEmpty(mProductDetails.currentPrice)) {
                mAddToCartProductDetails.setText(getString(R.string.add_to_bag_product_details, mProductDetails.currentPrice));
            }
        }

    }

    @Override
    void updateData() {
        mProductDetailsPresenter.getProductDetails(mProductId);
    }

    @Optional
    @OnClick({R.id.addToCartProductDetails})
    public void addToCartProductDetailsClicked(View view) {

        //update product image for the gridview adapter
        if (mProductDetails != null && mProductDetails.productImageUrls != null &&
                mProductDetails.productImageUrls.size() >0) {
            mProductDetails.productImageUrl = mProductDetails.productImageUrls;
        }

        mProductDetailsPresenter.saveProductsBag((Product) mProductDetails);
    }

    public void onEvent(ProductEvent productEvent) {
        if (productEvent != null) {
            if (productEvent.productDetailsRetrievedSuccefully) {
                mProductDetails = productEvent.productDetails;
                updateViews();
            }
        }
    }
}