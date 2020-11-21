package org.androidtown.covid19center.Search.Time;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.androidtown.covid19center.R;

public class SubTimeItemView extends LinearLayout {

    private TextView textView;

    public SubTimeItemView(Context context) {
        super(context);

        init(context);
    }

    public SubTimeItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.subtime_item, this, true);

        textView = (TextView) findViewById(R.id.subtime_textView);
    }

    public void setName(String name) {
        textView.setText(name);
    }

}
