// Generated code from Butter Knife. Do not modify!
package net.glouz.myapp.model.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ProductsGridviewAdapter$DrawerMenuViewHolder$$ViewInjector<T extends net.glouz.myapp.model.adapter.ProductsGridviewAdapter.DrawerMenuViewHolder> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558522, "field 'mPictureImageView' and method 'pictureImageViewClicked'");
    target.mPictureImageView = finder.castView(view, 2131558522, "field 'mPictureImageView'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.pictureImageViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558523, "field 'mPriceTextview'");
    target.mPriceTextview = finder.castView(view, 2131558523, "field 'mPriceTextview'");
    view = finder.findRequiredView(source, 2131558524, "field 'mWishlistProductGridview' and method 'wishlistProductGridviewClicked'");
    target.mWishlistProductGridview = finder.castView(view, 2131558524, "field 'mWishlistProductGridview'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.wishlistProductGridviewClicked(p0);
        }
      });
  }

  @Override public void reset(T target) {
    target.mPictureImageView = null;
    target.mPriceTextview = null;
    target.mWishlistProductGridview = null;
  }
}
