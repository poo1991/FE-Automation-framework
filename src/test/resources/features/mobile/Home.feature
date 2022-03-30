#Author: poonam.rukmaya.p@walmart.com
#Feature: Login features.

  @Academy_Mobile_Home
Feature: Academy Mobile Home

  @Mobile @Mobile_Home_TC01
  Scenario Outline: Verify Home Screen Recommended Learning sections
    Given I login to Academy with "<username>", "<password>" and "<storeNum>"
    When I close Badge Certification Request pop up if present
    Then Verify Recommended Learning sections displayed

    Examples:
      | username | password | storeNum |
      | TLZITTE  | TTT115   | 00871    |

