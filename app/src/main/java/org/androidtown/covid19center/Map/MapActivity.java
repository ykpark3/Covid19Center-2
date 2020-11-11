package org.androidtown.covid19center.Map;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

import org.androidtown.covid19center.DataBase.AppDatabase;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.List.ClinicItem;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    MapFragment mapFragment;
    private AppDatabase db;
    private ArrayList<ClinicItem> clinicDataList; // 진료소 담을 리스트 생성
    private int number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        set();
        setContentView(R.layout.fragment_map);
        FragmentManager fm = getSupportFragmentManager();
        mapFragment = (MapFragment) fm.findFragmentById(R.id.map);

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(MapActivity.this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        List<Marker> markers = new ArrayList<>();

        db = AppDatabase.getInstance(getBaseContext());

        clinicDataList = new ArrayList<ClinicItem>();

        number = 0;

        db.clinicDao().getAll().observe(this, clinics -> {

            for(int i=0; i<616;i++)
            {
                Marker marker = new Marker();
                //clinicDataList.add(new ClinicItem(clinics.get(i).getClinicName(),clinics.get(i).getClinicCallNumber(), clinics.get(i).getClinicAddress(), clinics.get(i).getX(), clinics.get(i).getY()));
                marker.setPosition(new LatLng(Double.parseDouble(clinics.get(i).getY()), Double.parseDouble(clinics.get(i).getX())));
                Log.d("순서", String.valueOf(Double.parseDouble(clinics.get(i).getY())));
                markers.add(marker);

                number++;
            }
            Log.d("순서",String.valueOf(number));
            if(number == 616){
                for(Marker marker :markers){
                    marker.setMap(naverMap);
                }
            }

        });


//        for(int i=0; i<clinicDataList.size();i++){
//            marker.setPosition(new LatLng(Double.parseDouble(clinicDataList.get(i).getX()), Double.parseDouble(clinicDataList.get(i).getY())));
//        }
//


    }

    private void set(){

    }
}
