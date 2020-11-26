package org.androidtown.covid19center.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("/users")
    Call<List<UsersData>> getUserData(@Query("id") String id, @Query("password") String password);
    //Call<ResponseBody> login(@Body UsersData users);

    @GET("/reservation")
    Call<List<ReservationVO>> getReservationVO();
    //Call<List<ReservationData>> getReservationData(@Query("user_id") String user_id,
        //                                           @Query("questionnaire_seq") int questionnaire_seq,
       //                                            @Query("hospital_name") String hospital_name,
          //                                         @Query("time") String time,
         //                                          @Query("date") String date);
}
