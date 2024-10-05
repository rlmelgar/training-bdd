# language: en
@transmissionWithPetitions
Feature: Receive a transmission with a list of related petitions

  Scenario: Receive a transmission with its petitions
    When receive a transmission with petitions
    Then the stored transmission will be returned

  Scenario: Receive a second transmission for the same spaceship
    Given a stored transmission of spaceship named 'Pepe'
    When receive a transmission with petitions of spaceship named 'Pepe'
    Then return an error

  Scenario Outline: Receive an incomplete transmission
    When receive a transmission named <Spaceship> and has <n> petitions
    Then return an error

    Examples:
      | Case                         | Spaceship | n |
      | When name is empty           |           | 1 |
      | When there are not petitions | Pepe      | 0 |
