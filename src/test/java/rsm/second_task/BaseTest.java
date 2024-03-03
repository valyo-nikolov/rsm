package rsm.second_task;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    Actions actions;
    JavascriptExecutor js;
    WebDriverWait wait;
    FluentWait fluentWait;

    public BaseTest() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920x1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");

        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().clearResolutionCache().setup();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        // explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // fluent wait
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15));
        fluentWait.pollingEvery(Duration.ofMillis(250));
        fluentWait.ignoring(StaleElementReferenceException.class);
        fluentWait.ignoring(NoSuchElementException.class);
        fluentWait.ignoring(ElementClickInterceptedException.class);

        driver.manage().window().maximize();

        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }

}
