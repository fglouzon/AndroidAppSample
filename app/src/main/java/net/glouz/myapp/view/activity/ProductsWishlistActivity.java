package net.glouz.myapp.view.activity;


import android.os.Bundle;

import net.glouz.myapp.R;
import net.glouz.myapp.view.fragment.ProductsWishlistFragment;

/**
 * WishlistActivity.
 */
public class ProductsWishlistActivity extends BaseActivity {

    private ProductsWishlistFragment mProductsWishlistFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            mProductsWishlistFragment = new ProductsWishlistFragment();
            mFragmentTransaction.add(R.id.wishlist_activity_container, mProductsWishlistFragment, ProductsWishlistFragment.TAG);
            mFragmentTransaction.commit();
        }
    }

    @Override
    void setContentView() {
        setContentView(R.layout.wishlist_activity);
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
