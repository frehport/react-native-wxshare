#import "WxShareDataFormatter.h"

@interface WxShareDataFormatter()

@end

@implementation WxShareDataFormatter

+ (BOOL)isValid:(id)e {
    if (e == [NSNull null]) {
        return false;
    }
    
    if ([e isKindOfClass:[NSString class]]) {
        if ([e isEqualToString:@""]) {
            return false;
        }
    }
    
    return true;
}

+ (NSString *)formatData:(NSArray *)arr {
    NSMutableString *str = [[NSMutableString alloc] init];
    for (NSUInteger i = 0; i < arr.count; i++) {
        NSDictionary *dic = arr[i];
        [str appendString:[NSString stringWithFormat:@"%ld.", i+1]];
        if ([self isValid:dic[@"supplier"]]) {
            [str appendString:dic[@"supplier"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"vendor"]]) {
            [str appendString:dic[@"vendor"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"platenum"]]) {
            [str appendString:[NSString stringWithFormat:@"%@", dic[@"platenum"]]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"marketdate"]]) {
            [str appendString:dic[@"marketdate"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"enterusername"]]) {
            [str appendString:dic[@"enterusername"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"transport"]]) {
            NSString *transport = @"";
            if ([[NSString stringWithFormat:@"%@", dic[@"transport"]] isEqualToString:@"0"]) {
                transport = @"海运";
            } else if ([[NSString stringWithFormat:@"%@", dic[@"transport"]] isEqualToString:@"1"]) {
                transport = @"空运";
            } else if ([[NSString stringWithFormat:@"%@", dic[@"transport"]] isEqualToString:@"2"]) {
                transport = @"陆运";
            }
            [str appendString:transport];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"countryname"]]) {
            [str appendString:dic[@"countryname"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"productname"]]) {
            [str appendString:dic[@"productname"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"brand"]]) {
            [str appendString:dic[@"brand"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"varieties"]]) {
            [str appendString:dic[@"varieties"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"spec"]]) {
            [str appendString:dic[@"spec"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"weight"]]) {
            [str appendString:[NSString stringWithFormat:@"%@", dic[@"weight"]]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"minprice"]]) {
            [str appendString:[NSString stringWithFormat:@"%@", dic[@"minprice"]]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"maxprice"]]) {
            [str appendString:[NSString stringWithFormat:@"%@", dic[@"maxprice"]]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"quality"]]) {
            [str appendString:dic[@"quality"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"cntrno"]]) {
            [str appendString:[NSString stringWithFormat:@"%@", dic[@"cntrno"]]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"packingplant"]]) {
            [str appendString:dic[@"packingplant"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"farm"]]) {
            [str appendString:dic[@"farm"]];
            [str appendString:@"/"];
        }
        if ([self isValid:dic[@"sailedescribe"]]) {
            [str appendString:dic[@"sailedescribe"]];
            [str appendString:@"/"];
        }
        
        if ([str hasSuffix:@"/"]) {
            [str deleteCharactersInRange:NSMakeRange(str.length - 1, 1)];
        }
        
        [str appendString:@"\n "];
        
    }
    return str;
}

@end
