//
//  WxShare.m
//  WxShare
//
//  Created by euky on 16/9/22.
//  Copyright © 2016年 euky. All rights reserved.
//

#import "WxShare.h"
#import "WXApi.h"
#import "WXApiObject.h"
#import "RCTBridgeModule.h"
#import "WxShareDataFormatter.h"

@interface WxShare()<RCTBridgeModule>

@end

@implementation WxShare


RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(registerApp:(NSString* )appKey) {
    [WXApi registerApp:appKey];
}

RCT_EXPORT_METHOD(shareText:(NSArray *)array) {
    SendMessageToWXReq *req = [[SendMessageToWXReq alloc] init];
    req.text = [WxShareDataFormatter formatData:array];
    req.bText = YES;
    req.scene = WXSceneSession;
    [WXApi sendReq:req];
}

RCT_EXPORT_METHOD(share:(NSDictionary *)dic) {
    SendMessageToWXReq *req = [[SendMessageToWXReq alloc] init];
    req.scene = WXSceneSession;
    if (dic[@"url"]) {
        req.bText = NO;
        WXMediaMessage *message = [WXMediaMessage message];
        message.title = dic[@"title"];
        message.description = dic[@"description"];
        if (dic[@"imageURL"]) {
            [message setThumbImage:[UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:dic[@"imageURL"]]]]];
        }
        
        WXWebpageObject *webpageObject = [WXWebpageObject object];
        webpageObject.webpageUrl = dic[@"url"];
        message.mediaObject = webpageObject;
        
        req.message = message;
        
    } else {
        req.text = dic[@"text"];
    }
    
    [WXApi sendReq:req];
}

@end
