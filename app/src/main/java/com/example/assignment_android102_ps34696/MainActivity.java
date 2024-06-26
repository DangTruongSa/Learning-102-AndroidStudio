package com.example.assignment_android102_ps34696;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.assignment_android102_ps34696.dao.sanphamDAO;
import com.example.assignment_android102_ps34696.database.Dbhelper;
import com.example.assignment_android102_ps34696.fragment.Fragmentad;
import com.example.assignment_android102_ps34696.fragment.Fragmentview;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

private sanphamDAO sanphamDAO;
private Dbhelper Dbhelper;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drlu1);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nvgtv1);

        // setup toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_dehaze_24);

        //set fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lnlu1,new Fragmentview())
                        .commit();
        getSupportActionBar().setTitle("QUẢN LÝ SẢN PHẨM");

        //item navi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                if (item.getItemId() == R.id.mQLSP){
                    fragment = new Fragmentview();
                } else if (item.getItemId() == R.id.mGT) {
                    fragment = new Fragmentad();
                }else if (item.getItemId() == R.id.mDX) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }else {
                    fragment = new Fragmentview();
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lnlu1,fragment)
                        .commit();

                getSupportActionBar().setTitle(item.getTitle());

                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });












    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
}