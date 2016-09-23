package com.novacloud.wxshare.react;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

/**
 * Created by Nova on 16/9/23.
 */

public class WxShareHelper {

    private static final String KEY_APP_KEY = "APP_KEY";

    public static String getAppKey(Context context) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai)
                metaData = ai.metaData;
            if (null != metaData) {
                appKey = metaData.getString(KEY_APP_KEY);
                if ((null == appKey) || appKey.length() != 24) {
                    appKey = null;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return appKey;
    }
    public static String formatData(ReadableArray array) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (int i = 0; i < array.size(); i++) {
                ReadableMap readableMap = array.getMap(i);

                stringBuffer.append(i + 1 + ".");
                String str = readableMap.getString("supplier");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("vendor");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                if (readableMap.getType("platenum") == ReadableType.Number) {
                    if (null != str && !"null".equals(str)) {
                        stringBuffer.append(readableMap.getDouble("platenum"));
                        stringBuffer.append("/");
                    }
                }


                str = readableMap.getString("marketdate");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("enterusername");
                if (null != str && !"null".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                if (readableMap.getType("transport") == ReadableType.Number) {

                    String transport = "";
                    if (readableMap.getInt("transport") == 0) {
                        transport = "海运";
                    } else if (readableMap.getInt("transport") == 1) {
                        transport = "空运";
                    } else if (readableMap.getInt("transport") == 2) {
                        transport = "陆运";
                    }

                    stringBuffer.append(transport);
                    stringBuffer.append("/");
                }


                str = readableMap.getString("countryname");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("productname");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("brand");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("varieties");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("spec");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("weight");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                if (readableMap.getType("minprice") == ReadableType.Number) {
                    stringBuffer.append(readableMap.getDouble("minprice"));
                    stringBuffer.append("/");

                }

                if (readableMap.getType("maxprice") == ReadableType.Number) {
                    stringBuffer.append(readableMap.getDouble("maxprice"));
                    stringBuffer.append("/");

                }

                str = readableMap.getString("quality");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("cntrno");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("packingplant");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/n");
                }

                str = readableMap.getString("farm");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                str = readableMap.getString("sailedescribe");
                if (null != str && !"null".equals(str) && !"".equals(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append("/");
                }

                if (stringBuffer.lastIndexOf("/") == stringBuffer.length() - 1) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }

                stringBuffer.append("\n ");
            }
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return stringBuffer.toString();

    }
}
