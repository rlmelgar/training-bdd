# language: en
Feature: Recover a list of petitions from an active transmission
  As a user of Head Spaceship
  I want to recover all petitions from a transmission
  In order to verify all petitions made by that spaceship

  Scenario Outline: Get all petitions from an active transmission stored which has petitions
    Given an <active> transmission stored with(out) <has_petitions>
    When recover all petitions of the transmission
    Then returns a list with all petitions

    Examples:
      | active | has_petitions |
      | 'true' | 'true'        |


  Scenario Outline: Get all petitions from an <status> transmission stored which has petitions <has_petitions>
    Given a transmission <status> stored with(out) <has_petitions>
    When recover all petitions of the transmission
    Then returns an empty list

    Examples:
      | status     | has_petitions |
      | 'active'   | 'false'       |
      | 'inactive' | 'true'        |
      | 'inactive' | 'false'       |

  Scenario Outline: Get all petitions <use_case>
    Given no transmission stored
    When recover all petitions of the transmission
    Then returns an error that indicates <error_message>

    Examples:
      | use_case                      | code  |  error_message              |
      | from nonexistent transmission | 33333 |  no transmission were found |
      | with an invalid code format   | 33333 |  the code format is invalid |