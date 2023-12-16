A set of notes, references, how-tos and quick instructions for the functional test automation solution for FTB application

# Getting Started
## Set up your work-station
1. Install [Java v>=17](https://www.oracle.com/java/technologies/downloads/#java17).
2. Install [Maven](https://maven.apache.org/install.html)
3. [Optional] Install [Allure CLI](https://docs.qameta.io/allure-report/#_commandline)

## Set up the project.
1. Clone the project's repository:
````
git clone TODO-NEED-THE-REPO-LINK !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
````
2. Create local test execution profile -  let's call it ``lcl``
- create ``src/main/resources/application-lcl.yml`` file. Note, you could copy-rename-past existing ``application.yml``
- provide valid values for ``datasourse.password``, ``datasourse.username``,  ``datasourse.url`` etc properties. See the how-to below for some hints on that.
- alter other settings as needed.

## Project overview
The test automation solution is a Java+SpringBoot application which uses Maven as a builder and TestNG as Unit-test framework(allows to structure and run the tests).
Spring-data is used to access DB(read-write).
RestAssured is used as special HTTP client tool.
Selenium WEbDriver is used to support GUI-based tests.
Functional tests are executed as a part of regular Maven cycle (`test` phase).
As a result of test execution a plain-text log file and Allure-results are generated.

## Project structure
1. `src/test/java` - all the tests are there. They are more or less structured by functionality/features. E.g. ``gui/admin/AdminLoginTest.java``
2. `src/test/java/.../api` - is the root for API tests while `src/test/java/.../gui` is the root for GUI tests.
3. `src/main/resources` - contains configuration files including test execution profiles(`application-<NAME>.yml`). Profiles allow to execute the tests against different environments etc. More on profiles - [Spring profiles](https://www.baeldung.com/spring-profiles)
4. `src/main/java` - contains all the services code to support all the tests, see below.
    - `..ftbtests.config` package contains Spring application configuration (`AppConfig`) and test data providers (`TestDataConfig` - allow to access property values from `application-*.yml` files)
    - `..ftbtests.data` package contains test data generator (`DataGen`, able to generate and persist test data) and `ModelTransformer` class which helps to convert entity classes to DTOs etc. Also misc data converters are stored there.
    - `..ftbtests.gui` package is the place for PageObjects and GUI-test steps (e.g. `NavigationSteps.java`).
    - `..ftbtests.api` package contains API-test steps.
    - `..ftbtests.misc` package contains miscellaneous auxiliary classes. Also, you could find solution-wide constants defined there.
    - `..ftbtests.model` package contains classes which model DB entities and API request/response's data.
    - `..ftbtests.repository` package contains low-level classes to work with DB tables (read/write from/to particular tables).
    - `..ftbtests.service` package contains more high-level classes to work with DB records. Note, DB operations are configured to be transactional.

# How-to ...
## ... run the tests locally using IDE.
1. Specify ``lcl`` Spring profile using ``spring.profiles.active`` flag. To do that - go to Edit configurations -> select run configuration -> add the flag to VM Options:
````
 -Dspring.profiles.active=lcl
````
2. Save and run.
3. Note, you could edit the IDE's TestNG-execution template so `-Dspring.profiles.active=lcl` would be added automatically.

## ... run all tests using Maven.
1. CD into the project's root directory. I.e. the project's ``pom.xml`` shall be there.
2. Run the test using Maven's ``clean test`` goals. Note, you do not really need anything else in case of a local run and assuming you do have ``lcl`` profile configured since ``lcl`` is the default one.
````
mvn clean test
````
or you could specify a profile to use:
````
mvn clean test -P<profile_name>
e.g.
mvn clean test -Plcl
or
mvn clean test -PRemote
````

## ... a group or groups of tests using Maven.
1. CD into the project's root directory. I.e. the project's ``pom.xml`` shall be there.
2.  Run the test using Maven's ``clean test`` goals.
    1. Run all the tests except ``API`` ones (using the default profile):
       ````
       mvn -Dgroups="" -DexcludedGroups="API" clean test
       ````
    2. Run all the tests included into the ``API.REST`` group:
       ````
       mvn -Dgroups="API.REST" clean test
       ````
    3. Run all the tests included into the ``GUI.admin`` group:
       ````
       mvn -Dgroups="GUI.admin" clean test
       ````
    4. Run all the tests included into the ``GUI.smoke`` group except those one which are included into the ``integration`` group:
       ````
       mvn -Dgroups="GUI.smoke" -DexcludedGroups="integration" clean test
       ````
    5. Run all the tests from the ``GUI.agent``, ``GUI.customer`` and ``API.REST.aircrafts`` groups:
       ````
       mvn -Dgroups="GUI.agent, GUI.customer, API.REST.aircrafts" clean test
       ````

Note, the list of groups-in-use could be found at ``com.m2p.at.ftbtests.misc.Constants``. 


## ... generate Allure report
As soon as you have your test run completed:
````
mvn allure:serve
````
Note, the tests generate a plain-text log file as well, see ``ftb_tests.log``

## ... add a test for an API call.
Imaging the task is to add a test for some API call. What to do? Where to go? Who is guilty?
Assumptions:
1. The endpoint is `TODO !!!!!!!!!!!!!!!!` and it is related to `Aircrafts` REST resource.
2. There existing tests for the service. Those tests cover some other endpoints.

Steps:
1. Find corresponding class in the `steps` package(or its sub-package) i.e. `api/steps/aircrafts/AircraftsSteps.java`
2. Add methods to perform the API call(s) to the steps-class. I.e. if the endpoint is about `HTTP GET` so add corresponding method to the class. Use existing code as an example. Note, you may need to create corresponding Request/Response/DTO classes.
3. Sample method:
````
TODOTODOTOD TODO !!!!!!!!!!
````
4. If there is a need to read/write from/to the DB you may need then to add corresponding entity classes under `model/db`. You may need to add corresponding repository classes) under `repository` and a service(s) under `service`
5. As soon as you are ready with infrastructure classes you can add a test into corresponding test class i.e. `tests.../api/rest/AircraftsTest.java`
6. You can do the API call from a test like:
```
    aircraftsSteps.getById(aircraftsId);
```


# Technologies
- Java as a programming language. Rationale: one of the most popular languages for test automation, many open-source  tools and libraries are available, easy to find an expert. https://www.java.com/
- [SpringBoot as IoC container, Data Access etc framework](https://spring.io/projects/spring-boot)
- [Maven as a building tool. ](https://maven.apache.org/what-is-maven.html)
- [TestNG as a fundamental unit-test framework for the functional tests.](https://testng.org/doc/)
- [RestAssured as a REST API client tool.](https://rest-assured.io/)
- [SeleniumWebDriver as a GUI-testing tool.](https://www.selenium.dev/documentation/webdriver/)
- [WebDriverManager as a tool to simplify SeleniumWebDriver installation and configuration.](https://github.com/bonigarcia/webdrivermanager)
- [Allure as a reporting tool.](https://docs.qameta.io/allure-report/)
- [ModelMapper for Entity-to-DTO transformation.](http://modelmapper.org/getting-started/)
- [Lombok to make Java nicer, more laconic and more readable.](https://projectlombok.org/)
- 
## Potentially useful Technologies
- [Selenoide as a useful wrapper for SeleniumWebDriver](https://selenide.org/documentation.html)
- [Awaitiity for async calls and retries](https://github.com/awaitility/awaitility)


### Reference Documentation
* [Spring profiles](https://www.baeldung.com/spring-profiles)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.4/maven-plugin/reference/html/)

