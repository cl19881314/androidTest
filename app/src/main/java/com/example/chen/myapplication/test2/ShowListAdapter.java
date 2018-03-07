package com.example.chen.myapplication.test2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.chen.myapplication.R;

/**
 * Created by chen on 2018-03-07.
 */

public class ShowListAdapter extends BaseAdapter {
    private Context mContext;
    private static final int TYPE_HOTE = 0;
    private static final int TYPE_DOC = 1;
    private static final int TYPE_VIDEO = 2;
    public ShowListAdapter(Context context){
        mContext = context;
    }
    private int getViewType(int position){
        int type = position % 3;
        return type;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = getItemView(position);
        }
        int type = (int) convertView.getTag();
        if (type != getViewType(position)){
            convertView = getItemView(position);
        }
        return convertView;
    }

    private View getItemView(int position){
        View convertView = null;
        switch (getViewType(position)){
            case TYPE_HOTE:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_hot_layout,null,false);
                convertView.setTag(TYPE_HOTE);
                break;
            case TYPE_DOC:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_doc_layout,null,false);
                convertView.setTag(TYPE_DOC);
                break;
            case TYPE_VIDEO:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_video_layout,null,false);
                convertView.setTag(TYPE_VIDEO);
                break;
        }
        return convertView;
    }
}
