package pom;

import com.CommonUtils;
import com.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class DocsPage extends AbstractPage {

    @FindBy(linkText = "Developer’s Portal")
    @CacheLookup
    private WebElement developersPortalLink;

    @FindBy(id = "hub-container")
    @CacheLookup
    private WebElement pageContainer;
    private SideBarComponent sideBar;

    public DocsPage(WebDriver chromeDriver) {
        super(chromeDriver);
        PageFactory.initElements(chromeDriver, this);
    }

    public boolean clickOnSideBarByText(String string, SideBarComponent.SideBarSections section) {
        try {
            CommonUtils.sleep(2000);
            sideBar = new SideBarComponent(chromeDriver);
            sideBar.clickOnSideBarByText(string, section);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hub-container"))).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
