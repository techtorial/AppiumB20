package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class CommonUtils {

    public static void elementTap(AndroidDriver<AndroidElement> androidDriver,AndroidElement androidElement){
        TouchAction touchAction=new TouchAction(androidDriver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(androidElement))).perform();
    }

    public static void longPress(AndroidDriver<AndroidElement> androidDriver,AndroidElement androidElement){
        TouchAction touchAction=new TouchAction(androidDriver);
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(androidElement))).perform();
    }
}
