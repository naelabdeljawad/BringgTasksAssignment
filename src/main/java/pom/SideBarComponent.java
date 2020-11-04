package pom;

import com.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SideBarComponent extends AbstractPage {

    public enum SideBarSections {
        GUIDELINES(0), BRINGG_SDK(27), FLEET_SELF_REGISTRATION(69);

        private final int sectionIndex;

        SideBarSections(int sectionIndex) {
            this.sectionIndex = sectionIndex;
        }

        public int getSectionStartIndex() {
            return this.sectionIndex;
        }
    }

    @FindBy(id = "hub-sidebar-content")
    @CacheLookup
    private WebElement pageContainer;

    public SideBarComponent(WebDriver chromeDriver) {
        super(chromeDriver);
        PageFactory.initElements(chromeDriver, this);
    }

    public boolean clickOnSideBarByText(String sideBarElementText, SideBarSections section) {
        // Please note I implemented the relevant section only "BRINGG SDK"

        int sectionStartingIndex;
        int sectionEndIndex;

        try {
            switch (section) {
                case GUIDELINES:
                    break;
                case BRINGG_SDK:
                    sectionStartingIndex = SideBarSections.BRINGG_SDK.getSectionStartIndex();
                    sectionEndIndex = SideBarSections.FLEET_SELF_REGISTRATION.getSectionStartIndex();
                    clickElementByText(sideBarElementText, sectionStartingIndex, sectionEndIndex);
                    break;
                case FLEET_SELF_REGISTRATION:
                    break;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void clickElementByText(String sideBarElementText, int startingIndex, int endingIndex) {
        List<WebElement> elements = chromeDriver.findElements(By.cssSelector("#hub-sidebar-content ul li div"));

        for (int i = startingIndex; i < endingIndex; i++) {
            if (elements.get(i).getText().equalsIgnoreCase(sideBarElementText)) {
                elements.get(i).click();
                return;
            }
        }
    }

    @Override
    public boolean isPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hub-sidebar-content"))).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
