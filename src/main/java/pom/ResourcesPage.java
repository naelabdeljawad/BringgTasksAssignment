package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ResourcesPage extends AbstractPage {

    @FindBy(linkText = "Task")
    @CacheLookup
    private WebElement taskLink;

    @FindBy(css = "#content-head h1")
    @CacheLookup
    private WebElement pageContainer;

    @FindBy(css = ".param_table")
    @CacheLookup
    private List<WebElement> tables;

    public ResourcesPage(WebDriver chromeDriver) {
        super(chromeDriver);
        PageFactory.initElements(chromeDriver, this);
    }

    public List<String> getTaskTableContent() {
        List<WebElement> tableCells = tables.get(4).findElements(By.tagName("td"));
        List<String> list = new ArrayList<>();

        for (int i = 0; i < tableCells.size(); i = i + 2) {
            list.add(tableCells.get(i).getText().replaceAll("[\\n]", " "));
        }
        return list;
    }

    public String getPageName() {
        try {
            return pageContainer.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isPageDisplayed() {
        try {
            wait.until(ExpectedConditions.textToBe(By.cssSelector("#content-head h1"), "Resources"));
            return pageContainer.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
