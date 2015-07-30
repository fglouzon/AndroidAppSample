// Generated code from Butter Knife. Do not modify!
package net.glouz.myapp.view.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ProductsWishlistFragment$$ViewInjector<T extends net.glouz.myapp.view.fragment.ProductsWishlistFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558573, "field 'mWishlistGridView'");
    target.mWishlistGridView = finder.castView(view, 2131558573, "field 'mWishlistGridView'");
  }

  @Override public void reset(T target) {
    target.mWishlistGridView = null;
  }
}
