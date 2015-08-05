package net.glouz.myapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.business.product.manager.ProductManager;
import net.glouz.myapp.model.api.webapi.ProductWebapi;
import net.glouz.myapp.model.models.product.ProductCategoriesContract;
import net.glouz.myapp.view.activity.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by glouzonf on 05/05/2015.
 */
public class FirstScreenController extends BaseController {

    private ProductWebapi mProductWebapi;
    private ProductManager mProductManager;

    //views
    private ProgressBar mProgressBar;
    private TextView mFirstScreenLoadSuccesTextView;
    private TextView mFirstScreenLoadDescriptionTextView;

    public FirstScreenController(View rootView, SpiceManager spiceManager) {
        mProgressBar = ButterKnife.findById(rootView, R.id.progressBarFirstScreen);
        mFirstScreenLoadSuccesTextView = ButterKnife.findById(rootView, R.id.firstScreenLoadSuccesTextView);
        mFirstScreenLoadDescriptionTextView = ButterKnife.findById(rootView, R.id.firstScreenLoadDescriptionTextView);

        mProductWebapi = new ProductWebapi(spiceManager);
        mProductManager = new ProductManager(mProductWebapi);
    }

    public FirstScreenController(View rootView) {
        mProgressBar = ButterKnife.findById(rootView, R.id.progressBarFirstScreen);
        mFirstScreenLoadSuccesTextView = ButterKnife.findById(rootView, R.id.firstScreenLoadSuccesTextView);
        mFirstScreenLoadDescriptionTextView = ButterKnife.findById(rootView, R.id.firstScreenLoadDescriptionTextView);

        mProductWebapi = new ProductWebapi(SampleApplication.getInstance().getSpiceManager());
        mProductManager = new ProductManager(mProductWebapi);
    }

    /**
     * get the products categories (women/men).
     */
    public void getProductsCategories() {
        mProgressBar.setVisibility(View.VISIBLE);
        Log.e("FSC PC", "...");
        mProductManager.getProductsCategories(true);
    }

    /**
     * products categories successfully fetched.
     */
    public void getProductsCategoriesSuccessfull() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mFirstScreenLoadSuccesTextView.setVisibility(View.VISIBLE);
    }

    /**
     * clear the wishlist and basket records.
     */
    public void clearWishlistAndBasketData() {
        mProductManager.clearSavedProductsWishlist();
        mProductManager.clearSavedProductsBasket();
    }

    public void updateViews(Cursor cursor) {
        categoriesInContentProvider(cursor);
//        Intent intent = new Intent(activity, MainActivity.class);
//        activity.startActivity(intent);
//        activity.finish();
    }

    public void goToMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public boolean categoriesInContentProvider(Cursor cursor) {
        boolean allCategoriesRetrieved = false;

//        if (getActivity() != null && !getActivity().isFinishing()) {
//            if (cursor == null && getActivity().getContentResolver() != null) {
//                cursor = getActivity().getContentResolver().query(ProductCategoriesContract.CONTENT_URI, null, null, null, null);
//            }

            final int totalCount = cursor.getCount();
            if (totalCount == 2) {

                allCategoriesRetrieved = true;
                while (cursor.moveToNext()) {

//                    category = true ;
//                    category = cursor.getString(cursor.getColumnIndex(ProductCategoriesContract.DESCRIPTION));
                    Log.e("CATEGORY CONTENT.PROV", cursor.getString(cursor.getColumnIndex(ProductCategoriesContract.DESCRIPTION)));

//                    mFirstScreenLoadDescriptionTextView.setText(cursor.getString(cursor.getColumnIndex(ProductCategoriesContract.DESCRIPTION)));
                }
            }

            cursor.close();
//        }

        return allCategoriesRetrieved;
    }

}
