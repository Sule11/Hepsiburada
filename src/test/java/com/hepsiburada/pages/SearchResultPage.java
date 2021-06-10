package com.hepsiburada.pages;

import com.hepsiburada.utilities.BrowserUtils;
import com.hepsiburada.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//a[.='Artan Fiyat']")
    public WebElement fiyatArtan;

    @FindBy(xpath = "//a[.='Azalan Fiyat']")
    public WebElement fiyatAzalan;

    @FindBy(xpath = "//a[.='Değerlendirme Sayısı']")
    public WebElement degerlendirmeSayisiBtn;

    @FindBy(xpath = "//span[@class='number-of-reviews']")
    public List<WebElement> reviews;

    @FindBy(xpath = "//div[@class='product-detail']")
    public List<WebElement> productDetails;

    @FindBy(xpath = "//li[@id='categorySuggestion1']/a[1]")
    public WebElement chooseCategory;


    String searchedItemName;

    public void searchItem(String string) {
        searchBox.sendKeys(string);
        searchBtn.click();
        searchedItemName = string;

    }

    public String getPageFullTitle() {
        return searchedItemName + " - Hepsiburada";
    }

    public void clickCategory() {
        BrowserUtils.waitFor(3);
        BrowserUtils.waitForClickablility(chooseCategory, 10);
        chooseCategory.click();
    }

    public void clickFiyatArtan() {
        BrowserUtils.waitFor(3);
        BrowserUtils.waitForClickablility(fiyatArtan, 10);
        fiyatArtan.click();
    }

    public List<Integer> createPriceList() {
        int count = 2;
        By pageButton = null;

        List<Integer> allPrices = new ArrayList<>();

        do {

            for (int i = 0; i < productDetails.size(); i++) {
                By normalPrice = By.xpath("(//div[@class='product-detail'])[" + (i + 1) + "]/div/div[@class='price-value']");
                By extraSalePrice = By.xpath("(//div[@class='product-detail'])[" + (i + 1) + "]/div[@class='price-container highlight-badge  hb-pl-cn']/span");
                if (Driver.get().findElements(normalPrice).size() != 0) {
                    WebElement element = Driver.get().findElement(normalPrice);
                    BrowserUtils.scrollToElement(element);
                    int price1 = Integer.parseInt(element.getText().replaceAll("[., TL]", ""));
                    allPrices.add(price1);
                    System.out.println(price1);

                } else if (Driver.get().findElements(extraSalePrice).size() != 0) {
                    WebElement element2 = Driver.get().findElement(extraSalePrice);
                    BrowserUtils.scrollToElement(element2);
                    int price2 = Integer.parseInt(element2.getText().replaceAll("[., TL]", ""));
                    allPrices.add(price2);
                    System.out.println(price2);
                }

            }

            pageButton = By.xpath("//li/a[.='" + count + "']");
            if (Driver.get().findElements(pageButton).size() != 0) {
                Driver.get().findElement(pageButton).click();
            }

            count++;

        } while (Driver.get().findElements(pageButton).size() != 0);

        return allPrices;
    }

    public void clickFiyatAzalan() {
        BrowserUtils.waitFor(3);
        BrowserUtils.waitForClickablility(fiyatAzalan, 10).click();
    }


    public void clickDegerlandirmeSayisiBtn(){
        BrowserUtils.waitFor(3);
        BrowserUtils.waitForClickablility(degerlendirmeSayisiBtn,10);
        degerlendirmeSayisiBtn.click();
    }

    public List<Integer> createReviewNumbersList(){
        int count = 2;
        By pageButton=null;
        List<Integer> reviewList = new ArrayList<>();
        do {
            System.out.println("page no: "+(count-1));
            List<String> elementsText = BrowserUtils.getElementsText(reviews);
            System.out.println(elementsText);

            for(int i=0; i<elementsText.size(); i++) {
                reviewList.add(Integer.parseInt(elementsText.get(i).replaceAll("[()]","")));
            }

            System.out.println(reviewList);


            pageButton = By.xpath("//li/a[.='" + count + "']");
            if(Driver.get().findElements(pageButton).size()!=0){
                Driver.get().findElement(pageButton).click();
            }

            count++;

        }while (Driver.get().findElements(pageButton).size()!=0);

        return reviewList;



    }



    @FindBy(xpath = "(//div[@class='box product'])")
    public List<WebElement> allProducts;

    @FindBy(xpath = "//div[@class='product-detail']")
    public List<WebElement> allProductDetails;


    @FindBy(xpath = "//div/span[@class='totalCount']")
    public WebElement totalCountOfProducts;

    @FindBy(xpath = "//div[contains(text(),\"Ürün sepete eklendi\")]")
    public WebElement ProductAddedToCartConfirmationMessage;

    @FindBy(css = "div#pagination")
    public WebElement pageButtons;

    @FindBy(xpath = "//div/span[@class='checkoutui-SalesFrontCash-XeG2a']")
    public WebElement productIsInCart;

    public String productDetail;

    public void setProductDetail(int productNum){
        BrowserUtils.waitFor(3);
        BrowserUtils.scrollToElement(allProducts.get(productNum));
        productDetail=allProductDetails.get(productNum).getText();
    }

    @FindBy(xpath = "//button[.='Sepete git']")
    public WebElement popUpSepeteGit;
}
