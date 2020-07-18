import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DelfiTests {

    @Test
    public void firstDelfiTest() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rus.delfi.lv");

        String text = driver.findElement(By.xpath(".//h1[contains(@class, 'headline__title')]")).getText();
        System.out.println(text);

        // Hometask 12.07.2020
        // Find 1st title element
        WebElement firstTitle = driver.findElement(By.xpath(".//h1[contains(@class, 'headline__title')]"));

        // Get & Save (this element text)
        String firstTitleText = firstTitle.getText();
        // firstTitleText = eto peremenaja

        // Click on this element
        firstTitle.click();

        // Find article's title element
        WebElement articleTitle = driver.findElement(By.xpath(".//h1[contains(@class,'d-inline')]"));
        //Kogda napisali driver....(By...); to mi dolzni ego soxranitj v kakuju-nibudj peremenaju (WebElement) i dajem nazvanije


        //Get&Save element's text
        String articleTitleText = articleTitle.getText();

        // Check (testi pi6utsja dlja proverki)
        Assertions.assertEquals(firstTitleText,articleTitleText,"Wrong article title!");


    }
}
