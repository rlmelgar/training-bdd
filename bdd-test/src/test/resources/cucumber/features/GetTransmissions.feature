# language: en
@transmission
Feature: Get a list of all active transmission stored
  As a user of Head Spaceship
  I want to recover all transmissions
  In order to know all spaceship current transmissions

  Scenario: Get all active transmission stored
    Given an active transmission stored with petitions
    When recover all transmissions
    Then returns a list with all transmissions
    