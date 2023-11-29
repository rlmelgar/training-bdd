Feature: Get a list of all active transmission stored

  Background:
    Given url baseUrl
    * def transmissionPetitionResponse = read('classpath:karate/transmissionPetition/transmissionPetitionResponse.json')

  Scenario: Get transmission petitions from a transmission stored
    When path 'v1', 'transmissions', '112233', 'petitions'
    And method GET
    Then status 200
    And print response
    And match response == '#[3]'
    And match each $[*] == transmissionPetitionResponse
