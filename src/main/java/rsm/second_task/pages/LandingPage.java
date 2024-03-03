package rsm.second_task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input#sp-cc-accept")
    WebElement acceptCookiesBtn;

    @FindBy(css = ".glow-toaster-button-dismiss")
    WebElement dismissBtn;

    @FindBy(css = "#nav-logo-sprites")
    WebElement amazonLogo;

    @FindBy(css = "select#searchDropdownBox")
    WebElement dropdownSearch;

    @FindBy(css = "#twotabsearchtextbox")
    WebElement searchInp;

    @FindBy(css = "#nav-search-submit-button")
    WebElement searchSubmit;


    //Action methods
    public void setDismissBtn() {
        clickBtn(dismissBtn);
    }

    public void setAcceptCookiesBtn() {
        clickBtn(acceptCookiesBtn);
    }

    public void setDropdownSearch(String option) {
        selectDropdownOption(dropdownSearch, option);
    }

    public void setSearchInput(String text) {
        typeText(searchInp, text);
    }

    public void setSearchSubmit() {
        clickBtn(searchSubmit);
    }


    //Verification methods
    public boolean isLogoDisplayed(String text) {
        return text.contentEquals(amazonLogo.getAttribute("aria-label"));
    }

}
