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


  Scenario Outline: Get all petitions from an non valid transmission stored
    Given a transmission <Status> stored with(out) <has_petitions>
    When recover all petitions of the transmission
    Then returns an empty list

    Examples: Status is active
      | Status   | has_petitions |
      | 'active' | 'false'       |

    Examples: Status is inactive
      | Status     | has_petitions |
      | 'inactive' | 'true'        |
      | 'inactive' | 'false'       |

  Scenario: Get all petitions but no transmission were found
    Given no transmission stored
    When recover all petitions of the transmission
    Then returns an error that indicates no transmission were found

  Scenario Outline: Get all petitions but the code format is invalid
    When recover all petitions of the transmission <code>
    Then returns an error that indicates the code format is invalid

    Examples:
      | code          |
      | 'asdas112123' |
      | 'inactive'    |
