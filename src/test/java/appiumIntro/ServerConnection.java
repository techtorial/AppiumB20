package appiumIntro;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServerConnection {

    @Test
    public void connectionTest() throws MalformedURLException {
        //It is time to install an application into your device
        File apkFile=new File("src/test/resources/ApiDemos-debug-newVersion.apk");

        //Appium Inspector Connection
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName","sdk_gphone64_arm64");
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("app",apkFile.getAbsolutePath());
        //APPIUM Server Connection
        URL appiumServerUrl=new URL("http://0.0.0.0:4723/wd/hub");

        AndroidDriver<AndroidElement> androidDriver=new AndroidDriver<>(appiumServerUrl,desiredCapabilities);

        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        AndroidElement appButton=androidDriver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"App\"]"));
        appButton.click();

        //Navigate to APp --> click Alarm --> Alarm Controller -->
        AndroidElement alarmButton=androidDriver.findElementByAccessibilityId("Alarm");
        alarmButton.click();

        AndroidElement alarmController=androidDriver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Alarm Controller\"]"));
        alarmController.click();

        List<AndroidElement> allControllers=androidDriver.findElements(By.className("android.widget.Button"));//3 elements

        for(AndroidElement controller:allControllers){
            System.out.println(controller.getText());
        }

        AndroidElement oneShotButton=androidDriver.findElementByAccessibilityId("One Shot Alarm");
        Assertions.assertEquals("One Shot Alarm",oneShotButton.getText());

        AndroidElement startButton=androidDriver.findElement(By.id("io.appium.android.apis:id/start_repeating"));
        Assertions.assertEquals("Start Repeating Alarm",startButton.getText());

        AndroidElement stopButton=androidDriver.findElementByXPath("//android.widget.Button[@content-desc=\"Stop Repeating Alarm\"]");
        Assertions.assertEquals("Stop Repeating Alarm",stopButton.getText());




    }





}
