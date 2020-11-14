package org.androidtown.covid19center.Search.List;

<<<<<<< HEAD
import org.androidtown.covid19center.Map.LocationConsts;
import org.androidtown.covid19center.Map.LocationInfo;

=======
>>>>>>> 24cf7a1044cc358d297d13b496581957c0c86d0e
public class ClinicItem {

    private String clinicName; // 진료소 명
    private String clinicCallNumber; // 진료소 전화번호
<<<<<<< HEAD
    private String clinicAddress;     // 진료소 주소
    private double clinicDistance;
    private String x;     // 진료소 떨어진 거리
    private String y;


    //생성자 초기화
    public ClinicItem(String clinicName, String clinicCallNumber, String clinicDistance, String x, String y) {
        this.clinicName = clinicName;
        this.clinicCallNumber = clinicCallNumber;
        this.clinicAddress = clinicDistance;
        this.x = x;
        this.y = y;
        this.clinicDistance = 0;

    }

    public ClinicItem(String clinicName, String clinicCallNumber, String clinicDistance, String x, String y, String start) {
        this.clinicName = clinicName;
        this.clinicCallNumber = clinicCallNumber;
        this.clinicAddress = clinicDistance;
        this.x = x;
        this.y = y;
        this.clinicDistance = 1;
        this.clinicDistance = new LocationInfo(LocationConsts.NOW_X, LocationConsts.NOW_Y, Double.parseDouble(x), Double.parseDouble(y)).getDistance();
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

    public double getClinicDistance() {
        return clinicDistance;
    }

    public void setClinicDistance(double clinicDistance) {
        this.clinicDistance = clinicDistance;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

=======
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

>>>>>>> 24cf7a1044cc358d297d13b496581957c0c86d0e
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
<<<<<<< HEAD

=======
>>>>>>> 24cf7a1044cc358d297d13b496581957c0c86d0e
}
