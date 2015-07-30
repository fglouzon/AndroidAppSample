// Generated code from Butter Knife. Do not modify!
package net.glouz.myapp.view.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ProductsForCategoryFragment$$ViewInjector<T extends net.glouz.myapp.view.fragment.ProductsForCategoryFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558563, "field 'mProductsProgressBar'");
    target.mProductsProgressBar = finder.castView(view, 2131558563, "field 'mProductsProgressBar'");
    view = finder.findRequiredView(source, 2131558564, "field 'mproductsGridView'");
    target.mproductsGridView = finder.castView(view, 2131558564, "field 'mproductsGridView'");
  }

  @Override public void reset(T target) {
    target.mProductsProgressBar = null;
    target.mproductsGridView = null;
  }
}
