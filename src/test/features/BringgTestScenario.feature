Feature: This is a feature file

  Background:
    Given I open bringg home page

  Scenario: Bringg Test Scenario
    Given I click developers portal link in home page
    And I select "Driver SDK for iOS" in side bar
    And I open the Search Box and type "tasksManager"
    And I verify tasksManager Property table information
    And I click on tasksManager Property table "Task" link
    When I verify Task table information
    Then  I close the browser