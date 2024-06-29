package appiumIntro;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.CommonUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Task {


    /*
1-Setup your Automation
2-Click + Button
3-Send your Name and Task
4-Validate Important Task is visible,enable and not selected.
5-Select important Task box button
6-Click Ok Button and Validate Name and Task shows up correctly on the list
7-Click X button and Ok Button(uio)
8-Validate the message is "The list does not contain any element !"
9-Proud of yourself
 */

    @Test
    public void validateToDoListInformation() throws MalformedURLException {
        //1-File Location
        File file=new File("src/test/resources/app-debug.apk");
        //2-Setting Up Desired Capabilities
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName","sdk_gphone64_arm64");
        desiredCapabilities.setCapability("platformName","Android");
        desiredCapabilities.setCapability("app",file.getAbsolutePath());
        desiredCapabilities.setCapability("newCommandTimeOut",600);
       //3-Setting Up Server Connection
        URL appiumServer=new URL("http:0.0.0.0:4723/wd/hub");

        //4-Create a driver and provide these settings
        AndroidDriver<AndroidElement> androidDriver=new AndroidDriver<>(appiumServer,desiredCapabilities);

        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        AndroidElement popUpOk=androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"));
        CommonUtils.elementTap(androidDriver,popUpOk);

        AndroidElement addButton=androidDriver.findElement(By.id("fr.stevenfrancony.mytodolist:id/addButton"));
        CommonUtils.elementTap(androidDriver,addButton);

        AndroidElement name=androidDriver.findElementByAndroidUIAutomator("text(\"Name...\")");
        name.sendKeys("Ahmet");

        AndroidElement task=androidDriver.findElementByXPath("//android.widget.EditText[@text=\"Task...\"]");
        task.sendKeys("Shopping");

        AndroidElement box=androidDriver.findElement(By.className("android.widget.CheckBox"));
        Assertions.assertTrue(box.isDisplayed() && box.isEnabled() && !box.isSelected());
        box.click();

        AndroidElement okButton=androidDriver.findElement(By.id("android:id/button1"));
        okButton.click();

        AndroidElement TaskDisplay=androidDriver.findElement(By.xpath("//android.widget.ListView[@resource-id=\"fr.stevenfrancony.mytodolist:id/list\"]/android.widget.RelativeLayout[2]"));
        Assertions.assertTrue(TaskDisplay.isDisplayed());

//        AndroidElement nameOfTaskDisplay=androidDriver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"fr.stevenfrancony.mytodolist:id/pseudo\" and @text=\"Ahmet\"]"));
        AndroidElement nameOfTaskDisplay=CommonUtils.AndroidUIAutomator(androidDriver,"Ahmet");
        Assertions.assertEquals("Ahmet",nameOfTaskDisplay.getText());

        AndroidElement xButton=androidDriver.findElement(By.id("fr.stevenfrancony.mytodolist:id/clearButton"));
        xButton.click();

        AndroidElement ouiButton=androidDriver.findElementByXPath("//android.widget.Button[@resource-id=\"android:id/button1\"]");
        CommonUtils.elementTap(androidDriver,ouiButton);

        AndroidElement message=androidDriver.findElement(By.id("fr.stevenfrancony.mytodolist:id/list_status"));
        Assertions.assertEquals("The list doesn't contain any element !",message.getText());

        androidDriver.quit();

    }











}
