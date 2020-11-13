package org.androidtown.covid19center.Search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;

import java.util.ArrayList;

public class ClinicActivity extends AppCompatActivity {


    private TextView clinicName;
    private TextView clinicAddress;
    private TextView clinicCallNumber;
    private String stringTemp;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);
        setLayoutElement();
        setIntentInfomation();
    }

    private void setLayoutElement(){
        clinicName = findViewById(R.id.text_clinicName);
        clinicAddress = findViewById(R.id.text_clinic_address);
        clinicCallNumber = findViewById(R.id.text_clinic_call_number);
        button = findViewById(R.id.button_book);
    }

    private void setIntentInfomation(){
        Intent intent = getIntent(); // 데이터 수신

        stringTemp = intent.getExtras().getString("clinicName");
        clinicName.setText(stringTemp);

        stringTemp = intent.getExtras().getString("clinicAddress");
        clinicAddress.setText(stringTemp);

        stringTemp = intent.getExtras().getString("clinicCallNumber");
        clinicCallNumber.setText(stringTemp);
    }


}
