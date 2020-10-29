package org.androidtown.covid19center.Search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidtown.covid19center.R;

public class FragmentSearch extends Fragment {

    private View view;
    private TextView search_textView;



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
        super.onStart();
        setSearchingBox();
    }


    public void setSearchingBox(){
        search_textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }

}