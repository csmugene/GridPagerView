package com.csmugene.gridpagerview.util;

import android.content.Context;

/**
 * Created by ichungseob on 2018. 8. 20..
 */

public class CommonUtil {

    private CommonUtil(){}

    public static int convertDpToPixels(Context context, int dp) {
        int px = 0;
        px = (int) (dp * context.getResources().getDisplayMetrics().density);
        return px;
    }
}
