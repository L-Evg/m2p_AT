package com.m2p.at.ftbtests.api.rest.v0;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.m2p.at.ftbtests.api.rest.BaseRestApiTest;
import com.m2p.at.ftbtests.api.rest.v0.model.AircraftDto;
import com.m2p.at.ftbtests.api.rest.v0.model.ApiResponse;
import com.m2p.at.ftbtests.api.rest.v0.model.CreateAircraftDto;
import com.m2p.at.ftbtests.api.rest.v0.steps.AircraftSteps;
import com.m2p.at.ftbtests.data.model.Aircraft;
import com.m2p.at.ftbtests.data.service.AircraftService;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.m2p.at.ftbtests.data.Utils.provideAlteredValue;
import static com.m2p.at.ftbtests.data.Utils.getRandomItem;
import static com.m2p.at.ftbtests.misc.Constants.*;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * A class for the REST API /aircrafts resource related tests.
 */
@Slf4j
@SpringBootTest
@Epic("API.REST V0")
@Feature("Aircafts resource")
@Story("CRUD Aircrafts")
public class AircraftsTest extends BaseRestApiTest {
    @Autowired
    private AircraftSteps steps;

    @Autowired
    private AircraftService service;

    @BeforeClass(alwaysRun = true)
    public void setupAircraftsTests() {
        steps.setRequestSpec(buildReqSpec());
    }

    @Test(groups = {API, API_REST, API_REST_AIRCRAFTS})
    @Severity(SeverityLevel.CRITICAL)
    // [WARNING] DO NOT TAKE THIS AS A SAMPLE! Discuss what is wrong/what could be improved.
    public void getAircraftByIdTest() {
        // Act
        /// Hard-coded ID value - fast and bad. A better option would be to place it to a property file - try it yourself!
        int id = 12;
        var actualResponse = steps.getById(id);

        // Assert
        assertThat(actualResponse)
                .as("Get-Aircraft response was null for a reason.")
                .isNotNull();
        assertThat(actualResponse.getAircraftId())
                .as("Get-Aircraft response contained wrong Id.")
                .isEqualTo(id);
        assertThat(actualResponse.getId())
                .as("Get-Aircraft response contained wrong Id.")
                .isEqualTo(id);
        assertThat(actualResponse.getModel())
                .as("Get-Aircraft response contained wrong Model.")
                .isEqualTo("Catalina");
        assertThat(actualResponse.getNumberOfSeats())
                .as("Get-Aircraft response contained wrong N-of-seats.")
                .isEqualTo(450);
        assertThat(actualResponse.getManufacturer())
                .as("Get-Aircraft response contained wrong Manufacturer.")
                .isEqualTo("Catalina");
    }

    @Test(description = "Get an aircraft data by random Id",
            groups = {API, API_REST, API_REST_SMOKE, API_REST_AIRCRAFTS})
    @Severity(SeverityLevel.CRITICAL)
    public void getAircraftByRandomIdTest() {
        // Arrange
        var aircraft = getRandomItem(service.getAll()); // Read a random record from the DB.
        var expectedDto = converter.aircraftToDto(aircraft); // Convert the data from DB representation into the API's one.

        // Act
        var actualResponse = steps.getById(aircraft.getAircraftId());

        // Assert
        verifyGetResponse(expectedDto, actualResponse);
    }

    @Test(description = "Get a list of aircrafts by model name.",
            groups = {API, API_REST, API_REST_AIRCRAFTS})
    @Severity(SeverityLevel.NORMAL)
    public void getAircraftsByModelNameTest() {
        // Arrange
        var allRecords = service.getAll(); // Read all the DB records. Note, numerous DB records would spoil the performance.
        var target = getRandomItem(allRecords); // Read a random record from the DB.
        var expectedDtos = allRecords.stream()
                .filter(aircraft -> aircraft.getModel().equals(target.getModel())) // Find aircrafts of the target model
                .map(aircraft -> converter.aircraftToDto(aircraft))// Convert all found records into DTOs
                .collect(toList());

        // Act
        var actualResponse = steps.getAllByModelName(target.getModel());

        // Assert
        verifyGetAllResponse(expectedDtos, actualResponse);
    }

    //TODO(TASK-FOR-STUDENTS): Cover /manufacturer/{manufacturerName} using getAircraftsByModelNameTest() as an example.

    @Test(description = "Create an aircraft.", groups = {API, API_REST, API_REST_SMOKE, API_REST_AIRCRAFTS})
    @Severity(SeverityLevel.CRITICAL)
    public void createAircraftTest() throws JsonProcessingException {
        // Arrange
        var inputData = CreateAircraftDto.of()
                .model(faker.aviation().aircraft())
                .manufacturer(faker.vehicle().manufacturer())
                // 117 - is a magic number and generally that is not good. Here - just an upper bound.
                .numberOfSeats(rnd.nextInt(117) + 1) // #nextInt() could return 0.
                .build();
        var expectedDto = AircraftDto.of()
                .model(inputData.getModel())
                .manufacturer(inputData.getManufacturer())
                .numberOfSeats(inputData.getNumberOfSeats())
                .build();

        // Act
        var createResponse = steps.create(inputData);
        //[DISCUSS]: What if we run the test over an over again? What would we get in the DB in a year?

        // Assert
        verifyCreateResponse(expectedDto, createResponse);
        // Ok, we could stop here but let's check the DB just in case. Any other options?
        verifyDbRecordsUponCreate(expectedDto, createResponse.getId());

    }

