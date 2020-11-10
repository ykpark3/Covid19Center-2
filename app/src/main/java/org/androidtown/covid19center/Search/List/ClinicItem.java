package org.androidtown.covid19center.Search.List;

public class ClinicItem {

    private String clinicName; // 진료소 명
    private String clinicCallNumber; // 진료소 전화번호
    private String clinicDistance;     // 진료소 떨어진 거리

    //생성자 초기화
    public ClinicItem(String clinicName, String clinicCallNumber, String clinicDistance) {
        this.clinicName = clinicName;
        this.clinicCallNumber = clinicCallNumber;
        this.clinicDistance = clinicDistance;
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
