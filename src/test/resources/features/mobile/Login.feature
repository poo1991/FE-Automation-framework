#Author: poonam.rukmaya.p@walmart.com
#Feature: Login features.
Feature: Academy Mobile Login

  @Mobile @Mobile_Login_TC01 @Mobile_Login
  Scenario Outline: Verify login functionality
    Given I login to Academy with "<username>", "<password>" and "<storeNum>"
    Then Academy Footer contains Home and Library
    And Academy Header contains Menu, logo and Language

    Examples:
      | username | password | storeNum |
      | TLZITTE  | TTT115   | 00871    |


  @Mobile @Mobile_Login_TC02 @Mobile_Login
  Scenario Outline: Verify Invalid login functionality
    Given I login to Academy with "<username>", "<password>" and "<storeNum>"
    Then Login Error message "<errorMessage>" is displayed

    Examples:
      | username | password | storeNum | errorMessage                                                                |
      | TLZITTE  | TTT11    | 00871    | We didn't recognize the username or password you entered. Please try again. |

