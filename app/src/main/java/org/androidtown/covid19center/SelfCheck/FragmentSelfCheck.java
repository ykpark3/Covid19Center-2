package org.androidtown.covid19center.SelfCheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidtown.covid19center.R;

public class FragmentSelfCheck extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
//        view = inflater.inflate(R.layout.fragment_self_check,container,false);
        view = inflater.inflate(R.layout.activity_questionnaire,container,false);
        return view;
    }
}