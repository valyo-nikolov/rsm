package rsm.second_task;

import org.testng.Assert;
import org.testng.annotations.Test;
import rsm.second_task.pages.*;

import java.util.Arrays;

public class AmazonTests extends BaseTest {

    LandingPage landingPage = new LandingPage(driver);
    SearchResultPage searchResultPage = new SearchResultPage(driver);
    PaperbackPage paperbackPage = new PaperbackPage(driver);
    BasketConfirmPage basketConfirmPage = new BasketConfirmPage(driver);
    BasketPage basketPage = new BasketPage(driver);

    @Test
    public void searchInTheBooksSectionTest() {
        final String BOOK_NAME = "Harry Potter and the Cursed Child - Parts One and Two";

        driver.get("https://www.amazon.co.uk/");

        //NB! This sleep here is used when the amazon captcha is switched on and user has to type manually the quiz -
        //I expect in real test environment the captcha to be switched off and this sleep to be removed
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        landingPage.setAcceptCookiesBtn();
        landingPage.setDismissBtn();
        Assert.assertTrue(landingPage.isLogoDisplayed("Amazon.co.uk"));

        landingPage.setDropdownSearch("Books");
        landingPage.setSearchInput(BOOK_NAME);
        landingPage.setSearchSubmit();
        Assert.assertTrue(searchResultPage.doesTitleContains(BOOK_NAME));
        Assert.assertTrue(searchResultPage.isPaperbackDisplayed());
        Assert.assertTrue(searchResultPage.isPriceDisplayed());
        Assert.assertTrue(searchResultPage.isDeliveryPriceDisplayed());
        Assert.assertTrue(searchResultPage.isDeliveryDateDisplayed());
        String paperbackPrice = searchResultPage.getPaperbackPrice();
        String paperbackDeliveryPrice = searchResultPage.getDeliveryPrice().replace(" delivery", "");
        String paperbackDeliveryDate = searchResultPage.getDeliveryDate();

        searchResultPage.setFirstSearchResultPaperback();
        Assert.assertTrue(paperbackPage.doesTitleContains(BOOK_NAME));
        Assert.assertTrue(paperbackPage.isPaperbackDisplayed());
        Assert.assertTrue(paperbackPage.isPaperbackPriceSame(paperbackPrice));
        String paperbackDeliveryPriceFromDetails = paperbackPage.getPaperbackDeliveryPrice();
        String paperbackDeliveryDateFromDetails = paperbackPage.getPaperbackDeliveryDate();
        Assert.assertEquals(paperbackDeliveryPrice, paperbackDeliveryPriceFromDetails);
        Assert.assertTrue(paperbackDeliveryDateFromDetails.contains(
                Arrays.stream(paperbackDeliveryDate.split(",")).toList().get(0)));
        Assert.assertTrue(paperbackDeliveryDateFromDetails.contains(
                Arrays.stream(paperbackDeliveryDate.split(",")).toList().get(1)));
        Assert.assertTrue(paperbackPage.isInStockDisplayed());
        Assert.assertTrue(paperbackPage.isDefaultQuantitySetToOne());

        paperbackPage.setAddToGiftCheckbox();
        paperbackPage.setAddToBasketBtn();
        Assert.assertTrue(basketConfirmPage.isAddedToBasket());
        basketConfirmPage.setGoToBasketBtn();
        Assert.assertTrue(basketPage.isGiftInBasketCheckboxChecked());
        Assert.assertTrue(basketPage.isGiftInSubtotalCheckboxChecked());
        Assert.assertTrue(basketPage.isPaperbackDisplayed());
        Assert.assertEquals(paperbackPrice, basketPage.getPaperbackBasketPrice());
        Assert.assertEquals(paperbackPrice, basketPage.getPaperbackBasketSubtotalPrice());
        Assert.assertTrue(basketPage.doesTitleContains(BOOK_NAME));
        Assert.assertTrue(basketPage.isOnlyOneItemInTheBasket());
    }
}
