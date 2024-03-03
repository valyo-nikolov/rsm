package rsm.second_task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//div[@data-component-type='s-search-result'])[1]//h2/a/span")
    WebElement firstSearchResultTitle;

    @FindBy(xpath = "((//div[@data-component-type='s-search-result'])[1]//div[@data-cy='price-recipe']//a)[1]")
    WebElement firstSearchResultPaperback;

    @FindBy(xpath = "((//div[@data-component-type='s-search-result'])[1]//div[@data-cy='price-recipe']//a)[2]" +
            "/span[@class='a-price']//span[@class='a-price-symbol']")
    WebElement firstSearchResultPriceSymbol
            ;
    @FindBy(xpath = "((//div[@data-component-type='s-search-result'])[1]//div[@data-cy='price-recipe']//a)[2]" +
            "/span[@class='a-price']//span[@class='a-price-whole']")
    WebElement firstSearchResultPriceWhole;

    @FindBy(xpath = "((//div[@data-component-type='s-search-result'])[1]//div[@data-cy='price-recipe']//a)[2]" +
            "/span[@class='a-price']//span[@class='a-price-fraction']")
    WebElement firstSearchResultPriceFraction;

    @FindBy(xpath = "((//div[@data-component-type='s-search-result'])[1]//div[@data-cy='delivery-recipe']//span/span)[1]")
    WebElement firstSearchResultDeliveryPrice;

    @FindBy(xpath = "((//div[@data-component-type='s-search-result'])[1]//div[@data-cy='delivery-recipe']//span/span)[2]")
    WebElement firstSearchResultDeliveryDate;

    //Action methods
    public void setFirstSearchResultPaperback() {
        clickLink(firstSearchResultPaperback);
    }

    public String getPaperbackPrice() {
        return firstSearchResultPriceSymbol.getText() +
                firstSearchResultPriceWhole.getText() + "." +
                firstSearchResultPriceFraction.getText();
    }

    public String getDeliveryPrice() {
        return firstSearchResultDeliveryPrice.getText();
    }

    public String getDeliveryDate() {
        return firstSearchResultDeliveryDate.getText();
    }


    //Verification methods
    public boolean doesTitleContains(String text) {
        return firstSearchResultTitle.getText().contains(text);
    }

    public boolean isPaperbackDisplayed() {
        return "Paperback".contentEquals(firstSearchResultPaperback.getText());
    }

    public boolean isPriceDisplayed() {
        return firstSearchResultPriceSymbol.isDisplayed()
                && firstSearchResultPriceWhole.isDisplayed()
                && firstSearchResultPriceFraction.isDisplayed();
    }

    public boolean isDeliveryPriceDisplayed() {
        return firstSearchResultDeliveryPrice.isDisplayed()
                && !firstSearchResultDeliveryPrice.getText().isEmpty();
    }

    public boolean isDeliveryDateDisplayed() {
        return firstSearchResultDeliveryDate.isDisplayed()
                && !firstSearchResultDeliveryDate.getText().isEmpty();
    }


}
