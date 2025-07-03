package com.niudada;

import com.niudada.constants.EmojiConstants;
import com.niudada.observer.PhoneApp;
import com.niudada.observer.RadioStation;
import com.niudada.observer.SmartDevice;
import com.niudada.subject.Weather;
import com.niudada.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        //注册观察者
        Weather weather = new Weather();
        weather.addObserver(new PhoneApp());
        weather.addObserver(new RadioStation());
        weather.addObserver(new SmartDevice());

        // 模拟天气变化
        for (int i = 0; i < 5; i++) {
            // 模拟日期变化
            log.info(DateUtil.getFutureDate(i) + "日" + EmojiConstants.DUSK + "通知：");
            weather.timePasses();
            System.out.println();
        }
    }
}