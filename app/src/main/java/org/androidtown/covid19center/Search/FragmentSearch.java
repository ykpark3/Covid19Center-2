package org.androidtown.covid19center.Search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;

import org.androidtown.covid19center.DataBase.AppDatabase;
import org.androidtown.covid19center.Map.FragmentMap;
import org.androidtown.covid19center.Mypage.FragmentMypage;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.List.SearchActivity;

import java.util.Map;

public class FragmentSearch extends Fragment implements OnMapReadyCallback {

    private View view;
    private TextView search_textView;
    private Button openApiBtn;

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

        openApiBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {

                getActivity().setContentView(R.layout.fragment_map);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map_fragment);

                if (mapFragment == null) {
                    mapFragment = MapFragment.newInstance();
                    fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
                }

                mapFragment.getMapAsync(FragmentSearch.this);
            }
        });
    }

    public void setSearchingBox(){
        search_textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

    }
}