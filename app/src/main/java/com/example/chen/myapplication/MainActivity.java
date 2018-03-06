package com.example.chen.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.chen.myapplication.test1.MyViewGroupActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMyViewGroup(View v){
        Intent intent = new Intent(this, MyViewGroupActivity.class);
        startActivity(intent);
    }

    public void showAnyItem(View v){

    }

}
