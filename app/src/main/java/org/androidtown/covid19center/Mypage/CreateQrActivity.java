package org.androidtown.covid19center.Mypage;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.androidtown.covid19center.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreateQrActivity extends AppCompatActivity {
    private ImageView qr_code;
//    private String text;
    private String qr_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_qr);

        ImageView qr_code = (ImageView)findViewById(R.id.qr_code);
//        text = "hello qrcode!"; //원하는 내용 저장

        //@@@
        qr_data = getQrData();//원하는 내용 저장



        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        BitMatrix bitMatrix = null;

        try {

            //@@@
            //qr코드에 문진표 json 저장해야됨!
            bitMatrix = multiFormatWriter.encode(qr_data, BarcodeFormat.QR_CODE,200,200);

//            bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qr_code.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    //Qr코드에 넣을 문진표 json값 가져오기
    public String getQrData(){

        String data = null;

        //json 파일 읽어와서 분석하기

        //assets폴더의 파일을 가져오기 위해
        //창고관리자(AssetManager) 얻어오기
        AssetManager assetManager= getAssets();

        //assets/ qr.json 파일 읽기 위한 InputStream
        try {
            InputStream is= assetManager.open("jsons/qr.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line=reader.readLine();
            }

            String jsonData= buffer.toString();

            //읽어온 json문자열 확인
            //json 분석
            //json 객체 생성
            JSONObject jsonObject= new JSONObject(jsonData);
            String name= jsonObject.getString("name");
            String msg= jsonObject.getString("msg");

            //tv.setText("이름 : "+name+"\n"+"메세지 : "+msg);

            //@@@
            data = jsonObject.toString();
        } catch (IOException e) {e.printStackTrace();} catch (JSONException e) {e.printStackTrace(); }

        return data;
    }
}

