package com.android.test.a1shippro.Utils;

import android.content.Context;
import android.content.res.TypedArray;

import com.android.test.a1shippro.R;

/**
 * Created by Zanty on 16/06/2016.
 */
public class Util {
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }
}
