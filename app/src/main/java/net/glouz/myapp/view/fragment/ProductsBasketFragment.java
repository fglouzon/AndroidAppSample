package net.glouz.myapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.squareup.leakcanary.RefWatcher;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.model.adapter.ProductsGridviewAdapter;
import net.glouz.myapp.model.models.product.Product;
import net.glouz.myapp.controller.ProductsBasketController;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class ProductsBasketFragment extends BaseFragment {

    public static final String TAG = ProductsBasketFragment.class.getSimpleName();

    @InjectView(R.id.productBaGridView)
    public GridView mproductBaGridView;
    private ProductsGridviewAdapter mProductsGridviewAdapter;
    private List<Product> mProductsInBag;

    private ProductsBasketController mProductsBasketPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.products_bag_fragment, container, false);
        return mRootView;
    }

    @Override
    boolean butterknifeInject() {
        return true;
    }

    @Override
    void initialiseObjects() {
        mProductsBasketPresenter = new ProductsBasketController(getSpiceManager());
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

    public void onEvent(ProductEvent productEvent) {
    }

    private void initProductsGridview() {

        mProductsInBag = mProductsBasketPresenter.getProductsBasket();

        if (mProductsInBag != null && !mProductsInBag.isEmpty()) {
            mProductsGridviewAdapter = new ProductsGridviewAdapter(getActivity(), mProductsInBag);
            mproductBaGridView.setAdapter(mProductsGridviewAdapter);
        }
    }
}