    @Test(description = "Update an aircraft.", groups = {API, API_REST, API_REST_SMOKE, API_REST_AIRCRAFTS})
    @Severity(SeverityLevel.CRITICAL)
    public void updateAircraftTest() throws JsonProcessingException {
        // Arrange
        var original = getRandomItem(service.getAll());
        var altered = buildUpdateRequest(original);
        var expectedDto = AircraftDto.of()
                .model(altered.getModel())
                .manufacturer(altered.getManufacturer())
                .numberOfSeats(altered.getNumberOfSeats())
                .build();

        // Act
        var updateResponse = steps.update(altered, original.getAircraftId());

        // Assert
        verifyCreateResponse(expectedDto, updateResponse);
        verifyDbRecordsUponCreate(expectedDto, updateResponse.getId());
    }

    @Test(description = "Partially update an aircraft.", groups = {API, API_REST, API_REST_AIRCRAFTS})
    @Severity(SeverityLevel.NORMAL)
    public void patchAircraftTest() throws JsonProcessingException {
        // Arrange
        var original = getRandomItem(service.getAll());

        var newModel = provideAlteredValue("!PTCHD!", original.getModel());

        var replaceOp = new ReplaceOperation(JsonPointer.of("model"), TextNode.valueOf(newModel));
        var patchData = new JsonPatch(List.of(replaceOp));
        var expectedDto = AircraftDto.of()
                .model(newModel)
                .manufacturer(original.getManufacturer())
                .numberOfSeats(original.getNumberOfSeats())
                .build();

        // Act
        var updateResponse = steps.patch(patchData, original.getAircraftId());

        // Assert
        verifyCreateResponse(expectedDto, updateResponse);
        verifyDbRecordsUponCreate(expectedDto, updateResponse.getId());
    }

    @Test(description = "Delete an aircraft.",
            groups = {API, API_REST, API_REST_AIRCRAFTS})
    @Severity(SeverityLevel.NORMAL)
    public void deleteAircraftTest() {
        // Arrange
        var victim = getRandomItem(service.getAll());
        log.info("About to delete\n{}", victim);

        // Act
        var actualResponse = steps.delete(victim.getAircraftId());

        log.info("DEL \n{}", actualResponse);

        // Assert
        verifyDeleteResult(victim.getAircraftId(), actualResponse);
    }

    // TODO: Could be made more generic, moved to a common location and re-used.
    @Step("Verify Get-Aircraft response.")
    private void verifyGetResponse(AircraftDto expected, AircraftDto actual) {
        log.info("Verify Get-Aircraft response.");
        assertThat(actual)
                .as("Seems Get-Aircraft call returned some unexpected data.")
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expected);
    }

    @Step("Verify Get-All-Aircrafts response.")
    private void verifyGetAllResponse(List<AircraftDto> expected, List<AircraftDto> actual) {
        log.info("Verify Get-All-Aircraft response.");
        assertThat(actual)
                .as("Seems Get-All-Aircrafts call returned some unexpected data.")
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expected);
    }

    @Step("Verify Create-Aircrafts response.")
    private void verifyCreateResponse(AircraftDto expected, AircraftDto actual) {
        log.info("Verify Create-Aircraft response.");
        verifyCreateResponseIgnoringSomeFields("Seems Create-Aircraft call returned some unexpected data.",
                expected, actual);
        assertThat(actual.getAircraftId())
                .as("Seems Create-Aircraft call returned some unexpected data - wrong id/aircraftId.")
                .isGreaterThan(0);
        assertThat(actual.getFlightIds())
                .as("Seems Create-Aircraft call returned some unexpected data - Flights expected to be empty.")
                .isEmpty();
    }

    private void verifyCreateResponseIgnoringSomeFields(String note, AircraftDto expected, AircraftDto actual) {
        assertThat(actual)
                .as(note)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                // Ignore fields which are auto-generated or will be filled in later.
                .ignoringFields("id", "aircraftId", "flights")
                .isEqualTo(expected);
    }

    @Step("Verify the DB upon Create-Aircrafts call.")
    private void verifyDbRecordsUponCreate(AircraftDto expected, long id) {
        log.info("Verify the DB upon Create-Aircraft call.");

        var dbRecordOptional = service.getOptionallyById(id);

        assertThat(dbRecordOptional)
                .as("Seems there is no DB entry for Id=%s upon Create-Aircraft API call.", id)
                .isPresent();

        var dbRecordDto = converter.aircraftToDto(dbRecordOptional.orElseThrow());
        verifyCreateResponseIgnoringSomeFields("Seems wrong data was saved to DB upon Create-Aircraft API call.",
                expected, dbRecordDto);
    }

    @Step
    private void verifyDeleteResult(long deletedRecordId, ApiResponse actualResponse) {
        assertThat(actualResponse.getMessage())
                .as("Delete response's message should not be empty.")
                .isNotEmpty();
        assertThat(actualResponse.getDetails())
                .as("Delete response's details should not be empty.")
                .isNotEmpty();
        assertThat(actualResponse.getDetails().get("Id"))
                .as("Delete response's details should contain 'Id' key entry having %s value.", deletedRecordId)
                .isNotEmpty()
                .isEqualTo(String.valueOf(deletedRecordId));

        var dbRecord = service.getOptionallyById(deletedRecordId);
        assertThat(dbRecord)
                .as("Seems Delete failed since there is a db record for id=%s.", deletedRecordId)
                .isNotEmpty()
                .isNotPresent();
    }

    private CreateAircraftDto buildUpdateRequest(Aircraft original) {
        log.info("A record to update\n{}", original);

        val upd = "!UPD!";
        var newModel = provideAlteredValue(upd, original.getModel());
        var newManufacturer = provideAlteredValue(upd, original.getManufacturer());

        return CreateAircraftDto.of()
                .model(newModel)
                .manufacturer(newManufacturer)
                .numberOfSeats(rnd.nextInt(266) + 1) // #nextInt() could return 0.
                .build();
    }
}
