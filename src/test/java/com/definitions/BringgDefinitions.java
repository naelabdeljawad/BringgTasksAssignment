package com.definitions;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pom.*;

import java.util.List;

import static com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter.getCurrentStep;

public class BringgDefinitions extends TestBase {

    private HomePage homePage;
    private TaskManagerPage taskManagerPage;
    private ResourcesPage resourcesPage;
    private DocsPage docsPage;
    private SearchPopup searchPopup;
    private HeaderComponent headerComponent;
    private SideBarComponent sideBarComponent;

    @Given("I open bringg home page")
    public void i_open_bringg_home_page() {
        homePage = new HomePage(chromeDriver);
        getCurrentStep().log(Status.INFO, "Opening bringg home page...");
        Assert.assertTrue(homePage.openBringgHomePage(), "Page is not open!");
        getCurrentStep().log(Status.INFO, "Home page is open!");
        Assert.assertTrue(homePage.isPageDisplayed());
    }

    @Given("I click developers portal link in home page")
    public void i_click_developers_portal_link_in_home_page() {
        getCurrentStep().log(Status.INFO, "Clicking Developers Portal...");
        Assert.assertTrue(homePage.clickDevelopersPortalLink());
        getCurrentStep().log(Status.INFO, "Clicked!");
    }

    @Given("I select {string} in side bar")
    public void i_select_in_side_bar(String string) {
        docsPage = new DocsPage(chromeDriver);
        Assert.assertTrue(docsPage.isPageDisplayed());
        getCurrentStep().log(Status.INFO, "Clicking Driver SDK for iOS...");
        Assert.assertTrue(docsPage.clickOnSideBarByText(string, SideBarComponent.SideBarSections.BRINGG_SDK));
        getCurrentStep().log(Status.INFO, "Clicked!");
    }

    @Given("I open the Search Box and type {string}")
    public void i_open_the_Search_Box_and_type(String string) {
        headerComponent = new HeaderComponent(chromeDriver);
        getCurrentStep().log(Status.INFO, "Clicking search box...");
        Assert.assertTrue(headerComponent.clickSearch());
        getCurrentStep().log(Status.INFO, "Clicked!");

        searchPopup = new SearchPopup(chromeDriver);
        Assert.assertTrue(searchPopup.isPageDisplayed());
        getCurrentStep().log(Status.INFO, "Sending ".concat(string).concat("text to search..."));
        Assert.assertTrue(searchPopup.setTextToSearch(string));
        getCurrentStep().log(Status.INFO, "Clicking first result...");
        Assert.assertTrue(searchPopup.clickOnResultByIndex(0));
        getCurrentStep().log(Status.INFO, "Clicked!");
    }

    @Given("I verify tasksManager Property table information")
    public void i_verify_tasksManager_Property_table_information() {
        taskManagerPage = new TaskManagerPage(chromeDriver);
        String[] expectedContent = {"tasksManager Property", "Functionality", "currentTask", "OptionalIf the user is currently working on a task, this property returns a Bringg Task data type. If the current user is not working on a task, the property returns nil.Note: Bringg data types are immutable."};
        getCurrentStep().log(Status.INFO, "Getting Task Manager table content...");
        Assert.assertTrue(taskManagerPage.isPageDisplayed());
        List<String> list = taskManagerPage.getTaskManagerTableContent();
        getCurrentStep().log(Status.INFO, "Got content!");

        for (int i = 0; i < expectedContent.length; i++) {
            Assert.assertEquals(list.get(i), expectedContent[i]);
        }
        getCurrentStep().log(Status.INFO, "Content verified!");
    }

    @Given("I click on tasksManager Property table {string} link")
    public void i_click_on_tasksManager_Property_table_link(String string) {
        getCurrentStep().log(Status.INFO, "Clicking Task link...");
        Assert.assertTrue(taskManagerPage.clickTaskLink());
        getCurrentStep().log(Status.INFO, "Clicked!");
    }

    @When("I verify Task table information")
    public void i_verify_Task_table_information() {
        resourcesPage = new ResourcesPage(chromeDriver);
        getCurrentStep().log(Status.INFO, "");
        Assert.assertTrue(resourcesPage.isPageDisplayed());

        String[] expectedContent = {"Id Int", "activeWaypointId Int", "asap boolean", "externalId Int", "priority String", "status Int", "title String", "waypoints Object"};
        getCurrentStep().log(Status.INFO, "Getting Task table content...");
        List<String> list = resourcesPage.getTaskTableContent();
        getCurrentStep().log(Status.INFO, "Got content!");

        for (int i = 0; i < expectedContent.length; i++) {
            Assert.assertEquals(list.get(i), expectedContent[i]);
        }
        getCurrentStep().log(Status.INFO, "Content verified!");
    }

    @Given("I close the browser")
    public void i_close_the_browser() {
        getCurrentStep().log(Status.INFO, "Closing chrome driver...");
        closeDriver();
        getCurrentStep().log(Status.INFO, "Chrome driver is closed!");
    }

}
