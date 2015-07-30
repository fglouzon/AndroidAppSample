// Generated code from Butter Knife. Do not modify!
package net.glouz.myapp.view.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ProductDetailsFragment$$ViewInjector<T extends net.glouz.myapp.view.fragment.ProductDetailsFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558556, "field 'mBrandProductDetails'");
    target.mBrandProductDetails = finder.castView(view, 2131558556, "field 'mBrandProductDetails'");
    view = finder.findRequiredView(source, 2131558557, "field 'mviewPager'");
    target.mviewPager = finder.castView(view, 2131558557, "field 'mviewPager'");
    view = finder.findRequiredView(source, 2131558558, "field 'mIndicator'");
    target.mIndicator = finder.castView(view, 2131558558, "field 'mIndicator'");
    view = finder.findRequiredView(source, 2131558559, "field 'mDescriptionProductDetails'");
    target.mDescriptionProductDetails = finder.castView(view, 2131558559, "field 'mDescriptionProductDetails'");
    view = finder.findRequiredView(source, 2131558560, "field 'mAddToCartProductDetails'");
    target.mAddToCartProductDetails = finder.castView(view, 2131558560, "field 'mAddToCartProductDetails'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.addToCartProductDetailsClicked(p0);
        }
      });
  }

  @Override public void reset(T target) {
    target.mBrandProductDetails = null;
    target.mviewPager = null;
    target.mIndicator = null;
    target.mDescriptionProductDetails = null;
    target.mAddToCartProductDetails = null;
  }
}
