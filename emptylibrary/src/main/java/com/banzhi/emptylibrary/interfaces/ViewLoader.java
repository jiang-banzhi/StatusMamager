package com.banzhi.emptylibrary.interfaces;

import android.content.Context;
import android.view.View;

/**
 * <pre>
 * @author : No.1
 * @time : 2018/9/13.
 * @desciption :
 * @version :
 * </pre>
 */

public abstract class ViewLoader {
    Context mContext;

    public ViewLoader(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    protected abstract View createView();

    public View getView() {
        return createView();
    }
}
