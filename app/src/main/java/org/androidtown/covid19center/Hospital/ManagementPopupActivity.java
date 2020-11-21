package org.androidtown.covid19center.Hospital;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;

public class ManagementPopupActivity extends AppCompatActivity {

    Button ok_btn, cancel_btn;
    AppCompatActivity activity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_management_popup);

        ok_btn = (Button)findViewById(R.id.ok_btn);
        cancel_btn = (Button)findViewById(R.id.cancel_btn);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //@@@@@해야할것!!
                //해당 환자 visited값 true로 변경하고
                //버튼 비활성화 하기

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
