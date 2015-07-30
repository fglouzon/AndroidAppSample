package net.glouz.myapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octo.android.robospice.SpiceManager;
import com.squareup.leakcanary.RefWatcher;

import net.glouz.myapp.SampleApplication;
import net.glouz.myapp.view.activity.BaseActivity;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by glouzonf on 05/05/2015.
 * <p/>
 * Base fragment that inflate the root layout of the current fragment and initialize the appropriate
 * related views.
 * <p/>
 * The layout id has to be set in the constructor of the extending fragment
 * and the views declared using the butterknife annotations
 * (i.e @InjectView(R.id.button1) Button button1;)
 */
public abstract class BaseFragment extends Fragment {

    protected EventBus mEventBus = EventBus.getDefault();

    protected SpiceManager mSpiceManager;

//    protected int mLayoutId;
    protected View mRootView;

    //initial presenter implementation
    //we want to use butterknife.findbyid instead of ButterKnife.inject
    //so basically in the fragment, oncreate view inflates the root view
    //so that we can pass it to the presenter which will hold the views updates logic
    protected boolean butterknifeInject = true;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        setRetainInstance(true);
//
//        if (mLayoutId != 0) {
//            // Inflate the layout for this fragment
//            mRootView = inflateRootView(mLayoutId, inflater, container, savedInstanceState);
//        } else {
//            throw new IllegalArgumentException(getString(R.string.fragment_layout_missing_exception, this.getClass().getSimpleName()));
//        }
//
//        return mRootView;
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (butterknifeInject) {
            ButterKnife.inject(this, mRootView);
        }

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

    abstract boolean butterknifeInject();

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

//    public void setLayoutId(int layoutId) {
//        this.mLayoutId = layoutId;
//    }

    protected SpiceManager getSpiceManager() {
        if (getActivity() != null && !getActivity().isFinishing()){
            mSpiceManager = ((BaseActivity)getActivity()).getSpiceManager();
        }
        return mSpiceManager;
    }

    @Override public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = SampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

}
