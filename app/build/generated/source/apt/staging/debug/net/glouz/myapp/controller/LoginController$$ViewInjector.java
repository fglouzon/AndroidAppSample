// Generated code from Butter Knife. Do not modify!
package net.glouz.myapp.controller;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class LoginController$$ViewInjector<T extends net.glouz.myapp.controller.LoginController> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558527, "method 'login'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login(finder.<android.widget.Button>castParam(p0, "doClick", 0, "login", 0));
        }
      });
  }

  @Override public void reset(T target) {
  }
}
