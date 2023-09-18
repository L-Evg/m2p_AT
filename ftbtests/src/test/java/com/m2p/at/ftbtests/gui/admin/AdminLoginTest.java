package com.m2p.at.ftbtests.gui.admin;

import com.m2p.at.ftbtests.gui.BaseGuiTest;
import com.m2p.at.ftbtests.gui.pages.HomePage;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import static com.m2p.at.ftbtests.misc.Constants.GUI;
import static com.m2p.at.ftbtests.misc.Constants.GUI_ADMIN;
import static com.m2p.at.ftbtests.misc.Constants.GUI_SMOKE;
import static com.m2p.at.ftbtests.misc.Constants.GUI_LOGIN;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Epic("GUI.Admin features")
@Feature("Login")
@Story("Login-as-admin")
public class AdminLoginTest extends BaseGuiTest {

    @Test(groups = {GUI, GUI_SMOKE, GUI_ADMIN, GUI_LOGIN})
    @Severity(SeverityLevel.CRITICAL)
    public void loginAsAdminTest() {
        var loginPage = nav.openLogin();

        var homePageCandidate = loginPage.login(testData.getAdmin().getLogin(), testData.getAdmin().getPassword());

        verifyAdminHomePageIsOpened(homePageCandidate, testData.getAdmin().getLogin());
    }

    @Step("Verify the page to be a Home for the admin({expectedLoggedUserName})")
    private void verifyAdminHomePageIsOpened(HomePage candidate, String expectedLoggedUserName) {
        log.info("Verify admin Home page is opened for {}", expectedLoggedUserName);
        assertThat(candidate.isSomeoneLoggedIn())
                .as("Seems no user logged in upon the Admin-login")
                .isTrue();
        assertThat(candidate.getCurrentUserName())
                .as("Unexpected name of logged-in uesr upon the Admin-login")
                .endsWithIgnoringCase(expectedLoggedUserName);
        assertThat(candidate.isAirportsLinkVisible())
                .as("Seems the Airports link is not visible on the admin's Home page")
                .isTrue();
        assertThat(candidate.isFlightsLinkVisible())
                .as("Seems the Flights link is not visible on the admin's Home page")
                .isTrue();
    }
}

