Feature: Contacts can be added and retrieved

  Users should be able to add contacts in a Liferay Portal Instance where the
  PULPO Connector has been installed and be able to retrieve contacts through
  the Contacts Service

  Background: I have authenticated in Liferay Portal Instance

    Given I am in the http://localhost:8080 url
    And   I click in the Sign In link
    And   I enter test as email
    And   I enter test as password
    And   I click in the Sign In button

  Scenario Outline: A Contact added on the client can be retrieved through the Contacts Service
    Given I click in the Menu element
    And   I click in the Control Panel element
    And   I click in the Users element
    And   I click in the Users and Organizations element
    And   I click in the plus element
    And   I enter <contact_screen_name> as Screen Name
    And   I enter <contact_email> as Email Address
    And   I enter <contact_first_name> as First Name
    And   I enter <contact_last_name> as Last Name
    When  I click in the Save button
    And   I wait for 5 seconds for the contact to be processed
    And   I click in the Users and Organizations element
    Then  The contact containing the entered Screen Name is visible in the list of contacts on the client

    And   There are at least 1 more contacts and at most 1 more contacts in the backend with the entered email

    Examples:
      | contact_screen_name |contact_email             |contact_first_name |contact_last_name|
      | julio               |julio.camarero@liferay.com|Julio              |Camarero         |