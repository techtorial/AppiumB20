package apk.qa.store.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class CartPage {

    public CartPage(AndroidDriver<AndroidElement> androidDriver){
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver, Duration.ofSeconds(10)),this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    List<AndroidElement> allProductNames;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    List<AndroidElement> allProductPrices;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    AndroidElement totalPrice;

    @AndroidFindBy(className = "android.widget.CheckBox")
    AndroidElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    AndroidElement visitStoreButton;

    public void productInformation(String firstProduct,String secondProduct,String firstProductPrice,String secondProductPrice){
        List<String> allNames= Arrays.asList(firstProduct,secondProduct);
        List<String> allPrices=Arrays.asList(firstProductPrice,secondProductPrice);

        for(int i=0;i<allProductNames.size();i++){
            Assert.assertEquals(allProductNames.get(i).getText().trim(),allNames.get(i));
            Assert.assertEquals(allProductPrices.get(i).getText().trim(),allPrices.get(i));
        }
        System.out.println(totalPrice.getText());
        double actualPrice=Double.parseDouble(totalPrice.getText().substring(2));
        double expectedPrice=Double.parseDouble(firstProductPrice.substring(1) +
                             Double.parseDouble(secondProductPrice.substring(1)));

        Assert.assertEquals(actualPrice,expectedPrice);

        Assert.assertTrue(checkBox.isDisplayed() && !checkBox.isSelected() && checkBox.isEnabled());

        checkBox.click();

        Assert.assertTrue(visitStoreButton.isDisplayed() && visitStoreButton.isEnabled());
    }











}
