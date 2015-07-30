
package android.databinding;
import net.glouz.myapp.staging.test.BR;
class DataBinderMapper {
    final static int TARGET_MIN_SDK = 16;
    private final java.util.HashMap<String, Integer> mLayoutIds;
    public DataBinderMapper() {
        mLayoutIds = new java.util.HashMap<String, Integer>();
    }
    public android.databinding.ViewDataBinding getDataBinder(android.view.View view, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        Integer id = mLayoutIds.get(tag);
        if (id == null) {
            return 0;
        }
        return id;
    }
}