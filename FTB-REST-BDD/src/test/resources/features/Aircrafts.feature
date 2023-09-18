Feature: Testing FTB REST API AIRCRAFTS resource
  Clients should be able to READ/CREATE/UPDATE/DELETE an aircraft record.

  Scenario: Get specific aircraft data by its ID
    Given FTB is up and running and the tests are configured
    When client gets details of Aircraft id=12
    Then aircraft data to be manufacturer='Catalina' and model='Catalina' and number of seats=450

  Scenario: Create an aircraft
    Given FTB is up and running and the tests are configured
    ## When Specify new item data and call the API
    ## Then CHECK!