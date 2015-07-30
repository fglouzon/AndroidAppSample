package net.glouz.myapp.view.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.glouz.myapp.R;

import org.apache.commons.lang3.StringUtils;

/**
 * @author glouzonf
 */
public class ProgressDialogSampleApp extends ProgressDialog {

	private TextView messageTextview;

	public ProgressDialogSampleApp(Context context, int theme) {
		super(context, theme);
	}

	public ProgressDialogSampleApp(Context context) {
		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog);
	}

	public void setText(String message) {
		messageTextview = (TextView) findViewById(R.id.progressMessage);
		messageTextview.setVisibility(View.VISIBLE);

		if (!StringUtils.isEmpty(message)) {
			messageTextview.setText(message);
		}
	}
}
