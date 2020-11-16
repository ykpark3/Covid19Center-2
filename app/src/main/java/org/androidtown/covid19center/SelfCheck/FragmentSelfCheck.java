package org.androidtown.covid19center.SelfCheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    public int check_num = 0;  //자가진단 체크한 증상 개수

    private OnCheckNumListener onCheckNumListener;

    private AppCompatActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_self_check, container, false);
        view = inflater.inflate(R.layout.activity_questionnaire,container,false);

        fever = view.findViewById(R.id.self_check_fever);
        muscle_ache = view.findViewById(R.id.self_check_muscle_ache);
        cough = view.findViewById(R.id.self_check_cough);
        sputum = view.findViewById(R.id.self_check_sputum);
        chill = view.findViewById(R.id.self_check_chill);
        dyspnea = view.findViewById(R.id.self_check_dyspnea);
        sore_throat = view.findViewById(R.id.self_check_sore_throat);

        submit = view.findViewById(R.id.self_check_submit);

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (fever.isChecked()) {
//                    check_num++;
//                }
//                if(muscle_ache.isChecked()){
//                    check_num++;
//                }
//                if(cough.isChecked()){
//                    check_num++;
//                }
//                if(sputum.isChecked()){
//                    check_num++;
//                }
//                if(chill.isChecked()){
//                    check_num++;
//                }
//                if(dyspnea.isChecked()){
//                    check_num++;
//                }
//                if(sore_throat.isChecked()){
//                    check_num++;
//                }
////                Toast.makeText(getContext(), "checknum : " + check_num, Toast.LENGTH_SHORT).show();
//
////                //자가진단표 체크한 개수 resultActivity에 전달
//                onCheckNumListener.onCheckNumSet(check_num);
////                ((OnCheckNumListener)activity).onCheckNumSet(check_num);
//
////                //자가진단 결과 액티비티 intent
//                Intent intent = new Intent(getActivity(), SelfCheckResultActivity.class);
//                intent.putExtra("checkNum", check_num);
//                startActivity(intent);
//
//            }
//        });

        return view;
    }

    public interface OnCheckNumListener{
        void onCheckNumSet(int check_num);
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        if (context instanceof OnCheckNumListener) {
//            onCheckNumListener = (OnCheckNumListener) context;
//
////            //자가진단 결과 액티비티 intent
////            Intent intent = new Intent(getActivity(), SelfCheckResultActivity.class);
////            startActivity(intent);
//        }
//
//        else {
//            throw new RuntimeException(context.toString() + " must implement OnCheckNumListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        onCheckNumListener = null;
//    }

    @Override
    public void onStart() {
        super.onStart();

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
//                Toast.makeText(getContext(), "checknum : " + check_num, Toast.LENGTH_SHORT).show();

//                //자가진단표 체크한 개수 resultActivity에 전달
//                onCheckNumListener.onCheckNumSet(check_num);
//                ((OnCheckNumListener)activity).onCheckNumSet(check_num);

                Log.d("checknum~~@@", String.valueOf(check_num));

//                //자가진단 결과 액티비티 intent
                Intent intent = new Intent(getActivity(), SelfCheckResultActivity.class);
                intent.putExtra("checkNum", check_num);
                startActivity(intent);

            }
        });
    }
}