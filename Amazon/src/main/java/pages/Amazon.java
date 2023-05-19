package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;


public class Amazon {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css="#twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id="nav-search-submit-button")
    WebElement search;

    @FindBy(css=".a-section.aok-relative.s-image-square-aspect")
    WebElement product;

    @FindBy(css="#buy-now-button")
    WebElement buyButton;

    @FindBy(css="div[id='averageCustomerReviews_feature_div'] span[class='a-size-base a-color-base']")
    WebElement rating;

    @FindBy(css="div[name='a-cardui-deck-autoname-0']")
    WebElement offers;

    @FindBy(css="#itembox-NoCostEmi")
    WebElement noCostEmiOffer;

    @FindBy(css="#itembox-Partner")
    WebElement partnerOffer;


    public Amazon(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchProduct()
    {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
//        B08BF4CZSV
//        B0B973VCZX
//        B01EZV35QU
//        B0BY8JZ22K
//        B0B4DWPM27
//        B08317Y4VP
//        GX40X54262
//        B08ZDSX9WN
//        B0BY8JZ22K
        String productId = System.getenv("PRODUCT_ID");
        searchBox.sendKeys(productId);
        //searchBox.sendKeys("B08317Y4VP");
        wait.until(ExpectedConditions.visibilityOf(search));
        search.click();
    }

    public void clickOnProduct()
    {
        wait.until(ExpectedConditions.visibilityOf(product));
        product.click();
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it=tabs.iterator();
        it.next();
        String id=it.next();
        driver.switchTo().window(id);
    }

    public boolean validateBuyNowButton()
    {
        try{
            wait.until(ExpectedConditions.visibilityOf(buyButton));
            System.out.println("BuyNow Button is Present");
        }
        catch (NoSuchElementException e){
            System.out.println(e);
        }
        return buyButton.isDisplayed();
    }

    public void validateRating()
    {
        try {
            wait.until(ExpectedConditions.visibilityOf(rating));
            System.out.println("Rating for this product is: " + rating.getText());
            double displayedRating = Double.parseDouble(rating.getText().replaceAll("\\(|\\)", ""));
            System.out.println(displayedRating);
            if (displayedRating >= 4.0) {
                System.out.println("Customer Rating for this product is over Four i.e " + displayedRating);
            } else {
                System.out.println("Customer rating for this product is under Four i.e " + displayedRating);
            }
        }
        catch (NoSuchElementException e)
        {
            System.out.println(e);

        }
    }

    public boolean goToOffers()
    {
        try{
            wait.until(ExpectedConditions.visibilityOf(offers));
            System.out.println(offers.getText());
        }
        catch (NoSuchElementException e)
        {
            System.out.println(e);
        }

        return offers.isDisplayed();
    }

}