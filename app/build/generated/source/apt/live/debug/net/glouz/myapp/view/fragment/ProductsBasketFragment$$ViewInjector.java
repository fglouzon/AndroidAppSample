// Generated code from Butter Knife. Do not modify!
package net.glouz.myapp.view.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ProductsBasketFragment$$ViewInjector<T extends net.glouz.myapp.view.fragment.ProductsBasketFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230829, "field 'mproductBaGridView'");
    target.mproductBaGridView = finder.castView(view, 2131230829, "field 'mproductBaGridView'");
  }

  @Override public void reset(T target) {
    target.mproductBaGridView = null;
  }
}
