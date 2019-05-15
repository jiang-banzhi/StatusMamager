package com.banzhi.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.banzhi.statusmanager.StatusManager;
import com.banzhi.statusmanager.annotation.ViewClick;
import com.banzhi.statusmanager.enums.LoadType;


public class TabFragment extends Fragment {

    public TabFragment() {
        // Required empty public constructor
    }


    public static TabFragment newInstance() {
        TabFragment fragment = new TabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    View mView;
    ImageView imageView;
    boolean isFirstInit;
    SparseArray<View> sparseArray = new SparseArray<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null)
            mView = inflater.inflate(R.layout.fragment_tab, container, false);
        isFirstInit = true;
        if (isFirstInit) {
            lazyLoad();

        }
        return mView;
    }

    StatusManager helper;

    private void lazyLoad() {
        if (!isFirstInit) {
            return;
        }
        if (getUserVisibleHint()) {
            isFirstInit = false;
            if (sparseArray.get(R.id.imageview) == null) {
                imageView = mView.findViewById(R.id.imageview);
                sparseArray.put(R.id.imageview, imageView);
            } else {
                imageView = (ImageView) sparseArray.get(R.id.imageview);
            }
            helper = new StatusManager.Builder(getActivity(), imageView).build();
            helper.init(this);
        }
    }


    @ViewClick(LoadType.BOTH)
    public void loadData() {
        Toast.makeText(getActivity(), "点击布局", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
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
