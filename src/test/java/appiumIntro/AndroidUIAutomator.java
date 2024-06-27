package appiumIntro;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidUIAutomator {

    @Test
    public void locatorTest() throws MalformedURLException {


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
        AndroidElement animationButton=androidDriver.findElementByAndroidUIAutomator("text(\"Animation\")");
        animationButton.click();

    }









}
