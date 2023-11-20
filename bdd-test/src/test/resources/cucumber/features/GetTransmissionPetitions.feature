# language: en
Feature: As a user of Head Spaceship
  I want to recover all petitions from a transmission
  In order to verify all petitions made by that spaceship

  Scenario: Get all petitions from an active transmission stored which has petitions
    Given an active transmission stored with petitions
    When recover all petitions of the transmission
    Then returns a list with all petitions

  Scenario: Get all petitions from an active transmission stored which has not petitions
    Given an active transmission stored without petitions
    When recover all petitions of the transmission
    Then returns an empty list

  Scenario: Get all petitions from an inactive transmission stored which has petitions
    Given an inactive transmission stored with petitions
    When recover all petitions of the transmission
    Then returns an empty list

  Scenario: Get all petitions from an inactive transmission stored which has not petitions
    Given an inactive transmission stored without petitions
    When recover all petitions of the transmission
    Then returns an empty list

  Scenario: Get all petitions from nonexistent transmission
    Given no transmission stored
    When recover all petitions of the transmission
    Then returns an error that indicates no transmission were found

  Scenario: Get all petitions with an invalid code format
    When recover all petitions of the transmission
    Then returns an error that indicates the code format is invalid
