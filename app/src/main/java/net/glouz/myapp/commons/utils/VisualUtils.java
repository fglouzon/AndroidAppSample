package net.glouz.myapp.commons.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.glouz.myapp.R;
import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.view.custom.ProgressDialogSampleApp;
import net.glouz.myapp.view.fragment.ErrorDialogFrag;

import de.greenrobot.event.EventBus;

/**
 * A collection of Utils to provide Visual effects across the app.
 * <p/>
 * Currently only contains the Progress Dialog effect. (Visuals to be changed by VD)
 *
 * @author phil.burtenshaw
 */
public class VisualUtils {

    public static final String TAG = VisualUtils.class.getSimpleName();
    private ProgressDialogSampleApp mProgressDialog;

    private static VisualUtils sVisualUtils;

    public static VisualUtils getInstance() {
        if (sVisualUtils == null) {
            sVisualUtils = new VisualUtils();
        }

        return sVisualUtils;
    }

    private VisualUtils() {
    }

    public void showProg(Context context, View view, boolean cancelable) {
        mProgressDialog = new ProgressDialogSampleApp(context);
        showProgress(mProgressDialog, null, view, cancelable);
    }

    public void showProg(Context context, String message, View view,
                                boolean cancelable) {
        mProgressDialog = new ProgressDialogSampleApp(context);
        showProgress(mProgressDialog, message, view, cancelable);
    }

    private static void showProgress(ProgressDialogSampleApp progress, String message,
                                     View view, boolean cancelable) {
        if (progress == null || !progress.isShowing()) {
            progress.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(final DialogInterface dialog) {
                    ProductEvent productEvent = new ProductEvent();
                    productEvent.cancelGetProductDetails = true;
                    EventBus.getDefault().post(productEvent);
                }
            });

            progress.show();
            progress.setText(message);
            progress.setCancelable(cancelable);
        }
    }

    /**
     * hides progress dialog.
     */
    public void hideProg() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (IllegalArgumentException iae) {
            // Do nothing.
            Log.i(TAG, iae.getMessage());
        }
    }

    /**
     * Shows a quick toast with a string message.
     *
     * @param message
     */
    public static void showToast(String message) {
        Toast.makeText(SampleApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * shows the error message dialog for timeout.
     *
     * @param activity
     */
    public static void showTimeoutDialog(Activity activity) {
        if (activity != null) {
            ErrorDialogFrag.newInstance(
                    activity.getString(R.string.http_timeout_error)).show(
                    activity.getFragmentManager(), ErrorDialogFrag.TAG);
        }
    }

    /**
     * shows the  error message dialog.
     *
     * @param activity
     * @param errorMessageStringId
     */
    public static void showErrorDialog(Activity activity,
                                       int errorMessageStringId) {
        if (activity != null) {
            ErrorDialogFrag.newInstance(
                    activity.getString(errorMessageStringId)).show(
                    activity.getFragmentManager(), ErrorDialogFrag.TAG);
        }
    }

}
