package net.glouz.myapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import net.glouz.myapp.R;
import net.glouz.myapp.controller.NavigationDrawerController;
import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.model.adapter.NavigationDrawerAdapter;
import net.glouz.myapp.model.models.product.ProductCategories;
import net.glouz.myapp.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class NavigationDrawerFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = NavigationDrawerFragment.class.getSimpleName();

    @InjectView(R.id.menuListNavigationDrawer)
    public ListView mListNavigationDrawer;

    private DrawerLayout mDrawerLayout;
    public LinearLayout mNavigationDrawerContainer;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private LayoutInflater mLayoutInflater;
    private View mFooterViewNavigationDrawer;
    protected Button mMenProductCategoryButton;
    protected Button mWomenProductCategoryButton;

    private NavigationDrawerAdapter mNavigationDrawerAdapter;
    private List<ProductCategories> mProductCategories;

    private final int mWomenSelection = 0;
    private final int mMenSelection = 1;
    private int mLastSelection = -1;

    private NavigationDrawerController mNavigationDrawerPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.navigation_drawer_fragment, container, false);
        return mRootView;
    }

    @Override
    boolean butterknifeInject() {
        return true;
    }

    @Override
    void initialiseObjects() {
        mNavigationDrawerPresenter = new NavigationDrawerController(getSpiceManager());
    }

    @Override
    void initialiseViews() {
        initNavigationDrawer();
    }

    @Override
    void updateViews() {

    }

    @Override
    void updateData() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNavigationDrawerPresenter.getProductsCategories();
    }

    public void onEventMainThread(ProductEvent productEvent) {
        if (productEvent.productsCategoriesFetchedFromCached) {
            mProductCategories = productEvent.productCategoriesList;
            mWomenProductCategoryButton.setSelected(true);

            updateCategoryMenu(mWomenSelection);
        }
    }

    //NAVIGATION DRAWER
    private void initNavigationDrawer() {

        initDrawerLayoutAndNavigationDrawerContainer ();

        mProductCategories = new ArrayList<>();
        mLayoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFooterViewNavigationDrawer = mLayoutInflater.inflate(R.layout.header_navigation_drawer, null, false);
        mWomenProductCategoryButton = ButterKnife.findById(mFooterViewNavigationDrawer, R.id.womenProductCategoryButton);
        mWomenProductCategoryButton.setOnClickListener(this);
        mMenProductCategoryButton = ButterKnife.findById(mFooterViewNavigationDrawer, R.id.menProductCategoryButton);
        mMenProductCategoryButton.setOnClickListener(this);

        mListNavigationDrawer.addHeaderView(mFooterViewNavigationDrawer, null, false);


        mProductCategories = new ArrayList<>();
        ProductCategories productCategories = new ProductCategories();
        productCategories.list = new ArrayList<>();
        mProductCategories.add(productCategories);

        mNavigationDrawerAdapter = new NavigationDrawerAdapter(getActivity(), new ArrayList<>(mProductCategories.get(0).list));
        mListNavigationDrawer.setAdapter(mNavigationDrawerAdapter);

        mListNavigationDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mNavigationDrawerAdapter.getItem(position) != null) {
                    if (!getActivity().isFinishing()) {
                        ((BaseActivity) getActivity()).setNewSelectionCategoryId(mNavigationDrawerAdapter.getItem(position).categoryId);
                    }

                    if (mDrawerLayout == null) {
                        mDrawerLayout = ((BaseActivity) getActivity()).mDrawerLayout;
                    }

                    if (mNavigationDrawerContainer == null) {
                        mNavigationDrawerContainer =  ((BaseActivity) getActivity()).mNavigationDrawerContainer;
                    }

                    mDrawerLayout.closeDrawer(mNavigationDrawerContainer);

                        ProductEvent productEvent = new ProductEvent();
                        productEvent.newCategorySelectionFromNavigationDrawer = true;
                        productEvent.newSelectedCategoryId = mNavigationDrawerAdapter.getItem(position).categoryId;
                        EventBus.getDefault().post(productEvent);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == mWomenProductCategoryButton) {
            updateCategoryMenu(mWomenSelection);
            setSelectionHeaderNavigationDrawer(mWomenProductCategoryButton);
        } else if (view == mMenProductCategoryButton) {
            updateCategoryMenu(mMenSelection);
            setSelectionHeaderNavigationDrawer(mMenProductCategoryButton);
        }
    }

    private void setSelectionHeaderNavigationDrawer(View view) {
        if (view == mWomenProductCategoryButton) {
            mWomenProductCategoryButton.setSelected(true);
            mMenProductCategoryButton.setSelected(false);
        } else {
            mWomenProductCategoryButton.setSelected(false);
            mMenProductCategoryButton.setSelected(true);
        }
    }

    private void updateCategoryMenu(int currentSelection) {
        if (mLastSelection != currentSelection && mProductCategories != null && !mProductCategories.isEmpty()) {
            mNavigationDrawerAdapter.clear();
            mNavigationDrawerAdapter.addAll(mProductCategories.get(currentSelection).list);
            mNavigationDrawerAdapter.notifyDataSetChanged();
            mLastSelection = currentSelection;

            resetSelection();
        }
    }

    //we reset the selection of the current selected category
    //each time the user switches from women to men categories and vice versa
    //we have to refresh the list of products if it's showned.
    private void resetSelection() {
        if (mNavigationDrawerAdapter != null && mNavigationDrawerAdapter.getCount() > 0) {

            mListNavigationDrawer.setItemChecked(1, true);

            initDrawerLayoutAndNavigationDrawerContainer();

            if (mDrawerLayout !=null) {
                    ProductEvent productEvent = new ProductEvent();
                    productEvent.categoryTypeSwitchedFromNavigationDrawer = true;
                    productEvent.newSelectedCategoryId = mNavigationDrawerAdapter.getItem(0).categoryId;
                    EventBus.getDefault().post(productEvent);
            }
        }
    }

    private void initDrawerLayoutAndNavigationDrawerContainer () {
        if (!getActivity().isFinishing() && ((BaseActivity) getActivity()).getActionBarDrawerToggle() != null) {
            mDrawerLayout = ((BaseActivity) getActivity()).mDrawerLayout;
            mNavigationDrawerContainer = ((BaseActivity) getActivity()).mNavigationDrawerContainer;
            mActionBarDrawerToggle = ((BaseActivity) getActivity()).getActionBarDrawerToggle();

            if (mDrawerLayout != null && mActionBarDrawerToggle != null) {
                mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
                mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            }
        }
    }
}