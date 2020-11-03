package org.androidtown.covid19center.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.androidtown.covid19center.DataBase.AppDatabase;
import org.androidtown.covid19center.DataBase.Clinic;
import org.androidtown.covid19center.DataBase.ClinicDAO;
import org.androidtown.covid19center.Mypage.FragmentMypage;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.FragmentSearch;
import org.androidtown.covid19center.SelfCheck.FragmentSelfCheck;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static final int FRAGMENT_SEARCH = 0;
    static final int FRAGMENT_SELF_CHECK = 1;
    static final int FRAGMENT_MYPAGE = 2;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentSearch fragmentSearch;
    private FragmentMypage fragmentMypage;
    private FragmentSelfCheck fragmentSelfCheck;
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private String inputStream;
    private String[] change;
    private String[][] token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigation();

        // 초기화면 검색화면으로 설정
        setFragment(FRAGMENT_SEARCH);
        token = new String[630][];

        // db저장



        try {
            inputStream = readText("clinics.txt");
            change = inputStream.split("\\n");
            divideComma(change);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readText(String file) throws IOException {
        InputStream is = getAssets().open(file);

        int size = is.available();

        byte[] buffer = new byte[size];
        BufferedReader read;
        is.read(buffer);
        is.close();

        String text = new String(buffer);
        return text;
    }

    private void divideComma(String[] line){
        for(int i=0; i<line.length; i++){
            line[i] = line[i].replaceAll("\t",",");
            token[i] = line[i].split(",");

        }
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
                        setFragment(FRAGMENT_SEARCH);
                        break;
                    case R.id.action_self_check:
                        setFragment(FRAGMENT_SELF_CHECK);
                        break;
                    case R.id.action_mypage:
                        setFragment(FRAGMENT_MYPAGE);
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
    public void setFragment(int fragmentNumber){

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (fragmentNumber){

            case FRAGMENT_SEARCH:
                fragmentTransaction.replace(R.id.Main_Frame, fragmentSearch);
                fragmentTransaction.commit();
                break;

            case FRAGMENT_SELF_CHECK:
                fragmentTransaction.replace(R.id.Main_Frame, fragmentSelfCheck);
                fragmentTransaction.commit();
                break;

            case FRAGMENT_MYPAGE:
                fragmentTransaction.replace(R.id.Main_Frame, fragmentMypage);
                fragmentTransaction.commit();
                break;
        }
    }


}