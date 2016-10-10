import { NativeModules } from 'react-native'

export default class WxShare {
    static registerApp(key) {
         NativeModules.WxShare.registerApp(key)
    }

    static share(array) {
       NativeModules.WxShare.share(array) 
    }

    static shareText(arr) {
        NativeModules.WxShare.shareText(arr) 
    }
}
