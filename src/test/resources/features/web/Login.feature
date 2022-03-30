#Author: poonam.rukmaya.p@walmart.com
#Feature: Login features.



@Login
Feature: Academy Login

  Background: Academy Login displayed
    Given Login Page of Academy is displayed

  @Web @Academy @Login @Smoke_Test @ScreenShot @TC01
  Scenario Outline: Verify login functionality with valid user name and password for TL
    When I login to Academy Web App with Username:"<userId>", Password:"<password>" and Store:"<storeNum>"
    And I close the Badge Certification modal in Academy Homepage
    Then Academy Homepage is displayed

    Examples:
      | userId  | password | storeNum |
      | TLZITTE | TTT115   | 00871    |
