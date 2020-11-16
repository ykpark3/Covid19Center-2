package org.androidtown.covid19center.Mypage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.androidtown.covid19center.QrCode.CreateQr;
import org.androidtown.covid19center.QrCode.ScanQr;
import org.androidtown.covid19center.R;

public class FragmentMypage extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_mypage,container,false);

        LinearLayout reservation_qr = view.findViewById(R.id.reservation_qr);
        LinearLayout medical_records = view.findViewById(R.id.medical_records);

        reservation_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Qr코드 생성 activity로 intent
                Intent intent = new Intent(getActivity(), CreateQr.class);
                startActivity(intent);
            }
        });

        medical_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //진료기록 액티비티로 수정해야됨!!
                Intent intent = new Intent(getActivity(), MedicalRecordActivity.class);
                startActivity(intent);
            }
        });

        Button button_create_qr = view.findViewById(R.id.button_create_qr);
        Button button_scan_qr = view.findViewById(R.id.button_scan_qr);

        button_create_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Qr코드 생성 activity로 intent
                Intent intent = new Intent(getActivity(), CreateQr.class);
                startActivity(intent);
            }
        });

        button_scan_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Qr코드 스캔 activity로 intent
                Intent intent = new Intent(getActivity(), ScanQr.class);
                startActivity(intent);
            }
        });

        return view;
    }

}