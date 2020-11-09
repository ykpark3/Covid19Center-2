package org.androidtown.covid19center.Retrofit;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApi {
    @Headers({
            "X-Naver-Client-Id: " + NaverConsts.CLIENT_ID,
            "X-Naver-Client-Secret: " + NaverConsts.CLIENT_SECRET
    })
    @GET("/map-geocode/v2/geocode?query=분당구 불정로 6")
    Call<Post> getData(@Query("addresses[].x") String x);

    @FormUrlEncoded
    @POST("/posts")
    Call<Post> postData(@FieldMap HashMap<String, Object> param);


}
