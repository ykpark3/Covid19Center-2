package org.androidtown.covid19center.Search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import org.androidtown.covid19center.Mypage.ReservationCompleteActivity;
import org.androidtown.covid19center.R;

public class LottieReservationCompleteActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiiy_lottie_reservation_complete);

        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie_reservation_complete);
        lottieAnimationView.playAnimation();
        lottieAnimationView.loop(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mHandler.postDelayed(mMyTask, 3000); // 3초후에 실행
    }

    private Runnable mMyTask = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), ReservationCompleteActivity.class);
            startActivity(intent);
            finish();
        }
    };


}
