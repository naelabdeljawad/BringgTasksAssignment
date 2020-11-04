package pom;

import com.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskManagerPage extends AbstractPage {

    @FindBy(linkText = "Task")
    @CacheLookup
    private WebElement taskLink;

    @FindBy(css = "#content-head h1")
    @CacheLookup
    private WebElement pageContainer;

    @FindBy(css = ".param_table_col_1_wider")
    @CacheLookup
    private WebElement tables;

    public TaskManagerPage(WebDriver chromeDriver) {
        super(chromeDriver);
        PageFactory.initElements(chromeDriver, this);
    }

    public List<String> getTaskManagerTableContent() {
        List<WebElement> tableCells = chromeDriver.findElements(By.cssSelector(".param_table_col_1_wider tr td"));
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            list.add(tableCells.get(i).getText().replaceAll("[\\n\\t]", ""));
        }
        return list;
    }

    public boolean clickTaskLink() {
        try {
            taskLink.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean clickTaskLink(String link) {
        try {
            chromeDriver.findElement(By.linkText(link)).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getPageName() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageContainer)).getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isPageDisplayed() {
        try {
            wait.until(ExpectedConditions.textToBe(By.cssSelector("#content-head h1"), "tasksManager"));
            return pageContainer.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
