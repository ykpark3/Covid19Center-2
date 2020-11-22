package org.androidtown.covid19center.Search;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import org.androidtown.covid19center.DataBase.AppDatabase;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.List.ClinicItem;

import java.util.ArrayList;

public class ClinicActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATTION_PERMISSION_REQUEST_CODE = 1000;
    MapFragment mapFragment;
    private AppDatabase db;
    private ArrayList<ClinicItem> clinicDataList; // 진료소 담을 리스트 생성
    private int number;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private Button button;
    private ImageButton callButton;
    private ImageButton backButton;
    private double x;
    private double y;
    private String[] clinicInfo;
    private String callNumber;

    private String clinicNameTmp;
    private TextView clinicName;
    private TextView clinicAddress;
    private TextView clinicCallNumber;
    private String stringTemp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setContentView(R.layout.activity_clinic);
        setLayoutElement();
        setIntentInfomation();

        FragmentManager fm = getSupportFragmentManager();
        mapFragment = (MapFragment) fm.findFragmentById(R.id.childMap);

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().replace(R.id.childMap, mapFragment).commit();
        }

        locationSource = new FusedLocationSource(this, LOCATTION_PERMISSION_REQUEST_CODE);
        mapFragment.getMapAsync(ClinicActivity.this);

        // 전화번호 거는 코드
        callButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + callNumber));
                startActivity(intent);
            }
        });

        // 예약 페이지 넘어가는 코드
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReservationActivity.class);

                intent.putExtra("clinicName", clinicNameTmp);

                startActivity(intent);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(y, x));
        naverMap.moveCamera(cameraUpdate);

        Marker marker = new Marker();
        marker.setWidth(40);
        marker.setHeight(60);
        marker.setPosition(new LatLng(y, x));

        marker.setMap(naverMap);

    }

    private void setLayoutElement() {
        clinicName = findViewById(R.id.text_clinicName);
        clinicAddress = findViewById(R.id.text_clinic_address);
        clinicCallNumber = findViewById(R.id.text_clinic_call_number);
        button = findViewById(R.id.button_book);
        callButton = findViewById(R.id.callButton);
        backButton = findViewById(R.id.clinicInfo_backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setIntentInfomation() {
        Intent intent = getIntent(); // 데이터 수신

        stringTemp = intent.getExtras().getString("clinicName");
        clinicName.setText(stringTemp);
        clinicNameTmp = stringTemp;

        stringTemp = intent.getExtras().getString("clinicAddress");
        clinicAddress.setText(stringTemp);

        stringTemp = intent.getExtras().getString("clinicCallNumber");
        callNumber = stringTemp;


        Log.d("test", callNumber);

        clinicCallNumber.setText(stringTemp);

        stringTemp = intent.getExtras().getString("clinicX");
        x = Double.parseDouble(stringTemp);

        stringTemp = intent.getExtras().getString("clinicY");
        y = Double.parseDouble(stringTemp);

    }

}
