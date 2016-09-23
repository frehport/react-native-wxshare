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

RCT_EXPORT_METHOD(share:(NSArray *)array) {
        SendMessageToWXReq *req = [[SendMessageToWXReq alloc] init];
        req.text = [WxShareDataFormatter formatData:array];
        req.bText = YES;
        req.scene = WXSceneSession;
        [WXApi sendReq:req];
}

@end
