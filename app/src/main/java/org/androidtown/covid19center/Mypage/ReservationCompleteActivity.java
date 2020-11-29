package org.androidtown.covid19center.Mypage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;

public class ReservationCompleteActivity extends AppCompatActivity {

    private String name; // 예약자 이름
    private final String nameExplain = "예약자 : ";
    private String clinicDate; // 예약 날짜
    private final String clinicDateExplain = "예약 날짜 : ";
    private String clinicName; // 병원 이름
    private final String clinicNameExplain = "예약 병원 : ";
    private String clinicAddress; // 병원 주소
    private final String clinicAddressExplain = "병원 주소 : ";
    private String clinicCallNumber; // 병원 전화번호
    private final String clinicCallNumberExplain = "전화번호 : ";
    private boolean nationalCheck; // 1번 문항 트루 펄스
    private String nationalPlace; // 갔다온 장소
    private final String nationPlaceExplain = "방문국가/지역/장소 : ";
    private String nationalDate; // 갔다온 기간
    private final String nationalDateExplain = "입국 날짜 : ";
    private boolean contactCheck; // 2번 문항 트루 펄스
    private String contactRelationShip; // 본인과의 관계
    private final String contactRelationShipExplain = "본인과의 관계 : ";
    private String contactRelationDate; // 접촉 기간
    private final String relationDateExplain = "접촉 기간 : ";
    private boolean symptomCheck; // 증상
    private String symptomList; // 증상 리스트
    private final String symptomListExplain = "관련 증상 : ";
    private String symptomDate; // 증상 날짜
    private final String symptomDateExplain = "증상 날짜 : ";
    private String doctorMessage;
    private final String doctorMessageExplain = "의사에게 전달 사항 : ";
    private boolean hasFever, hasMuscle_ache, hasCough, hasSputum, hasRunnyNose, hasDyspnea, hasSoreThroat;
    private StringBuffer symptoms;
    private ImageButton callButton;
    private TextView nameTextView;
    private TextView dateTextView;
    private TextView clinicNameTextView;
    private TextView clinicAddressTextView;
    private TextView clinicCallNumberTextView;
    private TextView nationalYesOrNoTextView;
    private TextView nationalPlaceTextView;
    private TextView nationalDateTextView;
    private TextView contactYesOrNoTextView;
    private TextView contactRelationTextView;
    private TextView contactDateTextView;
    private TextView symptomYesOrNoTextView;
    private TextView symptomListTextView;
    private TextView symptomDateTextView;
    private TextView doctorMessageTextView;
    private ImageButton backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reservation_complete);
        setElement();
        //setIntentInfomation();

        setServerData();

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + clinicCallNumber));
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    private void setServerData(){

//        clinicDate = ;
//        clinicName =;
//        clinicAddress =
//        clinicCallNumber =
//        nationalCheck =
//        nationalPlace =
//        nationalDate =
//        contactCheck =
//        contactRelationShip =
//        contactRelationDate =
//        hasFever = true false
//        hasMuscle_ache =
//        hasCough =
//        hasSputum =
//        hasRunnyNose =
//        hasDyspnea =
//        hasSoreThroat =
//        symptomDate =
//        doctorMessage =

        // 다 받은 후
        changeSymptomsToString();// 실행 바람
        setElementInfo(); //실행 바람
    }

    private void changeSymptomsToString(){
        symptoms = new StringBuffer();

        if(hasFever){
            symptoms.append("37.5도 이상 열,");
        }

        if(hasMuscle_ache){
            symptoms.append("전신통/근육통,");
        }

        if(hasCough){
            symptoms.append("기침,");
        }

        if(hasSputum){
            symptoms.append("가래,");
        }

        if(hasRunnyNose){
            symptoms.append("콧물,");
        }

        if(hasDyspnea){
            symptoms.append("호흡곤란,");
        }

        if(hasSoreThroat){
            symptoms.append("인후통,");
        }

        // 마지막 쉼표 지워주기
        if(symptoms.length() != 0){
            symptoms.deleteCharAt(symptoms.length()-1);
            symptomCheck = true;
        } else{
            symptomCheck = false;
        }

    }

    private void setElement(){
        backButton = findViewById(R.id.reservation_complete_backButton);
        nameTextView = findViewById(R.id.reservation_complete_userName);
        dateTextView = findViewById(R.id.reservation_complete_date);
        clinicNameTextView = findViewById(R.id.reservation_complete_clinicName);
        clinicAddressTextView = findViewById(R.id.reservation_complete_clinicAddress);
        clinicCallNumberTextView = findViewById(R.id.reservation_complete_callNumber);
        nationalYesOrNoTextView = findViewById(R.id.reservation_complete_national_check_textView);
        nationalPlaceTextView = findViewById(R.id.reservation_complete_national_true_textView);
        nationalDateTextView = findViewById(R.id.reservation_complete_national_true_date_textView);
        contactYesOrNoTextView = findViewById(R.id.reservation_complete_contact_check_textView);
        contactRelationTextView = findViewById(R.id.reservation_complete_contact_true_textView);
        contactDateTextView = findViewById(R.id.reservation_complete_contact_true_date_textView);
        symptomYesOrNoTextView = findViewById(R.id.reservation_complete_symptom_check_textView);
        symptomListTextView = findViewById(R.id.reservation_complete_symptom_true_textView);
        symptomDateTextView = findViewById(R.id.reservation_complete_symptom_date_textView);
        callButton = findViewById(R.id.reservation_complete_callButton);
        doctorMessageTextView = findViewById(R.id.reservation_complete_doctor_message_textView);
    }

    private void setElementInfo(){
        dateTextView.setText(clinicDateExplain + clinicDate);
        clinicNameTextView.setText(clinicNameExplain + clinicName);
        clinicAddressTextView.setText(clinicAddressExplain + clinicAddress);
        clinicCallNumberTextView.setText(clinicCallNumberExplain + clinicCallNumber);
        doctorMessageTextView.setText(doctorMessageExplain + doctorMessage);

        if(nationalCheck == true){
            nationalYesOrNoTextView.setText("있음");
            nationalPlaceTextView.setText(nationPlaceExplain+nationalPlace);
            nationalDateTextView.setText(nationalDateExplain+nationalDate);
            nationalPlaceTextView.setVisibility(View.VISIBLE);
            nationalDateTextView.setVisibility(View.VISIBLE);
        } else{
            nationalYesOrNoTextView.setText("없음");
            nationalPlaceTextView.setVisibility(View.GONE);
            nationalDateTextView.setVisibility(View.GONE);
        }

        if(contactCheck == true){
            contactYesOrNoTextView.setText("있음");
            contactRelationTextView.setText(contactRelationShipExplain+contactRelationShip);
            contactDateTextView.setText(relationDateExplain + contactRelationDate);
            contactRelationTextView.setVisibility(View.VISIBLE);
            contactDateTextView.setVisibility(View.VISIBLE);
        } else{
            contactYesOrNoTextView.setText("없음");
            contactRelationTextView.setVisibility(View.GONE);
            contactDateTextView.setVisibility(View.GONE);
        }

        if(symptomCheck == true){
            symptomYesOrNoTextView.setText("있음");
            symptomListTextView.setText(symptomListExplain + symptoms);
            symptomDateTextView.setText(symptomDateExplain + symptomDate);
            symptomListTextView.setVisibility(View.VISIBLE);
            symptomDateTextView.setVisibility(View.VISIBLE);
        } else{
            symptomYesOrNoTextView.setText("없음");
            symptomListTextView.setVisibility(View.GONE);
            symptomDateTextView.setVisibility(View.GONE);
        }
    }

    private void setIntentInfomation() {

        Intent intent = getIntent(); // 데이터 수신

        clinicDate = intent.getExtras().getString("clinicDate");
        clinicName = intent.getExtras().getString("clinicName");
        clinicAddress = intent.getExtras().getString("clinicAddress");
        clinicCallNumber = intent.getExtras().getString("clinicCallNumber");
        nationalCheck = intent.getExtras().getBoolean("nationalCheck");
        nationalPlace = intent.getExtras().getString("nationalPlace");
        nationalDate = intent.getExtras().getString("nationalDate");
        contactCheck = intent.getExtras().getBoolean("contactCheck");
        contactRelationShip = intent.getExtras().getString("contactRelationShip");
        contactRelationDate = intent.getExtras().getString("contactRelationDate");
        symptomList = intent.getExtras().getString("symptomList");
        symptomDate = intent.getExtras().getString("symptomDate");
        if(symptomList.length() == 0){
            symptomCheck = false;
        } else{
            symptomCheck = true;
        }
    }

}
