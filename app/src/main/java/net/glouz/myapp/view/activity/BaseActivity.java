package net.glouz.myapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.R;
import net.glouz.myapp.business.product.manager.ProductManager;
import net.glouz.myapp.commons.utils.VisualUtils;
import net.glouz.myapp.event.ErrorEvent;
import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.model.api.service.RequestServiceMockRESTapiSampleApp;
import net.glouz.myapp.model.api.service.RequestServiceSampleApp;
import net.glouz.myapp.view.fragment.ErrorDialogFrag;

import org.apache.commons.lang3.StringUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;
import de.greenrobot.event.EventBus;

/**
 * base activity that set the main layout, init the navigation drawer
 * and initialize the appropriate views.
 * listen to error events for appropriate UI updates.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    protected FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

    protected SpiceManager mSpiceManager;

    protected EventBus mEventBus = EventBus.getDefault();

    //navigation drawer
    private boolean mShowNavigationDrawer;
    @Optional
    @InjectView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @Optional
    @InjectView(R.id.navigationDrawerContainer)
    public LinearLayout mNavigationDrawerContainer;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    //toolbar
    @Optional
    @InjectView(R.id.toolbar)
    public Toolbar mToolbar;
    @Optional
    @InjectView(R.id.basketToolbar)
    public Button mBasketToolbarButton;
    @Optional
    @InjectView(R.id.wishlistToolbar)
    public Button mWishlistToolbarButton;

    protected String mNewSelectionCategoryId;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if (this instanceof  LoginActivity) {
            mSpiceManager = new SpiceManager(RequestServiceMockRESTapiSampleApp.class);
        } else {
            mSpiceManager = new SpiceManager(RequestServiceSampleApp.class);
        }

        setContentView();
        ButterKnife.inject(this);

        mShowNavigationDrawer = showNavigationDrawer();

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        //NAVIGATION DRAWER
        if (mShowNavigationDrawer) {
            initNavigationDrawer();
        }

        initialiseObjects();
        initialiseViews();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mShowNavigationDrawer) {
            mActionBarDrawerToggle.syncState();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mEventBus != null) {
            mEventBus.register(this);
        }

        if (mToolbar != null && mBasketToolbarButton != null){
                mBasketToolbarButton.setText(ProductManager.getTotalNumberProductsInBag());
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mEventBus != null) {
            mEventBus.unregister(this);
        }

    }

    abstract void setContentView();

    abstract boolean showNavigationDrawer();

    //custom behavior if showing the navigation drawer
    abstract void onNavigationDrawerOpened();
    abstract void onNavigationDrawerClosed();

    abstract void initialiseObjects();

    //add listeners or callbacks if necessary
    abstract void initialiseViews();

    abstract void updateViews();

    abstract void updateData();

    public void onEvent(ErrorEvent errorEvent) {
        if (errorEvent != null && !StringUtils.isEmpty(errorEvent.getErrorMessage())) {
            VisualUtils.getInstance().hideProg();

            ErrorDialogFrag.newInstance(errorEvent.getErrorMessage()).show(
                    getFragmentManager(), ErrorDialogFrag.TAG);
        }
    }

    public void onEvent(ProductEvent productEvent) {
        if (productEvent != null) {
            if (productEvent.productsBagSaved) {
                mBasketToolbarButton.setText(String.valueOf(productEvent.totalBagItems));
            }
        }
    }

    protected void showToolbarOptions(boolean show) {
        if (mBasketToolbarButton != null && mWishlistToolbarButton != null) {
            if (show) {
                mBasketToolbarButton.setVisibility(View.VISIBLE);
                mWishlistToolbarButton.setVisibility(View.VISIBLE);
            } else {
                mBasketToolbarButton.setVisibility(View.INVISIBLE);
                mWishlistToolbarButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Optional
    @OnClick({R.id.wishlistToolbar})
    public void wishlistToolbarClicked(View view) {
        if (!(this instanceof ProductsWishlistActivity)) {
            Intent intent = new Intent(this, ProductsWishlistActivity.class);
            startActivity(intent);
        }
    }

    @Optional
    @OnClick({R.id.basketToolbar})
    public void basketToolbarButtonClicked(View view) {
        if (!(this instanceof ProductsBagActivity)) {
            Intent intent = new Intent(this, ProductsBagActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            menu.clear();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                if (this instanceof ProductsWishlistActivity || this instanceof ProductsBagActivity) {
                    finish();
                }
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return mActionBarDrawerToggle;
    }

    public void setNewSelectionCategoryId(String newSelectionCategoryId) {
        this.mNewSelectionCategoryId = newSelectionCategoryId;
    }

    //NAVIGATION DRAWER
    private void initNavigationDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
                showToolbarOptions(true);

                onNavigationDrawerOpened();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                showToolbarOptions(false);

                onNavigationDrawerClosed();
            }
        };
    }

    @Override
    protected void onStart() {
        mSpiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        mSpiceManager.shouldStop();
        super.onStop();
    }

    public SpiceManager getSpiceManager() {
        return mSpiceManager;
    }
}
