package org.androidtown.covid19center.QrCode;

import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Server.ReservationVO;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreateJson_test {
    private ArrayList<ReservationVO> qrList = new ArrayList<>();

    ReservationVO reservationVO;

    private CreateJson_test(){
        reservationVO = new ReservationVO("user1", 1, "hospital_1", "11:30", "11/19");
    }

    public void putJson(){
        //Json 만들기
        JSONObject object = new JSONObject();

        try {
            object.put("user_id", reservationVO.getUser_id());
            object.put("questionnaire_seq", reservationVO.getQuestionnaire_seq());
            object.put("hospital", reservationVO.getHospital_name());
            object.put("date", reservationVO.getDate());
            object.put("time", reservationVO.getTime());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
