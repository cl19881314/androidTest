package com.example.chen.myapplication.test2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.chen.myapplication.R;

import java.util.ArrayList;

/**
 * Created by chen on 2018-03-07.
 */

public class ShowAnyItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_layout);
        ListView lvShow = (ListView) findViewById(R.id.lv_show_content);
        lvShow.setAdapter(new ShowListAdapter(this));
    }
}
