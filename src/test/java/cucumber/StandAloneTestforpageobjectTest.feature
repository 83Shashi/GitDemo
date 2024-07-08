
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

Background:
		Given I landed on Ecommerce page
		

   @Regression
    Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And checkout product <productName>  and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | name  										| password 			|  productName		 |
      | shashikhmouli@gmail.com		|@Samkumar1418	|	 ZARA COAT 3 		 | 
      
      