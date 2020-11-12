package org.androidtown.covid19center.Map;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import org.androidtown.covid19center.DataBase.AppDatabase;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.List.ClinicItem;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    MapFragment mapFragment;
    private static final int LOCATTION_PERMISSION_REQUEST_CODE = 1000;
    private AppDatabase db;
    private ArrayList<ClinicItem> clinicDataList; // 진료소 담을 리스트 생성
    private int number;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        set();
        setContentView(R.layout.fragment_map);
        FragmentManager fm = getSupportFragmentManager();
        mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        Log.d("테스트","4");

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
            Log.d("테스트","6");
        }
        Log.d("테스트","5");
        locationSource = new FusedLocationSource(this, LOCATTION_PERMISSION_REQUEST_CODE);
        mapFragment.getMapAsync(MapActivity.this);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        Log.d("테스트","2");
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                Log.d("테스트","1");
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d("테스트","3");
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
        List<Marker> markers = new ArrayList<>();
        InfoWindow infoWindow = new InfoWindow();

        db = AppDatabase.getInstance(getBaseContext());

        clinicDataList = new ArrayList<ClinicItem>();

        number = 0;

        db.clinicDao().getAll().observe(this, clinics -> {

            for (int i = 0; i < clinics.size() - 1; i++) {
                Marker marker = new Marker();
                //clinicDataList.add(new ClinicItem(clinics.get(i).getClinicName(),clinics.get(i).getClinicCallNumber(), clinics.get(i).getClinicAddress(), clinics.get(i).getX(), clinics.get(i).getY()));
                marker.setTag(clinics.get(i).getClinicName());
                marker.setWidth(50);
                marker.setHeight(80);
                marker.setPosition(new LatLng(Double.parseDouble(clinics.get(i).getY()), Double.parseDouble(clinics.get(i).getX())));
                marker.setOnClickListener(overlay ->{
                    infoWindow.open(marker);
                    return true;
                });
                Log.d("순서", String.valueOf(Double.parseDouble(clinics.get(i).getY())));

                markers.add(marker);
                number++;
            }

            Log.d("순서", String.valueOf(number));

            if (number == 616) {
                for (Marker marker : markers) {
                    marker.setMap(naverMap);
                }
            }
        });


        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getBaseContext()) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return (CharSequence)infoWindow.getMarker().getTag();
            }
        });

    }

    private void set(){

    }
}
