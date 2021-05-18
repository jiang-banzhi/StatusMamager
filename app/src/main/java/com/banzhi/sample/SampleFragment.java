package com.banzhi.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.banzhi.statusmanager.StatusManager;
import com.banzhi.statusmanager.annotation.ViewClick;
import com.banzhi.statusmanager.enums.LoadType;


public class SampleFragment extends Fragment {

    public SampleFragment() {
        // Required empty public constructor
    }


    public static SampleFragment newInstance() {
        SampleFragment fragment = new SampleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    View mView;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null)
            mView = inflater.inflate(R.layout.fragment_blank, container, false);
        imageView = mView.findViewById(R.id.imageview);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initEmpty();
    }

    StatusManager helper;

    private void initEmpty() {
        helper = new StatusManager.Builder(getActivity(), imageView).build();
        helper.init(this);
    }

    @ViewClick(LoadType.BOTH)
    public void loadData() {
        Toast.makeText(getActivity(), "点击布局", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("result", "setUserVisibleHint: ");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.update, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_empty:
                helper.showEmpty();
                break;
            case R.id.menu_error:
                helper.showError();
                break;
            case R.id.menu_loading:
                helper.showLoading();
                break;
            case R.id.menu_content:
                helper.showContent();
                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
