package org.androidtown.covid19center.Hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Server.ReservationVO;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ReservationVO> list;
    String date;
    int listCount = 0;

    public ListAdapter(Context context, ArrayList<ReservationVO> data, String date) {
        mContext = context;
        list = data;
        this.date = date;
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

        TextView listview_user_id = (TextView) view.findViewById(R.id.listview_user_id);
        TextView listview_hospital = (TextView) view.findViewById(R.id.listview_hospital);
        TextView listview_date = (TextView) view.findViewById(R.id.listview_date);
        TextView listview_time = (TextView) view.findViewById(R.id.listview_time);


        listview_user_id.setText(list.get(position).getUser_id());
        listview_hospital.setText(list.get(position).getHospital_name());
        listview_date.setText(list.get(position).getDate());
        listview_time.setText(list.get(position).getTime());

        return view;
    }

    //환자 리스트 추가
    public void addItem(ReservationVO reservationVO) {
        list.add(list.size(), reservationVO);
        listCount = list.size();
        this.notifyDataSetChanged();
    }

    //환자 리스트 날짜별 변경
    public void updateItem(ArrayList<ReservationVO> nList) {
        list = nList;
        listCount = list.size();
    }

}