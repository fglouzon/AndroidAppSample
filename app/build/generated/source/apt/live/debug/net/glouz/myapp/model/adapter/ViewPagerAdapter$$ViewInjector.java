// Generated code from Butter Knife. Do not modify!
package net.glouz.myapp.model.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ViewPagerAdapter$$ViewInjector<T extends net.glouz.myapp.model.adapter.ViewPagerAdapter> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230815, "field 'mImageProduct'");
    target.mImageProduct = finder.castView(view, 2131230815, "field 'mImageProduct'");
  }

  @Override public void reset(T target) {
    target.mImageProduct = null;
  }
}
