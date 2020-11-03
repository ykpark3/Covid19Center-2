package org.androidtown.covid19center.Search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.androidtown.covid19center.DataBase.AppDatabase;
import org.androidtown.covid19center.Network.ApiExplorer;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.List.SearchActivity;

public class FragmentSearch extends Fragment {

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

        search_textView = view.findViewById(R.id.searchBox);
        openApiBtn = view.findViewById(R.id.openApiButton);
        super.onStart();
        setSearchingBox();
        openApiBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
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

}