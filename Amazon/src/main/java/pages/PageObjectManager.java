package pages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    WebDriver driver;
    Amazon amazon;


    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public Amazon getAmazon() {
        amazon = new Amazon(driver);
        return amazon;
    }




}
