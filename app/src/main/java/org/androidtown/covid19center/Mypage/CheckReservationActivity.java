package org.androidtown.covid19center.Mypage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;

public class CheckReservationActivity extends AppCompatActivity {

    private Button questionnaireButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_reservation);

        questionnaireButton = findViewById(R.id.questionnaireButton);

        questionnaireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("~~~~~","questionnaire button click");
                goToNextActivity();
            }
        });
    }

    private void goToNextActivity() {
        Log.d("~~~~~","goToNextActivity");
        Intent intent = new Intent(this, QuestionnaireModificationActivity.class);
        startActivity(intent);

    }
}
