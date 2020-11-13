package org.androidtown.covid19center.SelfCheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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
    private Button submit;

    int check_num = 0;  //자가진단 체크한 증상 개수

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_self_check, container, false);
//        view = inflater.inflate(R.layout.activity_questionnaire,container,false);

        fever = view.findViewById(R.id.self_check_fever);
        muscle_ache = view.findViewById(R.id.self_check_muscle_ache);
        cough = view.findViewById(R.id.self_check_cough);
        sputum = view.findViewById(R.id.self_check_sputum);
        chill = view.findViewById(R.id.self_check_chill);
        dyspnea = view.findViewById(R.id.self_check_dyspnea);
        sore_throat = view.findViewById(R.id.self_check_sore_throat);

        submit = view.findViewById(R.id.self_check_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fever.isChecked()) {
                    check_num++;
                }
                if(muscle_ache.isChecked()){
                    check_num++;
                }
                if(cough.isChecked()){
                    check_num++;
                }
                if(sputum.isChecked()){
                    check_num++;
                }
                if(chill.isChecked()){
                    check_num++;
                }
                if(dyspnea.isChecked()){
                    check_num++;
                }
                if(sore_throat.isChecked()){
                    check_num++;
                }
                Toast.makeText(getContext(), "checknum : " + check_num, Toast.LENGTH_SHORT).show();

                //자가진단 결과 액티비티 intent
                Intent intent = new Intent(getActivity(), SelfCheckResultActivity.class);
                startActivity(intent);
            }
        });


//        //이거 안쓸거임 버려 개구려..
//        fever.setOnCheckedChangeListener(this);
//        muscle_ache.setOnCheckedChangeListener(this);
//        cough.setOnCheckedChangeListener(this);
//        sputum.setOnCheckedChangeListener(this);
//        chill.setOnCheckedChangeListener(this);
//        dyspnea.setOnCheckedChangeListener(this);
//        sore_throat.setOnCheckedChangeListener(this);

//        LinearLayout layout = view.findViewById(R.id.self_check_layout);
//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onCheckBoxClicked(v);
//            }
//        });

        return view;
    }

}