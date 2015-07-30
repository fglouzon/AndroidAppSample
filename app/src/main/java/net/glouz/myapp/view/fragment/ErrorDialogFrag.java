package net.glouz.myapp.view.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import net.glouz.myapp.R;

/**
 * @author glouzonf
 */
public class ErrorDialogFrag extends BaseDialogFrag {

    public static final String TAG = ErrorDialogFrag.class
            .getSimpleName();
    private static ErrorDialogFrag sInstance;
    private static Class<?> sActivityToRedirectTo;
    private static Activity sCurrentActivity;
    private static boolean sFinishCurrentActivity;
    protected String mTitle;

    public static ErrorDialogFrag newInstance(String errorMessage) {
        sInstance = new ErrorDialogFrag();
        sInstance.mTitle = errorMessage;
        return sInstance;
    }

    /**
     * Shows dialog and ondismiss redirects to another activity.
     *
     * @param errorMessage
     * @param activityToRedirectTo
     * @return
     */
    public static ErrorDialogFrag newInstance(String errorMessage,
                                              Activity currentActivity, Class<?> activityToRedirectTo,
                                              boolean finishCurrentActivity) {
        sInstance = new ErrorDialogFrag();
        sInstance.mTitle = errorMessage;
        sCurrentActivity = currentActivity;
        sActivityToRedirectTo = activityToRedirectTo;
        sFinishCurrentActivity = finishCurrentActivity;
        return sInstance;
    }

    public static ErrorDialogFrag getInstance() {
        return sInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.error_dialogfragment, null);

        TextView textErrorMessage = (TextView) v
                .findViewById(R.id.txt_validationdialog_error_message);
        textErrorMessage.setText(mTitle);

        v.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });

        return v;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        redirectToActivity();
    }

    private void redirectToActivity() {

        if (sCurrentActivity != null && sActivityToRedirectTo != null) {
            Intent intent = new Intent(sCurrentActivity, sActivityToRedirectTo);
            sCurrentActivity.startActivity(intent);
        }

        if (sFinishCurrentActivity) {
            sCurrentActivity.finish();
        }

    }
}