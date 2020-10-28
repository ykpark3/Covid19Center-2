package org.androidtown.covid19center;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentSearch fragmentSearch;
    private FragmentMypage fragmentMypage;
    private FragmentSelfCheck fragmentSelfCheck;
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigation();

    }


    // 바텀 네비게이션 설정 및 프레그먼트 변경

    public void setBottomNavigation(){
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_search:
                        break;
                    case R.id.action_self_check:
                        break;
                    case R.id.action_mypage:
                        break;
                }

                return true;
            }
        });

        fragmentSearch = new FragmentSearch();
        fragmentSelfCheck = new FragmentSelfCheck();
        fragmentMypage = new FragmentMypage();

    }


}