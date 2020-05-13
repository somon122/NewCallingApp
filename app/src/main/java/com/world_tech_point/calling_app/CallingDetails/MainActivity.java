package com.world_tech_point.calling_app.CallingDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.world_tech_point.calling_app.R;
import com.world_tech_point.calling_app.Reward.RewardActivity;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    TabLayout tabLayout;
    PageViewerAdapter pageViewerAdapter;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView_id);
        bottomNavigationView.setSelectedItemId(R.id.action_settings2);

        viewPager = findViewById(R.id.viewPager_id);
        tabLayout = findViewById(R.id.tabLayout_id);

        floatingActionButton = findViewById(R.id.dialerPad_id);

        tabLayout.addTab(tabLayout.newTab().setText("Recent"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacts"));
        tabLayout.addTab(tabLayout.newTab().setText("Friends"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Dialer coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        pageViewerAdapter = new PageViewerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageViewerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               int id = item.getItemId();
               switch (id){

                   case R.id.action_settings1:

                       Toast.makeText(MainActivity.this, "action_settings1", Toast.LENGTH_SHORT).show();

                       break;

                       case R.id.action_settings2:
                           Toast.makeText(MainActivity.this, "action_settings2", Toast.LENGTH_SHORT).show();
                       break;

                       case R.id.action_settings3:
                          startActivity(new Intent(MainActivity.this, RewardActivity.class));
                       break;

               }


               return true;
           }
       });

    }
}
