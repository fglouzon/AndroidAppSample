package net.glouz.myapp.view.activity;


import android.os.Bundle;

import net.glouz.myapp.R;
import net.glouz.myapp.view.fragment.ProductDetailsFragment;

/**
 * Activity example.
 */
public class ProductDetailsActivity extends BaseActivity {

    private ProductDetailsFragment mProductDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            mProductDetailsFragment = new ProductDetailsFragment();
            mFragmentTransaction.add(R.id.product_details_activity_container, mProductDetailsFragment, ProductDetailsFragment.TAG);
            mFragmentTransaction.commit();
        }
    }

    @Override
    void setContentView() {
        setContentView(R.layout.product_details_activity);
    }

    @Override
    boolean showNavigationDrawer() {
        return false;
    }

    @Override
    void onNavigationDrawerOpened() {

    }

    @Override
    void onNavigationDrawerClosed() {

    }

    @Override
    void initialiseObjects() {
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
}
