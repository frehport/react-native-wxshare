package com.novacloud.wxshare.react;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


/**
 * Created by Nova on 16/9/23.
 */

public class WxShareNativeModule extends ReactContextBaseJavaModule {

    private IWXAPI api;
    private static final String TITLE = "title";
    private static final String URL = "url";
    private static final String IMAGE_URL = "imageURL";
    private static final String DESCRIPTION = "description";


    public WxShareNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "WxShare";
    }

    @ReactMethod
    public void registerApp(String appKey) {
        api = WXAPIFactory.createWXAPI(getReactApplicationContext(), appKey, true);
        api.registerApp(appKey);
    }

    @ReactMethod
    public void shareText(ReadableArray array) {
        WXTextObject textObject = new WXTextObject();
        String text =  WxShareHelper.formatData(array);

        textObject.text = text;

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObject;
        msg.description = text;

        SendMessageToWX.Req  req = new SendMessageToWX.Req();
        req.transaction = UUID.randomUUID().toString();
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;

        api.sendReq(req);
    }

    @ReactMethod
    public void share(ReadableMap map) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = UUID.randomUUID().toString();

        if (map.hasKey(URL)) {
            WXWebpageObject webpageObject = new WXWebpageObject();
            webpageObject.webpageUrl = map.getString(URL);
            WXMediaMessage msg = new WXMediaMessage(webpageObject);
            msg.title = map.getString(TITLE);
            if (map.hasKey(DESCRIPTION)) msg.description = map.getString(DESCRIPTION);
            if (map.hasKey(IMAGE_URL)) msg.setThumbImage(getBitMap(map.getString(IMAGE_URL)));
            req.message = msg;

        } else {
            String text = map.getString(TITLE);
            WXTextObject textObject = new WXTextObject();
            textObject.text = text;

            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = textObject;
            msg.description = text;

            req.message = msg;
        }
        req.scene = SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }

    private Bitmap getBitMap(String url) {
        URL fileURl = null;
        Bitmap bitmap = null;
        try {
            fileURl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileURl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
