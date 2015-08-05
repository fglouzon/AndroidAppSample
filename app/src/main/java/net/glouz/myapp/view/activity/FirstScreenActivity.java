package net.glouz.myapp.view.activity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.glouz.myapp.R;
import net.glouz.myapp.view.fragment.FirstScreenFragment;

/**
 * Activity example.
 */
public class FirstScreenActivity extends BaseActivity {

    private FirstScreenFragment mFirstScreenFragment = new FirstScreenFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            mFirstScreenFragment = FirstScreenFragment.newInstance();
            mFragmentTransaction.add(R.id.first_screen_activity_container, mFirstScreenFragment, FirstScreenFragment.TAG);
            mFragmentTransaction.commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    void setContentView() {
        setContentView(R.layout.first_screen_activity);
    }

    @Override
    boolean showNavigationDrawer() {
        return false;
    }

    @Override
    void onNavigationDrawerOpened() {

    }

    @Override
    void onNavigationDrawerClosed() {

    }

    @Override
    void initialiseObjects() {

    }

    @Override
    void initialiseViews() {

    }

    @Override
    void updateViews() {

    }

    @Override
    void updateData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
