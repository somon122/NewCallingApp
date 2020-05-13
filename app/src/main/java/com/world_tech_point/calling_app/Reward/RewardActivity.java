package com.world_tech_point.calling_app.Reward;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.world_tech_point.calling_app.CallingDetails.MainActivity;
import com.world_tech_point.calling_app.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RewardActivity extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){

                    case R.id.nav_home:

                        Toast.makeText(RewardActivity.this, "nav_home", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.nav_gallery:

                        Toast.makeText(RewardActivity.this, "nav_gallery", Toast.LENGTH_SHORT).show();

                        break;

                     case R.id.nav_slideshow:

                         Toast.makeText(RewardActivity.this, "nav_slideshow", Toast.LENGTH_SHORT).show();

                        break;



                }



                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


                return false;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            exitsAlert();
        } else {
            exitsAlert();
        }

    }

    private void exitsAlert() {

        startActivity(new Intent(RewardActivity.this, MainActivity.class));
        finish();

    }
}
