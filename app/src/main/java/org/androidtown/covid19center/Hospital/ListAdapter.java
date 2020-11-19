package org.androidtown.covid19center.Hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Server.ReservationVO;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ReservationVO> list;

    public ListAdapter(Context context, ArrayList<ReservationVO> data) {
        mContext = context;
        list = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ReservationVO getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview_reservation, null);

//        ImageView imageView = (ImageView)view.findViewById(R.id.poster);
//        TextView movieName = (TextView)view.findViewById(R.id.movieName);
//        TextView grade = (TextView)view.findViewById(R.id.grade);
//
//        imageView.setImageResource(sample.get(position).getPoster());
//        movieName.setText(sample.get(position).getMovieName());
//        grade.setText(sample.get(position).getGrade());

        TextView user_id = (TextView)view.findViewById(R.id.listview_user_id);
        TextView hospital = (TextView)view.findViewById(R.id.listview_hospital);
        TextView date = (TextView)view.findViewById(R.id.listview_date);
        TextView time = (TextView)view.findViewById(R.id.listview_time);


        user_id.setText(list.get(position).getUser_id());
        hospital.setText(list.get(position).getHospital_name());
        date.setText(list.get(position).getDate());
        time.setText(list.get(position).getTime());

        return view;
    }
}