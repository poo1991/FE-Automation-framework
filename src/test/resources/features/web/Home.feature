#Author: poonam.rukmaya.p@walmart.com
#Feature: Home features.



@Home
Feature: Academy Home

  Background: Academy Login displayed
    Given Login Page of Academy is displayed

  @Web @Academy @HamburgerMenu @Smoke_Test @TC01_HamburgerMenu @Home
  Scenario Outline: Verify the Hamburger Menu for a TL Login
    Given HomePage is displayed after login with Username:"<userId>", Password:"<password>" and Store:"<storeNum>"
    When I close the Badge Certification modal in Academy Homepage
    And I click on Hamburger Menu icon
    Then User information is displayed in the Hamburger Menu for "<userName>"
    And Verify links in the Main Navigation for "TL"
    And Verify navigation on click of below links under Main Navigation in the Hamburger Menu
      | Hamburger_Menu_Links |
      | Library              |
      | My Learning          |
      | Teams                |
      | Home                 |
      | View Profile         |


    Examples:
      | userId  | password | storeNum | userName       |
      | TLZITTE | TTT115   | 00871    | Tamatha Zitter |

  @Web @Academy @HamburgerMenu @Smoke_Test @TC02_HamburgerMenu @Home
  Scenario Outline: Verify the Hamburger Menu for a TL Login
    Given HomePage is displayed after login with Username:"<userId>", Password:"<password>" and Store:"<storeNum>"
    When I close the Badge Certification modal in Academy Homepage
    And I click on Hamburger Menu icon
    Then User information is displayed in the Hamburger Menu for "<userName>"
    And Verify links in the Main Navigation for "TL"
    And Verify navigation on click of below links under Main Navigation in the Hamburger Menu
      | Hamburger_Menu_Links |
      | Library              |
      | My Learning          |
      | Teams                |
      | Home                 |
      | View Profile         |


    Examples:
      | userId  | password | storeNum | userName       |
      | TLZITTE | TTT115   | 00871    | Tamatha Zitter |

