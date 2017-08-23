Feature: Assets can be added and retrieved

  Users should be able to add assets in a Liferay Portal Instance where the
  PULPO Connector has been installed and be able to retrieve assets through
  the Assets Service

  Background: I have authenticated in Liferay Portal Instance

    Given I am in the Liferay main page
    And   I click in the Sign In link
    And   I enter test as email
    And   I enter test as password
    And   I click in the Sign In button
    And   There is no asset with the same title

  Scenario Outline: An Asset added on the client can be retrieved through the Assets Service
    Given I click in the Menu element
    And   I click in the Content element
    And   I click in the Web Content element
    And   I click in the plus element
    And   I click in the Basic Web Content element
    And   I enter <asset_title> as Web Content Title
    When  I click in the Publish button
    And   I wait for 5 seconds for the asset to be processed
    Then  The asset containing the entered title is visible in the list of assets on the client

    And   There are at least 1 more assets and at most 1 more assets in the backend with the entered title
    Then  The asset with the entered title can be found through the assets Service

    Examples:
      | asset_title |
      | Reykjavik   |