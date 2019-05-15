package com.banzhi.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.banzhi.statusmanager.StatusManager;
import com.banzhi.statusmanager.annotation.ViewClick;
import com.banzhi.statusmanager.enums.LoadType;

public class PartEmptyActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    StatusManager helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_empty);
        TextView textView = findViewById(R.id.textview2);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        helper = new StatusManager.Builder(this)
                .setContentView(recyclerView)
                .setEmptyView(new EmptyView(this,"空数据"))
                .setErrorView(new EmptyView(this,"错误布局"))
                .build();
        helper.init(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @ViewClick(LoadType.BOTH)
    public void click() {
        Log.i("result", "click: "+System.currentTimeMillis());
        if (type == 1) {
            Toast.makeText(this, "点击空布局", Toast.LENGTH_SHORT).show();
            helper.showEmpty();
        } else if (type == 2) {
            Toast.makeText(this, "点击错误布局", Toast.LENGTH_SHORT).show();
        }
    }

    int type;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_empty:
                helper.showEmpty();
                type = 1;
                break;
            case R.id.menu_error:
                helper.showError();
                type = 2;
                break;
            case R.id.menu_loading:
                helper.showLoading();
                break;
            case R.id.menu_content:
                helper.showContent();
                type = 0;
                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public class Adapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.simple, parent, false);

            return new TestViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((TestViewHolder) holder).setText("内容" + position + text);
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        String text;

        public void refresh() {
            text += "内容追加";
            notifyDataSetChanged();
        }
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TestViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.sample);
        }

        public void setText(String tex) {
            textView.setText(tex);
        }
    }
}
