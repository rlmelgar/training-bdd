Feature: Get a list of all active transmission stored

  Background:
    Given url baseUrl
    Given path '/v1/transmissions'
    * def transmissionResponse = read('classpath:karate/transmission/transmissionResponse.json')

  @first
  Scenario: Get an active transmission stored
    When method GET
    Then status 200
    And print response
    And match response == '#[1]'
    And match each $[*] == {"id": "#string", "spaceship": {"name": "#string","type": "#string","navPoint": "#string","cargo": "##string","crew": "#number"}}

  @second
  Scenario: Get an active transmission stored
    When path 'v1', 'transmissions'
    And method GET
    Then status 200
    And print response
    And match response == '#[1]'
    And match each $[*] == transmissionResponse
    And match response[*].spaceship.name contains 'Ship name'
