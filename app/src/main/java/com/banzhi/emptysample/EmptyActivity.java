package com.banzhi.emptysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.banzhi.emptylibrary.ELoad;
import com.banzhi.emptylibrary.annotation.ViewClick;
import com.banzhi.emptylibrary.enums.LoadType;
import com.banzhi.emptylibrary.interfaces.OnLayoutClickListener;

public class EmptyActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    ELoad helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View groupView = LayoutInflater.from(this).inflate(R.layout.activity_empty, null);
        setContentView(groupView);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        helper = new ELoad.Builder(this)
                .setEmptyView(new EmptyView(this,"空数据"))
                .setErrorView(new EmptyView(this,"错误布局"))
                .build();
        helper.init(this);
        helper.init(new OnLayoutClickListener() {
            @Override
            public void onEmptyViewClick() {

            }

            @Override
            public void onErrorViewClick() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @ViewClick(LoadType.BOTH)
    public void click() {
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
