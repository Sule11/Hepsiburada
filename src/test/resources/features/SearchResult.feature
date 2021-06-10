Feature:Search result page

  Background:
    Given the user navigates to the url
    And searches an item with the search box "samsung tv"

  Scenario:Verify that the searched item name matches with the page title
    Then page title name should match the searched item name

  Scenario:Verify that user can sort the results according to "fiyat artan"
    Then Items should be sorted from cheapest to expensive

  Scenario:Verify that user can sort the results according to "fiyat azalan"
    Then Items should be sorted from most expensive to cheapest


  Scenario:Verify that user can sort the results according to "degerlendirme sayisi"
    Then Items should be sorted according to number of reviews





