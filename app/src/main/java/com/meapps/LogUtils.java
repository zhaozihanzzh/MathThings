package com.meapps;
import android.util.*;
import com.meapps.maththings.*;

public final class LogUtils {
    private static String TAG = "MathThings";
    private static boolean isDebugging = BuildConfig.DEBUG;
    public static void d(String info){
        if(isDebugging){
            Log.d(TAG, info);
        }
    }
	public static void e(String info, Throwable ex){
		Log.e(TAG, info, ex);
	}
}
