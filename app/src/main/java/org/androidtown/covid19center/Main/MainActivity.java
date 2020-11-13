package org.androidtown.covid19center.Main;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.naver.maps.map.NaverMapSdk;

import org.androidtown.covid19center.DataBase.AppDatabase;
import org.androidtown.covid19center.DataBase.Clinic;
import org.androidtown.covid19center.Map.LocationConsts;
import org.androidtown.covid19center.Mypage.FragmentMypage;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.FragmentSearch;
import org.androidtown.covid19center.Search.List.ClinicItem;
import org.androidtown.covid19center.SelfCheck.FragmentSelfCheck;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnBackPressedListener{

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
    private String address;
    ArrayList<ClinicItem> clinicDataList;
    private AppDatabase db;
    private long lastTimeBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigation();

        setFragment(FRAGMENT_SEARCH);

        // 메모장 저장
        saveData();


    }


    private void saveData(){
        token = new String[616][];
        clinicDataList = new ArrayList<ClinicItem>();

        db = AppDatabase.getInstance(getBaseContext());

        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient(NaverConsts.CLIENT_ID));

        // db저장
        try {
            inputStream = readText("clinics.txt");
            change = inputStream.split("\\n");
            divideComma(change);
        } catch (IOException e) {
            e.printStackTrace();
        }

        db.clinicDao().getAll().observe(this, clinics -> {
            if(clinics.isEmpty()){
                insertClinics();
            } else{
                Log.d("메모리", "비어있지않았음");
                Log.d("메모리", String.valueOf(clinics.size()));
            }
        });

//        // geocode 불러오는 코드
//        for(int i=0; i<token.length;i++)
//        {
//            address = token[i][1];
//
//            try {
//                Thread thread = new NaverApi(address);
//                thread.start();
//                thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }

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

    private void insertClinics(){

        for(int i=0;i<token.length;i++)
        {
            Log.d("태순",String.valueOf(i));
            db.clinicDao().insert(new Clinic(token[i][0], token[i][1],token[i][2], token[i][3], token[i][4]));
            clinicDataList.add(new ClinicItem(token[i][0], token[i][1],token[i][2], token[i][3], token[i][4]));
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



    @Override
    public void onBackPressed(){

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        for(Fragment fragment : fragmentList){
            if(fragment instanceof OnBackPressedListener){
                ((OnBackPressedListener)fragment).onBackPressed();
                return;
            }
        }

        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }

        lastTimeBackPressed = System.currentTimeMillis();
        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
    }




}