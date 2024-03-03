package rsm.second_task.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    public Actions actions;
    public JavascriptExecutor js;
    public WebDriverWait wait;
    public FluentWait fluentWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(this.driver);
        js = (JavascriptExecutor) this.driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
        fluentWait = new FluentWait<>(driver)
                .ignoring(StaleElementReferenceException.class)
                .withTimeout(Duration.ofSeconds(15));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        PageFactory.initElements(this.driver, this);
    }

    public void setFluentWait(WebElement element) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickBtn(WebElement element) {
        setFluentWait(element);
        element.click();
    }

    public void clickLink(WebElement element) {
        setFluentWait(element);
        element.click();
    }

    public void selectCheckbox(WebElement element) {
        setFluentWait(element);
        element.click();
    }

    public void selectDropdownOption(WebElement element, String option) {
        Select select = new Select(element);
        select.selectByVisibleText(option);
    }

    public void typeText(WebElement element, String text) {
        setFluentWait(element);
        element.sendKeys(text);
    }

    public boolean doesAttributeExists(WebElement element, String attribute) {
        boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null){
                result = true;
            }
        } catch (Exception ignored) {}

        return result;
    }
}
