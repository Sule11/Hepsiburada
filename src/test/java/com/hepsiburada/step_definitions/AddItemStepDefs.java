package com.hepsiburada.step_definitions;

import com.hepsiburada.pages.CartPage;
import com.hepsiburada.pages.HomePage;
import com.hepsiburada.pages.SearchResultPage;
import com.hepsiburada.utilities.BrowserUtils;
import com.hepsiburada.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class AddItemStepDefs {
    HomePage homePage = new HomePage();
    SearchResultPage searchResultPage=new SearchResultPage();
    CartPage cartPage=new CartPage();

    @When("user searches an item with the search box {string}")
    public void user_searches_an_item_with_the_search_box(String string) {
        BrowserUtils.waitFor(7);
        //Driver.get().navigate().refresh();
        try{
            BrowserUtils.waitForVisibility(homePage.searchBox,10);
            homePage.searchBox.sendKeys(string);
        }
        catch(StaleElementReferenceException e){
            homePage.searchBox.sendKeys(string);
        }
        BrowserUtils.waitFor(3);
        homePage.searchBtn.click();
    }
    @Then("Title should write the name of the product {string}")
    public void Title_should_write_the_name_of_the_product(String string) {
       BrowserUtils.waitFor(3);
       Assert.assertTrue(Driver.get().getTitle().contains(string));
    }
    @When("user scrolls down to the bottom of the page")
    public void user_scrolls_down_to_the_bottom_of_the_page() {
        BrowserUtils.waitFor(3);
        BrowserUtils.scrollToElement(searchResultPage.pageButtons);

    }
    @When("user clicks a page number button {int}")
    public void user_clicks_a_page_number_button(int num) {
        BrowserUtils.waitFor(3);
        homePage.clickPageNumber(num);
    }

    @When("user clicks on the {int} item on the page")
    public void user_clicks_on_the_item_on_the_page(int number) {
        searchResultPage.setProductDetail(number-1);
        BrowserUtils.waitFor(3);
        BrowserUtils.hover(searchResultPage.allProducts.get(number-1));
        searchResultPage.allProducts.get(number-1).click();
    }
    @When("adds the item to cart")
    public void adds_the_item_to_cart() {
        BrowserUtils.waitFor(3);
       BrowserUtils.waitForClickablility(homePage.addToCart, 20);
        homePage.addToCart.click();
    }
    @Then("a page with {string} message should be displayed")
    public void a_page_with_message_should_be_displayed(String string) {
        BrowserUtils.waitFor(5);
        if(searchResultPage.productIsInCart!=null){
            BrowserUtils.highlight(searchResultPage.productIsInCart);
            Assert.assertTrue(searchResultPage.productIsInCart.isDisplayed());
        }else{
            Assert.assertTrue(searchResultPage.ProductAddedToCartConfirmationMessage.isDisplayed());
        }
    }
    @When("user navigates to cart")
    public void user_navigates_to_cart() {

        BrowserUtils.waitFor(15);
        if(searchResultPage.popUpSepeteGit!=null){
            searchResultPage.popUpSepeteGit.click();
        }else{
            BrowserUtils.hover(homePage.myCart);
            BrowserUtils.waitForClickablility(homePage.myCart,30);
            homePage.myCart.click();
        }

    }
    @When("clicks the trash button")
    public void clicks_the_trash_button() {
        BrowserUtils.waitFor(3);
        if(cartPage.removeProducts.size()>0) {
            for (int i = 0; i < cartPage.removeProducts.size(); i++) {
                BrowserUtils.waitFor(5);
                BrowserUtils.hover(cartPage.removeProducts.get(i));
                BrowserUtils.waitFor(5);
                cartPage.removeProducts.get(i).click();
                BrowserUtils.waitFor(5);
                cartPage.closeRemovedItem.click();
            }
        }
    }
    @Then("{string} message should be displayed")
    public void message_should_be_displayed(String string) {
        BrowserUtils.waitFor(3);
        Assert.assertTrue(cartPage.YourCartIsEmptyMessage.getText().contains(string));
    }
    @When("user navigates back to shopping page {string}")
    public void user_navigates_back_to_shopping_page(String string) {
            BrowserUtils.waitFor(3);
            homePage.searchBox.sendKeys(string+ Keys.ENTER);
    }
    @When("user finds the removed item")
    public void user_finds_the_removed_item() {
        BrowserUtils.waitFor(3);

        String totalCountOfProducts = searchResultPage.totalCountOfProducts.getText().replace(".","");
        int totalCountOfProd = Integer.parseInt(totalCountOfProducts);

        int productsInOnePage = searchResultPage.allProducts.size();
        boolean foundProduct = false;

        for(int i=0; i<=totalCountOfProd/productsInOnePage;i++) {
            BrowserUtils.waitFor(3);
            WebElement eachPage=Driver.get().findElement(By.xpath("//li/a[.='"+(i+2)+"']"));

            for (int j = 0; j <searchResultPage.allProducts.size(); j++) {
                BrowserUtils.waitFor(3);
                BrowserUtils.scrollToElement(searchResultPage.allProducts.get(j));
                if (searchResultPage.allProducts.get(j).getText().contains(searchResultPage.productDetail)) {
                    searchResultPage.allProducts.get(j).click();

                    foundProduct = true;
                    break;
                }
            }
            if(!foundProduct){
                eachPage.click();
                BrowserUtils.waitFor(5);

            }else{
                break;
            }
        }
    }




}
