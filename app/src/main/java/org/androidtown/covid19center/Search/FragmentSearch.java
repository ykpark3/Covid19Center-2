package org.androidtown.covid19center.Search;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.L;
import com.google.android.gms.location.LocationListener;
import com.naver.maps.map.MapFragment;

import org.androidtown.covid19center.Main.MainActivity;
import org.androidtown.covid19center.Map.LocationConsts;
import org.androidtown.covid19center.Map.MapActivity;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.List.SearchActivity;

public class FragmentSearch extends Fragment {

    MapFragment mapFragment;
    LocationManager locationManager;
    private View view;
    private TextView search_textView;
    private Button openApiBtn;
    private View.OnKeyListener mOnKeyBackPressedListener;
    private LocationManager mLocMan; // 위치 관리자

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        mLocMan = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        return view;
    }

    @Nullable
    @Override
    public void onStart() {

        super.onStart();

        search_textView = view.findViewById(R.id.searchBox);
        openApiBtn = view.findViewById(R.id.openApiButton);



        setSearchingBox();


        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        openApiBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setSearchingBox() {
        search_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocation();
            }
        });
    }

    private void setLocation() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GPSListener());
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new GPSListener());

        } else {

            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


            Log.d("0435", String.valueOf(location));

            if(location == null){

                Log.d("0431", "124");

                if(!mLocMan.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    new AlertDialog.Builder(getContext()).setTitle("GPS 설정").setMessage("GPS가 꺼져 있습니다. \n원할한 서비스를 이용을 위해 GPS를 활성화 하시겠습니까?").setPositiveButton("GPS 켜기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(gpsOptionsIntent);
                        }
                    }).setNegativeButton("GPS 켜지 않기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LocationConsts.NOW_X = 126.924;
                            LocationConsts.NOW_Y = 37.516;
                            Toast.makeText(getActivity().getApplicationContext(), "위치 설정이 꺼져있습니다. 원활한 서비스 이용을 위해 위치 서비스를 켜주시길 바랍니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), SearchActivity.class);
                            startActivity(intent);
                        }
                    }).create().show();

                }

            } else{
                LocationConsts.NOW_X = location.getLongitude();
                LocationConsts.NOW_Y = location.getLatitude();
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        }
    }
}