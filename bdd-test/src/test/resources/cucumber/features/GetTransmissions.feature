# language: en
@transmission
Feature: Get a list of all active transmission stored

  Scenario: Get all active transmission stored
    Given an active transmission stored with petitions
    When recover all transmissions
    Then returns a list with all transmissions
