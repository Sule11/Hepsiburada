package com.hepsiburada.pages;

import com.hepsiburada.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//input[@type='text']")
    public WebElement searchBox;

    @FindBy(xpath = "//div[@class='SearchBoxOld-buttonContainer']")
    public WebElement searchBtn;

    @FindBy(css = "div#myAccount")
    public WebElement Account;

    @FindBy(xpath = "//span[@title='Giriş Yap']")
    public WebElement AccountLogIn;



}
