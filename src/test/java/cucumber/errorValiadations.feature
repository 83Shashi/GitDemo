#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.

@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page
    When  Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is diaplayed

    Examples: 
      | name  										| password 			|  
      | shashikhmouli@gmail.com		| Samkumar1418	|	  
      