package org.androidtown.covid19center.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.androidtown.covid19center.Mypage.FragmentMypage;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.FragmentSearch;
import org.androidtown.covid19center.SelfCheck.FragmentSelfCheck;

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

        // 초기화면 검색화면으로 설정
        fragmentManager = getSupportFragmentManager();
        setFragment(fragmentSearch);
    }


    // 바텀 네비게이션 설정
    public void setBottomNavigation(){
        bottomNavigationView = findViewById(R.id.bottomNavi);

        // 프레그먼트 선택 시 이벤트 처리 함수
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_search:
                        setFragment(fragmentSearch);
                        break;
                    case R.id.action_self_check:
                        setFragment(fragmentSelfCheck);
                        break;
                    case R.id.action_mypage:
                        setFragment(fragmentMypage);
                        break;
                }

                return true;
            }
        });

        fragmentSearch = new FragmentSearch();
        fragmentSelfCheck = new FragmentSelfCheck();
        fragmentMypage = new FragmentMypage();
    }

    // 프레그먼트 변경 함수
    public void setFragment(Fragment fragment){

        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.Main_Frame, fragment);
        fragmentTransaction.commit();
    }

}