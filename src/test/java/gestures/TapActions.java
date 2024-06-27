package gestures;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.CommonUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TapActions {

    @Test
    public void tabTest() throws MalformedURLException {
        File file=new File("src/test/resources/ApiDemos-debug-newVersion.apk");
        //Setup Connection with Inspector
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName","sdk_gphone64_arm64");
        desiredCapabilities.setCapability("platformName","Android");
        desiredCapabilities.setCapability("app",file.getAbsolutePath());
        desiredCapabilities.setCapability("newCommandTimeOut",600);

        //Setup Connection with Appium Server

        URL appiumServerUrl=new URL("http://0.0.0.0:4723/wd/hub");

        AndroidDriver<AndroidElement> androidDriver=new AndroidDriver<>(appiumServerUrl,desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        AndroidElement contentButton=androidDriver.findElementByAccessibilityId("Content");

        TouchAction touchAction=new TouchAction(androidDriver);

        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(contentButton))).perform();

        AndroidElement packagesButton=androidDriver.findElementByAndroidUIAutomator("text(\"Packages\")");

//        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(packagesButton))).perform();
        CommonUtils.elementTap(androidDriver,packagesButton);

    }

    @Test
    public void longPress() throws MalformedURLException {
        File file=new File("src/test/resources/ApiDemos-debug-newVersion.apk");
        //Setup Connection with Inspector
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName","sdk_gphone64_arm64");
        desiredCapabilities.setCapability("platformName","Android");
        desiredCapabilities.setCapability("app",file.getAbsolutePath());
        desiredCapabilities.setCapability("newCommandTimeOut",600);

        //Setup Connection with Appium Server

        URL appiumServerUrl=new URL("http://0.0.0.0:4723/wd/hub");

        AndroidDriver<AndroidElement> androidDriver=new AndroidDriver<>(appiumServerUrl,desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Views --> Expandable List --> Custom adaptor -->Validate Peoples Name is Displayed

        //NOTE:Please use different locaotr --> Please use taps common utils

        AndroidElement viewsButton=androidDriver.findElementByAccessibilityId("Views");
        CommonUtils.elementTap(androidDriver,viewsButton);

        AndroidElement expandableList=androidDriver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]"));
        CommonUtils.elementTap(androidDriver,expandableList);

        AndroidElement customerAdaptorButton=androidDriver.findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")");
        CommonUtils.elementTap(androidDriver,customerAdaptorButton);

        AndroidElement peopleNames=androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]"));
        Assertions.assertTrue(peopleNames.isDisplayed());

        TouchAction touchAction=new TouchAction(androidDriver);
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(peopleNames))).perform();

        AndroidElement sampleMenu=androidDriver.findElementByXPath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample menu\"]");
        Assertions.assertEquals("Sample menu",sampleMenu.getText());
        androidDriver.navigate().back();
        androidDriver.navigate().back();
    }











}
