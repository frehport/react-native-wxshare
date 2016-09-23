package com.novacloud.wxshare.react;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.UUID;


/**
 * Created by Nova on 16/9/23.
 */

public class WxShareNativeModule extends ReactContextBaseJavaModule {

    private IWXAPI api;

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
    public void share(ReadableArray array) {
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

}
