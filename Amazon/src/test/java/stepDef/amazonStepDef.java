package stepDef;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.TestContextSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class amazonStepDef {
    TestContextSetup tcs;
    WebDriverWait wait;

    public amazonStepDef(TestContextSetup tcs) {
        this.tcs = tcs;
    }

    @Given("I am able to search specified productId")
    public void i_am_able_to_search_specified_product_id() {
        tcs.pom.getAmazon().searchProduct();
    }
    @When("I open the product page for specified productId")
    public void i_open_the_product_page_for_specified_product_id() {
        tcs.pom.getAmazon().clickOnProduct();
    }
    @Then("I should see the Buy Now button")
    public void i_should_see_the_buy_now_button() {
        Assert.assertTrue(tcs.pom.getAmazon().validateBuyNowButton());
    }

    @Then("I should check if customer rating for the product is over four")
    public void iShouldCheckIfCustomerRatingForTheProductIsOverFour() {
        tcs.pom.getAmazon().validateRating();
    }

    @Then("Print all the offers available on the description page for the product")
    public void printAllTheOffersAvailableOnTheDescriptionPageForTheProduct() {
        Assert.assertTrue(tcs.pom.getAmazon().goToOffers());
    }
}
