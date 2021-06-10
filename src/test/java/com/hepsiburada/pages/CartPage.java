package com.hepsiburada.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage{

    @FindBy(xpath = "//a[@class='delete_product_3DFC0']")
    public List<WebElement> removeProducts;

    @FindBy(xpath = "//div[@class='content_Z9h8v']/h1")
    public WebElement YourCartIsEmptyMessage;

    @FindBy(css = "div.action_box_close_2I9zg>a")
    public WebElement closeRemovedItem;

}
