package rsm.second_task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketConfirmPage extends BasketPage {

    public BasketConfirmPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(),'Added to Basket')]")
    WebElement confirmBasketAddition;

    @FindBy(css = "#sw-gtc a")
    WebElement goToBasketBtn;

    //Action methods
    public void setGoToBasketBtn() {
        clickBtn(goToBasketBtn);
    }

    //Verification methods
    public boolean isAddedToBasket() {
        return confirmBasketAddition.isDisplayed();
    }
}
