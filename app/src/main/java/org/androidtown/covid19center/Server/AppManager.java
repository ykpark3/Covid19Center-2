package org.androidtown.covid19center.Server;

import java.util.ArrayList;

public class AppManager {
    private static AppManager instance;
    private ArrayList<ReservationVO> reservationVOArrayList = new ArrayList<>();

    public static AppManager getInstance() {
        if(instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void setReservationVOArrayList(ArrayList<ReservationVO> reservationVOArrayList) {
        this.reservationVOArrayList = reservationVOArrayList;
    }

    public ArrayList<ReservationVO> getReservationVOArrayList() {
        return reservationVOArrayList;
    }
}
