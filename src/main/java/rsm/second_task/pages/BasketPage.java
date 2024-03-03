package rsm.second_task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasketPage extends BasePage {

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//input[@type='checkbox'])[2]")
    WebElement giftInBasketCheckbox;

    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    WebElement giftInSubtotalCheckbox;

    @FindBy(css = "span.a-truncate-full")
    WebElement bookTitle;

    @FindBy(css = "span.sc-product-binding")
    WebElement productBiding;

    @FindBy(css = ".sc-product-price")
    WebElement paperbackPrice;

    @FindBy(css = "#sc-subtotal-label-activecart")
    WebElement subtotalItems;

    @FindBy(css = "#sc-subtotal-amount-buybox .sc-price")
    WebElement subtotalPrice;

    //Action methods
    public String getPaperbackBasketPrice() {
        return paperbackPrice.getText();
    }

    public String getPaperbackBasketSubtotalPrice() {
        return subtotalPrice.getText();
    }

    //Verification methods
    public boolean isGiftInBasketCheckboxChecked() {
        return doesAttributeExists(giftInBasketCheckbox, "checked");
    }

    public boolean isGiftInSubtotalCheckboxChecked() {
        return doesAttributeExists(giftInSubtotalCheckbox, "checked");
    }

    public boolean doesTitleContains(String text) {
        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.a-truncate-full")));
        return getTextJs(bookTitle).contains(text);
    }

    public boolean isPaperbackDisplayed() {
        return productBiding.getText().contains("Paperback");
    }

    public boolean isOnlyOneItemInTheBasket() {
        return subtotalItems.getText().contains("Subtotal (1 item)");
    }

    //Supportive methods
    public String getTextJs(WebElement element){
        return (String) (js.executeScript(
                "return arguments[0].innerText;", element));
    }
}
