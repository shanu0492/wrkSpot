Feature: Get All countries

  Scenario: To get countries details wrkSpot
    Given user has access to endpoint "/countries/countries"
    When user hits the request
    Then user should get the response code 200
    And validate the response bt deserializing

