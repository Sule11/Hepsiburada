package com.hepsiburada.pages;

import com.hepsiburada.utilities.BrowserUtils;
import com.hepsiburada.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage{


    @FindBy(css = "button#addToCart")
    public WebElement addToCart;

    @FindBy(css = "a.sf-OldMyAccount-2OvEz.sf-OldMyAccount-3TYPj")
    public WebElement myCart;

    @FindBy(xpath = "//a[.='Çıkış Yap']")
    public WebElement logOut;

    public void clickPageNumber(int num){
        BrowserUtils.waitFor(3);
        WebElement pageNumber= Driver.get().findElement(By.xpath("//a[.='"+num+"']"));
        pageNumber.click();
    }




}
