package net.glouz.myapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.glouz.myapp.R;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;

/**
 * Created by glouzonf on 05/05/2015.
 * <p>
 * Base fragment that inflate the root layout of the current fragment and initialize the appropriate
 * related views.
 * <p>
 * The layout id has to be set in the constructor of the extending fragment
 * and the views declared using the butterknife annotations
 * (i.e @InjectView(R.id.button1) Button button1;)
 */
public abstract class BaseFragmentViewModel<T extends IView, S extends AbstractViewModel<T>> extends ViewModelBaseFragment<T, S> {

    protected EventBus mEventBus = EventBus.getDefault();

    protected int mLayoutId;
    protected View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setRetainInstance(true);

        if (mLayoutId != 0) {
            // Inflate the layout for this fragment
            mRootView = inflateRootView(mLayoutId, inflater, container, savedInstanceState);
        } else {
            throw new IllegalArgumentException(getString(R.string.fragment_layout_missing_exception, this.getClass().getSimpleName()));
        }

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.inject(this, mRootView);

        setModelView((T) this);

        initialiseObjects();
        initialiseViews();
        updateData();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mEventBus != null) {
            mEventBus.register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mEventBus != null) {
            mEventBus.unregister(this);
        }

    }

    abstract void initialiseObjects();

    //add listeners or callbacks if necessary
    abstract void initialiseViews();

    abstract void updateViews();

    abstract void updateData();

    private View inflateRootView(int layoutId, LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(layoutId, container, false);
    }

    public void setLayoutId(int layoutId) {
        this.mLayoutId = layoutId;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = SampleApplication.getRefWatcher(getActivity());
//        refWatcher.watch(this);
    }

}
