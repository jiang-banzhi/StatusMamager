package com.banzhi.emptysample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Class clz = null;
        switch (v.getId()) {
            case R.id.tv_test1:
                clz = EmptyActivity.class;
                break;
            case R.id.tv_test2:
                clz = PartEmptyActivity.class;
                break;
            case R.id.tv_test3:
                clz = ContainerActivity.class;
                break;
            case R.id.tv_test4:
                clz = PagerActivity.class;
            default:

                break;
        }
        startActivity(new Intent(this, clz));
    }

}
