package com.example.tudienanhviet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.tudienanhviet.SearchView.BaseExampleFragment;
import com.example.tudienanhviet.SearchView.DBHelper;
import com.example.tudienanhviet.SearchView.SearchActivity;
import com.example.tudienanhviet.SearchView.SlidingSearchResultFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DBHelper db;
    Button btnDichVanBan, btnTuCuaBan, btnTuDaTra, btnDongTuBQT,btnKhoaHoc,btnThem;
   // EditText editText;
    FloatingSearchView mSearchView;
    private DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DBHelper(MainActivity.this);
        db.insertYourWords();
        db.insertHistories();
        navigationView=findViewById(R.id.nav_view_main);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.item_home:
                        return true;
                    case R.id.item_search:
                        Intent i = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.item_course:
                        Intent itent = new Intent(MainActivity.this, CoursesActivity.class);
                        startActivity(itent);
                        return true;
                    case R.id.item_history:
                        Intent it = new Intent(MainActivity.this, TranslateHistoryActivity.class);
                        startActivity(it);
                        return true;
                    case R.id.item_para:
                        Intent ite = new Intent(MainActivity.this, DichVanBanActivity.class);
                        startActivity(ite);
                        return true;
                    case R.id.item_yourwords:
                        Intent iten = new Intent(MainActivity.this, YourWordActivity.class);
                        startActivity(iten);
                        return true;
                    case R.id.item_exit:
                        finishAffinity();
                        return true;
                    default:
                        return true;
                }
            }
        });
        mDrawerLayout=findViewById(R.id.drawer_layout_main);
        mSearchView=findViewById(R.id.searchMain);
        //btnVietAnh = findViewById(R.id.btnTuDienVietAnh);
        btnDichVanBan = findViewById(R.id.btnDichVanBan);
        btnTuCuaBan = findViewById(R.id.btnTuCuaBan);
        btnTuDaTra = findViewById(R.id.btnTuDaTra);
        btnDongTuBQT = findViewById(R.id.btnDongTuBQT);
        btnKhoaHoc= findViewById(R.id.btnKhoaHoc);
//        btnVietAnh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, VietAnhActivity.class);
//                startActivity(intent);
//            }
//        });
        btnDichVanBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(MainActivity.this, TranslateParagraphActivity.class);
                Intent intent = new Intent(MainActivity.this, DichVanBanActivity.class);
                startActivity(intent);
            }
        });
        btnTuCuaBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YourWordActivity.class);
                startActivity(intent);
            }
        });
        btnTuDaTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TranslateHistoryActivity.class);
                startActivity(intent);
            }
        });
        btnDongTuBQT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IrregularVerbsActivity.class);
                startActivity(intent);
            }
        });
        btnKhoaHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CoursesActivity.class);
                startActivity(intent);
            }
        });

        mSearchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
            @Override
            public void onMenuOpened() {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }

            @Override
            public void onMenuClosed() {
                mDrawerLayout.closeDrawers();
            }
        });
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_change_colors) {
                    Intent i = new Intent(MainActivity.this,SearchActivity.class);
                    startActivity(i);

                } else {
                    Intent i = new Intent(MainActivity.this,SearchActivity.class);
                    startActivity(i);
                }

            }
        });
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.sliding_list_example:
//                return true;
//            case R.id.sliding_search_bar_example:
//                Intent i = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(i);
//                return true;
//            case R.id.item_course:
//                Intent itent = new Intent(MainActivity.this, CoursesActivity.class);
//                startActivity(itent);
//                return true;
//            case R.id.item_history:
//                Intent it = new Intent(MainActivity.this, TranslateHistoryActivity.class);
//                startActivity(it);
//                return true;
//            case R.id.item_para:
//                Intent ite = new Intent(MainActivity.this, DichVanBanActivity.class);
//                startActivity(ite);
//                return true;
//            case R.id.item_yourwords:
//                Intent iten = new Intent(MainActivity.this, YourWordActivity.class);
//                startActivity(iten);
//                return true;
//            default:
//                return true;
//        }
//
//    }
}