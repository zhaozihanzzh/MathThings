package com.meapps;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.os.*;
import android.preference.*;
import java.util.*;
import com.meapps.maththings.*;

public class App extends Application implements Thread.UncaughtExceptionHandler {
    private static List<Activity> mActivities = new ArrayList<>();
    public static void addActivity(Activity activity) {
        LogUtils.d(mActivities.add(activity) + " to Create activity " + activity);
    }
    public static List<Activity> getActivities() {
        return mActivities;
    }
    public static void removeActivity(Activity activity) {     
        LogUtils.d(mActivities.remove(activity) + " to Destroy activity " + activity);
    }
    public static void finishAll() {
        for (Activity a : mActivities) {
            if (!a.isFinishing()) {
                a.finish();
            }
        }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
		//设置Thread Exception Handler
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
	@Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtils.e("Exception by MathThings: ",ex);
        final Intent intent = new Intent(this, Crash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  
                        Intent.FLAG_ACTIVITY_NEW_TASK);

        StringBuilder logWrongInfo = new StringBuilder();
        logWrongInfo.append("Model: " + Build.MODEL + "    SDK: " + Build.VERSION.SDK_INT
                            + "\n" + "Fingerprint: " + Build.FINGERPRINT
                            + "\n" + ex.toString());
        for(StackTraceElement e : ex.getStackTrace()){
            logWrongInfo.append("\n     at " +e.toString());
        }
        intent.putExtra("log", logWrongInfo.toString());
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
        // System.exit() or killProcess() is necessary here.
    }
}
