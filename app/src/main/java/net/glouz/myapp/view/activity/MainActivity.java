package net.glouz.myapp.view.activity;


import android.os.Bundle;

import net.glouz.myapp.R;
import net.glouz.myapp.controller.MainActivityController;
import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.view.fragment.FirstScreenFragment;
import net.glouz.myapp.view.fragment.ProductsForCategoryFragment;

/**
 * Activity example.
 */
public class MainActivity extends BaseActivity {

    private ProductsForCategoryFragment mProductsForCategoryFragment = new ProductsForCategoryFragment();

    private MainActivityController mMainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            mProductsForCategoryFragment = new ProductsForCategoryFragment();
            mFragmentTransaction.add(R.id.main_activity_container, mProductsForCategoryFragment, FirstScreenFragment.TAG);
            mFragmentTransaction.commit();
        }
    }

    @Override
    void setContentView() {
        setContentView(R.layout.main_activity);
    }

    @Override
    boolean showNavigationDrawer() {
        return true;
    }

    @Override
    void onNavigationDrawerOpened() {
    }

    @Override
    void onNavigationDrawerClosed() {

    }

    @Override
    void initialiseObjects() {
        mMainActivityPresenter = new MainActivityController(getSpiceManager());
    }

    @Override
    void initialiseViews() {
    }

    @Override
    void updateViews() {
    }

    @Override
    void updateData() {
    }

    public void onEvent(ProductEvent productEvent) {
        super.onEvent(productEvent);

        if (productEvent != null) {
            if (productEvent.addToWishlist) {
                mMainActivityPresenter.saveProductsWishlist(true, productEvent.product);
            } else if (productEvent.removeFromWishlist) {
                mMainActivityPresenter.saveProductsWishlist(false, productEvent.product);
            }
        }
    }
}