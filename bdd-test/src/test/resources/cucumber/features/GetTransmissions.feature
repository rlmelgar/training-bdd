# language: en
@transmission
Feature: Get a list of all active transmission stored
  As a user of Head Spaceship
  I want to recover all transmissions
  In order to know all spaceship current transmissions

  Background:
    Given no transmission stored

  Scenario: Get an active transmission stored
    Given an active transmission stored with petitions
    And tree inactive transmissions stored with petitions
    When recover all transmissions
    Then returns a list with a transmission
    And the response was ok

  Scenario: Get two active transmission stored
    Given two active transmissions stored with petitions
    And one inactive transmission stored with petitions
    And tree inactive transmissions stored without petitions
    When recover all transmissions
    Then returns a list with two transmissions
    And the response was ok

  Scenario Outline: Get an active transmission stored
    Given <numDocsAct> active transmission stored <hasPetitionsAct> petitions
    And <numDocsInac> inactive transmissions stored <hasPetitionsInac> petitions
    When recover all transmissions
    Then returns a list with <numDocsAct> transmission
    And the response was ok

    Examples:
      | numDocsAct | hasPetitionsAct | numDocsInac | hasPetitionsInac |
      | 1          | true            | 3           | true             |
      | 3          | true            | 1           | true             |

  Scenario: Get only active transmissions stored
    Given the following transmissions stored
      | id | active | hasPetitions |
      | 10 | true   | false        |
      | 20 | false  | true         |
      | 30 | true   | false        |
      | 40 | true   | true         |

    When recover all transmissions
    Then returns a list with tree transmissions
    And the response was ok


    