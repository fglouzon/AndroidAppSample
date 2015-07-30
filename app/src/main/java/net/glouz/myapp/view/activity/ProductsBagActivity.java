package net.glouz.myapp.view.activity;


import android.os.Bundle;

import net.glouz.myapp.R;
import net.glouz.myapp.view.fragment.ProductsBasketFragment;
import net.glouz.myapp.view.fragment.ProductsWishlistFragment;

/**
 * WishlistActivity.
 */
public class ProductsBagActivity extends BaseActivity {

    private ProductsBasketFragment mProductsBasketFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            mProductsBasketFragment = new ProductsBasketFragment();
            mFragmentTransaction.add(R.id.products_bag_activity_container, mProductsBasketFragment, ProductsWishlistFragment.TAG);
            mFragmentTransaction.commit();
        }
    }

    @Override
    void setContentView() {
        setContentView(R.layout.products_bag_activity);
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
