Feature: Adding Item to cart Function

  Background:
    Given the user navigates to the url
    And searches an item with the search box "samsung tv"


  Scenario: Adding item to cart and deleting item from cart
    And user scrolls down to the bottom of the page
    And user clicks a page number button 2
    And user clicks on the 1 item on the page
    And adds the item to cart
    Then a page with "Ürün sepetinizde" message should be displayed


  Scenario:Deleting item from cart
    And user clicks on the 6 item on the page
    And adds the item to cart
    When user navigates to cart
    And clicks the trash button
    Then "Sepetin şu an boş" message should be displayed

    @wip
    Scenario: Deleting item from cart and adding the item
      And user clicks on the 3 item on the page
      And adds the item to cart
      When user navigates to cart
      And clicks the trash button
      And user navigates back to shopping page "samsung tv"
      And user finds the removed item
      And adds the item to cart
      Then a page with "Ürün sepetinizde" message should be displayed








