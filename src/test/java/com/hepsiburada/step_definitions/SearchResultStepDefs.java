package com.hepsiburada.step_definitions;

import com.hepsiburada.pages.SearchResultPage;
import com.hepsiburada.utilities.ConfigurationReader;
import com.hepsiburada.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class SearchResultStepDefs {

    SearchResultPage searchResultPage = new SearchResultPage();

    @Given("the user navigates to the url")
    public void the_user_navigates_to_the_url() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @And("searches an item with the search box {string}")
    public void searchesAnItemWithTheSearchBox(String string) {
        searchResultPage.searchItem(string);
        searchResultPage.clickCategory();
    }

    @Then("page title name should match the searched item name")
    public void pageTitleNameShouldMatchTheSearchedItemName() {
        Assert.assertEquals(Driver.get().getTitle(),
                searchResultPage.getPageFullTitle());
    }

    @Then("Items should be sorted from cheapest to expensive")
    public void items_should_be_sorted_from_cheapest_to_expensive() {
        searchResultPage.clickCategory();
        searchResultPage.clickFiyatArtan();
        List<Integer> allPrices = searchResultPage.createPriceList();
        for (int m = 0; m < allPrices.size() - 1; m++) {
            Assert.assertTrue("All product are listed from cheap to expensive",
                    allPrices.get(m) <= allPrices.get(m + 1));
        }

    }
    @Then("Items should be sorted from most expensive to cheapest")
    public void items_should_be_sorted_from_most_expensive_to_cheapest() {
        searchResultPage.clickCategory();
        searchResultPage.clickFiyatAzalan();
        List<Integer> allPrices=searchResultPage.createPriceList();
        for (int m = 0; m < allPrices.size() - 1; m++) {
            Assert.assertTrue("All product are listed from expensive to cheap",
                    allPrices.get(m) >= allPrices.get(m + 1));
        }

    }


    @Then("Items should be sorted according to number of reviews")
    public void items_should_be_sorted_according_to_number_of_reviews() {
        searchResultPage.clickCategory();
        searchResultPage.clickDegerlandirmeSayisiBtn();
        List<Integer> reviewList =searchResultPage.createReviewNumbersList();
        for (int i = 0; i <reviewList.size()-1; i++) {
            Assert.assertTrue("products are listed from most reviewed to least reviewed",
                    reviewList.get(i) >= reviewList.get(i + 1));
        }
        }

    }
