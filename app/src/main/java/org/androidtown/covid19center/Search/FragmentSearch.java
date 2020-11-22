package org.androidtown.covid19center.Search;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.naver.maps.map.MapFragment;

import org.androidtown.covid19center.Map.LocationConsts;
import org.androidtown.covid19center.Map.MapActivity;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.List.SearchActivity;

public class FragmentSearch extends Fragment{

    private View view;
    private TextView search_textView;
    private Button openApiBtn;
    MapFragment mapFragment;
    private View.OnKeyListener mOnKeyBackPressedListener;
    LocationManager locationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_search,container,false);

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

    public void setSearchingBox(){
        search_textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setLocation();
            }
        });
    }

    private void setLocation(){
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this.getActivity(), new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    0 );
        }
        else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            LocationConsts.NOW_X = location.getLongitude();
            LocationConsts.NOW_Y = location.getLatitude();
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        }
    }
}