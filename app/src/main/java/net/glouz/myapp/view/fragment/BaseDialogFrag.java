package net.glouz.myapp.view.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import net.glouz.myapp.R;

/**
 * @author glouzonf
 */
public abstract class BaseDialogFrag extends DialogFragment {

	public static final int TRANSPARENT_BACKGROUND = 0;
	public static final int OPAQUE_BACKGROUND = 1;
	protected Dialog mDialog;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDialog = super.onCreateDialog(savedInstanceState);
		setDialogProperties(mDialog);
		return mDialog;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setRetainInstance(true);
	}

	protected Dialog setDialogProperties(Dialog dialog) {
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				getActivity().getResources().getDrawable(
						R.drawable.background_static_white_error));
		dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
		
		setDialogLayoutProperties(TRANSPARENT_BACKGROUND);
		return dialog;
	}
	
	/**
	 * This makes the dialog take up the full width.
	 * 
	 * @param dimAmount // Dim level. 0.0 (TRANSPARENT_BACKGROUND) - no dim, 1.0(OPAQUE_BACKGROUND)
	 * @return
	 */
	protected Dialog setDialogLayoutProperties(float dimAmount) {

		WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		lp.dimAmount = dimAmount;
		mDialog.getWindow().setAttributes(lp);
		return mDialog;
	}

    @Override
    public void show(FragmentManager manager, String tag) {
        // Avoids having multiple time the same error
        // TODO the tag used to show the error should be the error displayed.
        // This will make it possible to show multiple errors if they are different

        if (manager.findFragmentByTag(tag) == null) {
            super.show(manager, tag);
            manager.executePendingTransactions();
        }
    }

	@Override public void onDestroy() {
		super.onDestroy();
//		RefWatcher refWatcher = SampleApplication.getRefWatcher(getActivity());
//		refWatcher.watch(this);
	}
}