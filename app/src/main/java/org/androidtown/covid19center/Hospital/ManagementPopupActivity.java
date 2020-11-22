package org.androidtown.covid19center.Hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Server.ReservationVO;

import java.util.ArrayList;

public class ManagementPopupActivity extends AppCompatActivity {

    Button ok_btn, cancel_btn;
    AppCompatActivity activity = this;
    private String ques_seq;  //예약 구분 시퀀스
    private int position; //리스트 인덱스
    private ArrayList<ReservationVO> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_management_popup);

        Intent intent = getIntent();
        ques_seq = intent.getExtras().getString("questionnaire_seq");
        position = intent.getExtras().getInt("position");
        list = (ArrayList<ReservationVO>)intent.getSerializableExtra("reservationList");

        ok_btn = (Button)findViewById(R.id.ok_btn);
        cancel_btn = (Button)findViewById(R.id.cancel_btn);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //@@@@@해야할것!!
                //해당 환자 visited값 true로 변경하고
                //버튼 비활성화 하기
                list.get(position).setVisited(true);

                Intent intent2 = new Intent(activity, ReservationManagementActivity.class);
                intent2.putExtra("modifiedList", list);
                //startActivity(intent2);

                Toast.makeText(activity, "진료 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
