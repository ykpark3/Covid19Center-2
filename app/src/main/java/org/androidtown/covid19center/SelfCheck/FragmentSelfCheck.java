package org.androidtown.covid19center.SelfCheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import org.androidtown.covid19center.R;

public class FragmentSelfCheck extends Fragment {

    private View view;

    private CheckBox fever;
    private CheckBox muscle_ache;
    private CheckBox cough;
    private CheckBox sputum;
    private CheckBox chill;
    private CheckBox dyspnea;
    private CheckBox sore_throat;

    int check_num = 0;  //자가진단 체크한 증상 개수

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_self_check,container,false);
//        view = inflater.inflate(R.layout.activity_questionnaire,container,false);

        fever = view.findViewById(R.id.self_check_fever);
        muscle_ache = view.findViewById(R.id.self_check_muscle_ache);
        cough = view.findViewById(R.id.self_check_cough);
        sputum = view.findViewById(R.id.self_check_sputum);
        chill = view.findViewById(R.id.self_check_chill);
        dyspnea = view.findViewById(R.id.self_check_dyspnea);
        sore_throat = view.findViewById(R.id.self_check_sore_throat);

        return view;
    }
}