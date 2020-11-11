package org.androidtown.covid19center.Search.List;

public class ClinicItem {

    private String clinicName; // 진료소 명
    private String clinicCallNumber; // 진료소 전화번호
    private String clinicDistance;     // 진료소 떨어진 거리
    private String x;     // 진료소 떨어진 거리
    private String y;

    //생성자 초기화
    public ClinicItem(String clinicName, String clinicCallNumber, String clinicDistance, String x, String y) {
        this.clinicName = clinicName;
        this.clinicCallNumber = clinicCallNumber;
        this.clinicDistance = clinicDistance;
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getClinicDistance() {
        return clinicDistance;
    }

    public void setClinicDistance(String clinicDistance) {
        this.clinicDistance = clinicDistance;
    }

    public String getClinicCallNumber() {
        return clinicCallNumber;
    }

    public void setClinicCallNumber(String clinicCallNumber) {
        this.clinicCallNumber = clinicCallNumber;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
