package apk.qa.store.tests;

import apk.qa.store.pages.MainPage;
import apk.qa.store.pages.ProductPage;
import org.testng.annotations.Test;

public class CartTest extends TestBase{


    @Test
    public void validateCartFunctionality(){
        MainPage mainPage=new MainPage(androidDriver);
        ProductPage productPage=new ProductPage(androidDriver);

        mainPage.login(androidDriver,"Belgium","Suzan");
        productPage.addingProductToTheCart();
    }










}
