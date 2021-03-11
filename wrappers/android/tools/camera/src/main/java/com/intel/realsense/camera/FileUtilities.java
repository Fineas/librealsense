package com.intel.realsense.camera;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtilities {

    private static final String TAG = "file_utilities";

    private final static Handler mHandler = new Handler();
    private static Thread mUiThread;

    public static final void runOnUiThread(Runnable action) {
        if (Thread.currentThread() != mUiThread) {
            mHandler.post(action);
        } else {
            action.run();
        }
    }

    public static String getExternalStorageDir(Context context) {
        return context.getExternalFilesDir(null).getAbsolutePath();
    }

    public static void saveFileToExternalDir(Context context, final String fileName, byte[] data) {
        try {
            File file = new File(context.getExternalFilesDir(null) + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            Log.i(TAG, "saveFileToExternalDir: file " + fileName + " saved successfully");
        } catch (Exception e) {
            Log.e(TAG, "saveFileToExternalDir: failed to create a file " + fileName, e);
        }
    }

    public static boolean isPathEmpty(String path) {
        try {
            File folder = new File(path);
            if (!folder.exists()) {
                return true;
            }
            final File[] files = folder.listFiles();
            if (files.length == 0)
                return true;
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}
