package com.example.dell.rtmeeting.utils;

import android.hardware.Camera;

/**
 * Created by JiangJun on 2018/1/23.
 */

public class CommonUtil {

    public static boolean isCameraCanUse(){
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            if (mCamera != null)
                mCamera.release();
            mCamera = null;
        }
        return canUse;
    }
}
