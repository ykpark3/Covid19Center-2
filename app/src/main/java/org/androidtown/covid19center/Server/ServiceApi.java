package org.androidtown.covid19center.Server;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("/users")
    Call<List<UsersVO>> getUserData(@Query("id") String id, @Query("password") String password);
    //Call<ResponseBody> login(@Body UsersData users);

    @GET("/reservation")
    Call<List<ReservationVO>> getReservationVO();

    @GET("/quesionnaire")
    Call<List<QuestionnaireVO>> getQuesionnaireVO();

    @POST("/reservationInsert")
    Call<ResponseBody> sendReservationData(@Body ReservationData reservationData);

    @POST("/questionnaire")
    Call<ResponseBody> sendQuestionnaireData(@Body QuestionnaireData questionnaireData);
}
