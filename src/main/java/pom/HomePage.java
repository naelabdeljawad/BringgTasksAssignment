package pom;

import com.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class HomePage extends AbstractPage {

    @FindBy(css = "#menu-item-54852 a")
    @CacheLookup
    private WebElement developersPortalLink;

    @FindBy(className = "page-template")
    @CacheLookup
    private WebElement pageContainer;

    public HomePage(WebDriver chromeDriver) {
        super(chromeDriver);
        PageFactory.initElements(chromeDriver, this);
    }

    public boolean openBringgHomePage() {
        try {
            chromeDriver.manage().window().maximize();
            chromeDriver.get(PropertiesReader.getInstance().getProperty("bringg.url"));
            chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //Init page factory to load elements after accepting cookies to avoid stale elements exceptions
            PageFactory.initElements(chromeDriver, this);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-template")));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean clickDevelopersPortalLink() {
        try {
            // Scroll
            JavascriptExecutor executor = (JavascriptExecutor) chromeDriver;
            executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            developersPortalLink = chromeDriver.findElement(By.cssSelector("#menu-item-54852 a"));
            executor.executeScript("arguments[0].click();", developersPortalLink);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean clickDevelopersPortalLink(String linkText) {
        try {
            chromeDriver.findElement(By.linkText(linkText)).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-template"))).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
