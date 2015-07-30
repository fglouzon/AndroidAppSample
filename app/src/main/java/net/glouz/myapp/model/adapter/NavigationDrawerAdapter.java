package net.glouz.myapp.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.glouz.myapp.R;
import net.glouz.myapp.model.models.product.ProductCategory;

import java.util.List;

public class NavigationDrawerAdapter extends ArrayAdapter<ProductCategory> {

	private LayoutInflater mInflater;

	public NavigationDrawerAdapter(Context context, List<ProductCategory> data) {
		super(context, 0, data);
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		DrawerMenuViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_adapter_navigation_drawer, null);
			holder = new DrawerMenuViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (DrawerMenuViewHolder) convertView.getTag();
		}
		
		holder.bind(getItem(position));

		return convertView;

	}

	static class DrawerMenuViewHolder {

		private TextView mTextLabel;

		public DrawerMenuViewHolder(View view) {
			mTextLabel = (TextView) view.findViewById(R.id.item_name_navigation_drawer);
		}

		public void bind(ProductCategory item) {

			// Bind the data efficiently with the holder.
			mTextLabel.setText(item.name);


		}
	}


}
