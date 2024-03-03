package rsm.second_task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaperbackPage extends BasePage {

    public PaperbackPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#productTitle")
    WebElement bookTitle;

    @FindBy(css = "#productSubtitle")
    WebElement bookPaperback;

    @FindBy(css = "#tmm-grid-swatch-PAPERBACK span.slot-price span")
    WebElement paperbackPrice;

    @FindBy(xpath = "(//span[@data-csa-c-delivery-price])[1]")
    WebElement paperbackDeliveryDetails;

    @FindBy(css = "#availability span")
    WebElement inStock;

    @FindBy(xpath = "//span[text()='Quantity:']/following-sibling::span")
    WebElement defaultQuantity;

    @FindBy(css = "#gift-wrap")
    WebElement addToGiftCheckbox;

    @FindBy(css = "#add-to-cart-button")
    WebElement addToBasketBtn;


    //Action methods
    public String getPaperbackDeliveryPrice() {
        return paperbackDeliveryDetails.getAttribute("data-csa-c-delivery-price");
    }

    public String getPaperbackDeliveryDate() {
        return paperbackDeliveryDetails.getAttribute("data-csa-c-delivery-time");
    }

    public void setAddToGiftCheckbox() {
        selectCheckbox(addToGiftCheckbox);
    }

    public void setAddToBasketBtn() {
        clickBtn(addToBasketBtn);
    }


    //Verification methods
    public boolean doesTitleContains(String text) {
        return bookTitle.getText().contains(text);
    }

    public boolean isPaperbackDisplayed() {
        return bookPaperback.getText().contains("Paperback");
    }

    public boolean isPaperbackPriceSame(String text) {
        return text.contentEquals(paperbackPrice.getText());
    }

    public boolean isInStockDisplayed() {
        return "In stock".contentEquals(inStock.getText());
    }

    public boolean isDefaultQuantitySetToOne() {
        return defaultQuantity.getText().contentEquals("1");
    }
}
