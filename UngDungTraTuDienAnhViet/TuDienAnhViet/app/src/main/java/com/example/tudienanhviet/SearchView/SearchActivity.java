package com.example.tudienanhviet.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.tudienanhviet.CoursesActivity;
import com.example.tudienanhviet.DichVanBanActivity;
import com.example.tudienanhviet.R;
import com.example.tudienanhviet.TranslateHistoryActivity;
import com.example.tudienanhviet.YourWordActivity;
import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements BaseExampleFragment.BaseExampleFragmentCallbacks, NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "SearchActivity";
    FrameLayout f;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        showFragment(new SlidingSearchResultFragment());
    }

    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
        searchView.attachNavigationDrawerToMenuButton(mDrawerLayout);
    }

    @Override
    public void onBackPressed() {
        List fragments = getSupportFragmentManager().getFragments();
        BaseExampleFragment currentFragment = (BaseExampleFragment) fragments.get(fragments.size() - 1);

        if (!currentFragment.onActivityBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (menuItem.getItemId()) {
            case R.id.item_home:
                this.finish();
                return true;
            case R.id.item_search:
                //showFragment(new SlidingSearchResultFragment());
                return true;
            case R.id.item_course:
                Intent i = new Intent(SearchActivity.this, CoursesActivity.class);
                startActivity(i);
                return true;
            case R.id.item_history:
                Intent it = new Intent(SearchActivity.this, TranslateHistoryActivity.class);
                startActivity(it);
                return true;
            case R.id.item_para:
                Intent ite = new Intent(SearchActivity.this, DichVanBanActivity.class);
                startActivity(ite);
                return true;
            case R.id.item_yourwords:
                Intent iten = new Intent(SearchActivity.this, YourWordActivity.class);
                startActivity(iten);
                return true;
            case R.id.item_exit:
                this.finishAffinity();
                return true;
            default:
                return true;
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_content, fragment)
                .addToBackStack(null).commit();
    }


}