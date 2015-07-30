package net.glouz.myapp.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.glouz.myapp.R;
import net.glouz.myapp.event.ProductEvent;
import net.glouz.myapp.model.models.product.Product;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class ProductsGridviewAdapter extends ArrayAdapter<Product> {

    private LayoutInflater mInflater;
    private Context mContext;

    public ProductsGridviewAdapter(Context context, List<Product> data) {
        super(context, 0, data);
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerMenuViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_adapter_products_gridview, null);
            holder = new DrawerMenuViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DrawerMenuViewHolder) convertView.getTag();
        }

        holder.bind(mContext, getItem(position));

        return convertView;

    }

    static class DrawerMenuViewHolder {

        private Context mContext;
        private Product mItem;
        @InjectView(R.id.item_image_product_gridview)
        public ImageView mPictureImageView;
        @InjectView(R.id.item_price_product_gridview)
        public TextView mPriceTextview;
        @InjectView(R.id.wishlist_product_gridview)
        public ImageView mWishlistProductGridview;

        public DrawerMenuViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

        public void bind(Context context, Product item) {
            mContext = context;
            mItem = item;

            // Bind the data efficiently with the holder.
            if (item.currentPrice != null && !item.currentPrice.isEmpty()) {
                    mPriceTextview.setText(item.currentPrice);
                }

            if (item.productImageUrl != null && !item.productImageUrl.isEmpty()) {
                if (item.productImageUrl.iterator().next() != null){
                    Picasso.with(context).load(item.productImageUrl.iterator().next()).placeholder(R.drawable.place_holder_product_list).into(mPictureImageView);

                }
            }

            if (!item.wishlist) {
                Picasso.with(mContext).load(R.drawable.wishlist).into(mWishlistProductGridview);
            } else {
                Picasso.with(mContext).load(R.drawable.wishlist_saved).into(mWishlistProductGridview);
            }

        }

        @OnClick({R.id.item_image_product_gridview})
        public void pictureImageViewClicked(View view) {
            ProductEvent productEvent = new ProductEvent();
            productEvent.getProductDetails = true;
            productEvent.product = mItem;
            EventBus.getDefault().post(productEvent);
        }

        @OnClick({R.id.wishlist_product_gridview})
        public void wishlistProductGridviewClicked(View view) {
            if (!mItem.wishlist){
                Picasso.with(mContext).load(R.drawable.wishlist_saved).into(mWishlistProductGridview);
                mItem.wishlist = true;

                ProductEvent productEvent = new ProductEvent();
                productEvent.addToWishlist = true;
                productEvent.product = mItem;
                EventBus.getDefault().post(productEvent);
            } else {
                Picasso.with(mContext).load(R.drawable.wishlist).into(mWishlistProductGridview);
                mItem.wishlist = false;

                ProductEvent productEvent = new ProductEvent();
                productEvent.removeFromWishlist = true;
                productEvent.product = mItem;
                EventBus.getDefault().post(productEvent);
            }

        }
    }
}
