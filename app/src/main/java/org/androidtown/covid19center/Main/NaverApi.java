package org.androidtown.covid19center.Main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class NaverApi extends Thread {

    static String NaverAddress = null;

    public NaverApi(String address) {
        NaverAddress = address;
    }

    public void run() {
        main();
    }

    public static void main() {

        String text = null;
        try {
            text = URLEncoder.encode(NaverAddress, "UTF-8");
        } catch (UnsupportedEncodingException e){
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = NaverConsts.GEOCORD_URL+text;

        Map<String, String> requestHeaders =new HashMap<>();
        requestHeaders.put("X-NCP-APIGW-API-KEY-ID", NaverConsts.CLIENT_ID);
        requestHeaders.put("X-NCP-APIGW-API-KEY", NaverConsts.CLIENT_SECRET);
        String responseBody = get(apiURL, requestHeaders);

        parseData(responseBody);
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");

            for(Map.Entry<String, String> header : requestHeaders.entrySet()){
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                return readBody(con.getInputStream());
            } else{
                return readBody(con.getErrorStream());
            }

        } catch (IOException e){
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }

    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e){
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e){
            throw new RuntimeException("연결이 실패했습니다. :" + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader =  new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("error readBody");
        }
    }

    private static void parseData(String responseBody) {
        String x;
        String y;
        String name;
        String name2;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responseBody);
            JSONArray jsonArray = jsonObject.getJSONArray("addresses");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                x = item.getString("x");
                y = item.getString("y");
                name = item.getString("roadAddress");
                name2 = item.getString("jibunAddress");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}