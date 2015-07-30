// Generated code from Butter Knife. Do not modify!
package net.glouz.myapp.view.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class BaseActivity$$ViewInjector<T extends net.glouz.myapp.view.activity.BaseActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findOptionalView(source, 2131558536, null);
    target.mDrawerLayout = finder.castView(view, 2131558536, "field 'mDrawerLayout'");
    view = finder.findOptionalView(source, 2131558539, null);
    target.mNavigationDrawerContainer = finder.castView(view, 2131558539, "field 'mNavigationDrawerContainer'");
    view = finder.findOptionalView(source, 2131558537, null);
    target.mToolbar = finder.castView(view, 2131558537, "field 'mToolbar'");
    view = finder.findOptionalView(source, 2131558570, null);
    target.mBasketToolbarButton = finder.castView(view, 2131558570, "field 'mBasketToolbarButton'");
    if (view != null) {
      view.setOnClickListener(
        new butterknife.internal.DebouncingOnClickListener() {
          @Override public void doClick(
            android.view.View p0
          ) {
            target.basketToolbarButtonClicked(p0);
          }
        });
    }
    view = finder.findOptionalView(source, 2131558571, null);
    target.mWishlistToolbarButton = finder.castView(view, 2131558571, "field 'mWishlistToolbarButton'");
    if (view != null) {
      view.setOnClickListener(
        new butterknife.internal.DebouncingOnClickListener() {
          @Override public void doClick(
            android.view.View p0
          ) {
            target.wishlistToolbarClicked(p0);
          }
        });
    }
  }

  @Override public void reset(T target) {
    target.mDrawerLayout = null;
    target.mNavigationDrawerContainer = null;
    target.mToolbar = null;
    target.mBasketToolbarButton = null;
    target.mWishlistToolbarButton = null;
  }
}
