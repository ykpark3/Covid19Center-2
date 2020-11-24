package org.androidtown.covid19center.Server;

import java.util.ArrayList;

public class AppManager {
    private static AppManager instance;
    private ArrayList<ReservationVO> reservationVOArrayList = new ArrayList<>();
    private String userId;

    public static AppManager getInstance() {
        if(instance == null) {
            instance = new AppManager();
        }
        return instance;
    }


    public ArrayList<ReservationVO> getReservationVOArrayList() {
        return reservationVOArrayList;
    }
    public void setReservationVOArrayList(ArrayList<ReservationVO> reservationVOArrayList) {
        this.reservationVOArrayList = reservationVOArrayList;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
