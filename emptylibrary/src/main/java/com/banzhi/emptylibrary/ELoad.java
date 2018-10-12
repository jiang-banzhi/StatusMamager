package com.banzhi.emptylibrary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.banzhi.emptylibrary.annotation.ViewClick;
import com.banzhi.emptylibrary.enums.LoadType;
import com.banzhi.emptylibrary.interfaces.OnLayoutClickListener;
import com.banzhi.emptylibrary.interfaces.ViewLoader;
import com.banzhi.emptylibrary.utils.ViewUtils;
import com.banzhi.emptylibrary.view.SimpleEmptyView;
import com.banzhi.emptylibrary.view.SimpleErrorView;
import com.banzhi.emptylibrary.view.SimpleLoadingView;

import java.lang.reflect.Method;

/**
 * <pre>
 * @author : No.1
 * @time : 2018/9/11.
 * @desciption :
 * @version :
 * </pre>
 */

public class ELoad implements View.OnClickListener {
    Context mContext;
    View targetView;
    View mEmptyView;
    View mErrorView;
    View mLoadView;
    boolean isFillWindow;
    OnLayoutClickListener mListener;
    Object object;

    private ELoad(Builder builder) {
        mContext = builder.context;
        targetView = builder.contentView;
        mEmptyView = builder.emptyView;
        mErrorView = builder.errorView;
        mLoadView = builder.loadingView;
        isFillWindow = builder.isFillWindow;


    }

    public void init(OnLayoutClickListener listener) {
        mListener = listener;
        init();
        initListener();
    }

    public void init(Object clz) {
        object = clz;
        init();
        initListener();
    }

    private void initListener() {
        mEmptyView.setTag(LoadType.EMPTY);
        mErrorView.setTag(LoadType.ERROR);
        mEmptyView.setOnClickListener(this);
        mErrorView.setOnClickListener(this);
    }

    private void init() {
        if (isFillWindow) {
            whole();
        } else {
            partial();
        }
    }

    public void whole() {
        ViewGroup parentView;
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            parentView = activity.findViewById(android.R.id.content);
            targetView = parentView.getChildAt(0);
            parentView.addView(mEmptyView, targetView.getLayoutParams());
            parentView.addView(mErrorView, targetView.getLayoutParams());
            parentView.addView(mLoadView, targetView.getLayoutParams());
            showContent();
        } else {
            partial();
        }


    }

    /**
     * 仅将布局添加到targetview
     */
    public void partial() {
        if (targetView == null) return;
        ViewGroup parent = (ViewGroup) targetView.getParent();
        //内容view的位置
        int index = parent.indexOfChild(targetView);
        ViewGroup.LayoutParams targetParams = targetView.getLayoutParams();
        parent.removeView(targetView);
        ViewGroup emptyContainer;
        if (targetParams instanceof ViewGroup.MarginLayoutParams) {//父容器是ConstraintLayout、RelativeLayout
//            emptyContainer = new ConstraintLayout(mContext);
            emptyContainer = new FrameLayout(mContext);
            emptyContainer.setId(targetView.getId());
            targetView.setId(-1);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) targetParams;
            emptyContainer.setLayoutParams(layoutParams);
            parent.addView(emptyContainer, index, layoutParams);
        } else {
            emptyContainer = new FrameLayout(mContext);
            emptyContainer.setLayoutParams(targetParams);
            parent.addView(emptyContainer, index, targetParams);
        }
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
                ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT);
        emptyContainer.addView(targetView, params);
        emptyContainer.addView(mEmptyView, params);
        emptyContainer.addView(mErrorView, params);
        emptyContainer.addView(mLoadView, params);
        showContent();
    }

    public void showContent() {
        targetView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mLoadView.setVisibility(View.GONE);
    }

    public void showError() {
        targetView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mLoadView.setVisibility(View.GONE);
    }

    public void showEmpty() {
        targetView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        mLoadView.setVisibility(View.GONE);
    }

    public void showLoading() {
        targetView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mLoadView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        ViewUtils.setDelayedClickable(v, 500);
        Object object = v.getTag();
        if (null != object) {
            LoadType tag = (LoadType) v.getTag();
            if (mListener != null) {
                if (LoadType.EMPTY == tag) {
                    mListener.onEmptyViewClick();
                }
                if (LoadType.ERROR == tag) {
                    mListener.onErrorViewClick();
                }
            } else {
                clickLayout(tag);
            }
        }

    }

    private void clickLayout(LoadType type) {
        if (object == null) {
            return;
        }
        Class clazz = object.getClass();
        try {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                if (m.getAnnotation(ViewClick.class) != null) {
                    ViewClick annotation = m.getAnnotation(ViewClick.class);
                    LoadType values = annotation.value();
                    if (values == LoadType.BOTH || values == type) {
                        m.setAccessible(true);
                        m.invoke(object);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class Builder {
        Context context;
        View contentView;
        View emptyView;
        View errorView;
        View loadingView;
        LayoutInflater inflater;
        boolean isFillWindow;
        Object object;
        OnLayoutClickListener listener;

        public Builder(Context context, View contentView) {
            this.context = context;
            this.contentView = contentView;
            inflater = LayoutInflater.from(context);
        }


        public Builder(Context context) {
            this(context, null);
            isFillWindow = true;
        }

        public Builder isFillWindow() {
            this.isFillWindow = true;
            return this;
        }

        public Builder isFillWindow(boolean isFill) {
            this.isFillWindow = isFill;
            if (!isFillWindow) {
                throw new NullPointerException("如果isFillWindow=false,必须设置contentView!");
            }
            return this;
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            isFillWindow = false;
            return this;
        }

        public Builder setEmptyView(View emptyView) {
            this.emptyView = emptyView;
            return this;
        }

        public Builder setErrorView(View errorView) {
            this.errorView = errorView;
            return this;
        }

        public Builder setLoadingView(View loadingView) {
            this.loadingView = loadingView;
            return this;
        }

        public Builder setEmptyView(ViewLoader loader) {
            this.emptyView = loader.getView();
            return this;
        }

        public Builder setErrorView(ViewLoader loader) {
            this.errorView = loader.getView();
            return this;
        }

        public Builder setLoadingView(ViewLoader loader) {
            this.loadingView = loader.getView();
            return this;
        }

        /**
         * 不设置空界面 异常界面点击
         *
         * @return
         */
        public ELoad build() {
            if (emptyView == null) {
                emptyView = new SimpleEmptyView(context).getView();
            }
            if (errorView == null) {
                errorView = new SimpleErrorView(context).getView();
            }
            if (loadingView == null) {
                loadingView = new SimpleLoadingView(context).getView();
            }
            return new ELoad(this);
        }

    }


}
