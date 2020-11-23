package org.androidtown.covid19center.SelfCheck;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class QuestionnarieActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private EditText countryEditText;
    private TextView entranceDateTextView;
    private EditText relationEditText;
    private TextView termTextView;
    private TextView startVirusDateTextView;
    private final Calendar myCalender = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        setLayoutElement();

    }


    private void setLayoutElement(){
        countryEditText = findViewById(R.id.questionnarie_editText_country);
        entranceDateTextView = findViewById(R.id.questionnarie_textView_date);
        relationEditText = findViewById(R.id.questionnarie_editText_relation);
        termTextView = findViewById(R.id.questionnarie_textView_term);
        startVirusDateTextView = findViewById(R.id.questionnarie_textView_start_date);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR,year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        DatePickerDialog.OnDateSetListener virusStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR,year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateVirusStartLabel();
            }
        };


        entranceDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(QuestionnarieActivity.this, date, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        termTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNumberPicker(v, "접촉기간", "1", 30, 0,1, 1);
            }
        });

        startVirusDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(QuestionnarieActivity.this, virusStartDate, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel(){
        String myFormat = "yy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        entranceDateTextView.setText(sdf.format(myCalender.getTime()));
        entranceDateTextView.setTextColor(Color.BLACK);
    }

    private void updateVirusStartLabel(){
        String myFormat = "yy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        startVirusDateTextView.setText(sdf.format(myCalender.getTime()));
        startVirusDateTextView.setTextColor(Color.BLACK);
    }



    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        termTextView.setText(String.valueOf(newVal)+ "일");
        termTextView.setTextColor(Color.BLACK);
    }

    public void showNumberPicker(View view, String title, String subtitle, int maxvalue, int minvalue, int step, int defvalue){
        NumberpickerDialog newFragment = new NumberpickerDialog();

        //Dialog에는 bundle을 이용해서 파라미터를 전달한다
        Bundle bundle = new Bundle(6); // 파라미터는 전달할 데이터 개수
        bundle.putString("title", title); // key , value
        bundle.putString("subtitle", subtitle); // key , value
        bundle.putInt("maxvalue", maxvalue); // key , value
        bundle.putInt("minvalue", minvalue); // key , value
        bundle.putInt("step", step); // key , value
        bundle.putInt("defvalue", defvalue); // key , value
        newFragment.setArguments(bundle);
        //class 자신을 Listener로 설정한다
        newFragment.setValueChangeListener(this);
        newFragment.show(getSupportFragmentManager(), "null");
    }


}
