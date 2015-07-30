package net.glouz.myapp.model.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import net.glouz.myapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by glouzonf on 18/05/2015.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private String[] mImages;
    private LayoutInflater inflater;

    @InjectView(R.id.imageProduct)
    public ImageView mImageProduct;

    public ViewPagerAdapter(Context context, String[] images) {
        this.mContext = context;
        this.mImages = images;
    }

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

            inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.item_viewpager, container,
                    false);
            ButterKnife.inject(this, itemView);

            Picasso.with(mContext).load(mImages[position]).placeholder(R.drawable.place_holder_product_list).into(mImageProduct);

            // Add item_viewpager.xml to ViewPager
            container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        container.removeView((LinearLayout) object);

    }
}
