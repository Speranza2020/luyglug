import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;

public class DelfiTestThirdArticle {
    // Первые 3 локатора относятся к Home Page, осталтные 2 к Article page, последние 2 к Comments page
    private final By ARTICLE = By.tagName("article");
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By COMMENTS = By.xpath(".//a[@class ='comment-count text-red-ribbon']");

    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'd-print-none')]");

    private final By COMMENTS_PAGE_TITLE = By.xpath(".//h1[@class = 'article-title']");
    private final By COMMENTS_PAGE_COMMENTS = By.xpath(".//span[contains(@class, 'type-cnt')]");

    //Sozdajem kopiju loggera:
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    // this- ozna4ajet? 4to mi berem teku6ij klass


    @Test
    public void firdArticleTitleCommentsCheck () {
        LOGGER.info("This test is checking third article title and comments");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");

        LOGGER.info("Browser opening, window maximization");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        LOGGER.info("Home page opening");
        driver.get("https://rus.delfi.lv"); //WebDriver dostupen toljko v odnom metode, poetomu v ostaljnix slu4ajax driver - krasnij


        LOGGER.info("Getting all articles"); // pered etim sdelatj locator na verhu
        List<WebElement> articles = driver.findElements(ARTICLE); // Po4emu mi pi6em List, a ne prosto WebElement?

        LOGGER.info("To find 3rd article by text");
        WebElement article = articles.get(2);  // 2- potomu 4to s4itajetsja ot 0.To estj 0-1,1-2,2-3

        LOGGER.info("To find title on the article");
        // find title (vnutri samoj statji bude iskatj nazvanije). Po4emu vnutri, esli pri napisanii lokatora
        // vidajet vse nazvanija, a ne konkretno na6u.
        String homePageTitle = article.findElement(TITLE).getText().trim();

        LOGGER.info("Getting comments on home page");
        // lokator nado sdelatj. mozem nazatj na ljuboj koment i posmotretj kod.
        // T.k. mi pi6em lokator dlja vsex komentov na glavnoj str, gde vse statji naxodjatsja..
        // izna4aljno proverim, esli estj kom-ti v dannoj statje
        int homePageComments = 0;
        if (!article.findElements(COMMENTS).isEmpty()) {
            // v statje article mi i6em vse elementi s kommentami
            homePageComments = parseComment(article.findElement(COMMENTS).getText());
        }

        LOGGER.info("Clicking on the article's title");
        article.findElement(TITLE).click();

        LOGGER.info("Time to wait for title,10sec");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ARTICLE_PAGE_TITLE));

        LOGGER.info("Getting title on the article page, for compare with title on homepage");
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText().trim();


        LOGGER.info("Getting comments count on article page"); // Objazateljno text perevesti v cifri, toestj otbrositj skobki..
        int articlePageComments = Integer.parseInt(driver.findElement(ARTICLE_PAGE_COMMENTS).getText().substring(1,driver.findElement(ARTICLE_PAGE_COMMENTS).getText().length() -1));


        LOGGER.info("Checking title and comments count");
        Assertions.assertTrue(homePageTitle.startsWith(articlePageTitle),"Wrong Title");
        //proverjajem e6e kommenti
        Assertions.assertEquals(homePageComments,articlePageComments,"Wrong comments count");

        LOGGER.info("To open comments page on the article page by click");
        driver.findElement(ARTICLE_PAGE_COMMENTS).click();

        LOGGER.info("Time to wait for title on the article page");//Po4emu imenno v etix dvux mestax mi zapuskajem wait?
        // i po4emu spustja 3 poziciji mi zdem article page title, a ne srazu?
        wait.until(ExpectedConditions.visibilityOfElementLocated(COMMENTS_PAGE_TITLE));

        LOGGER.info("To get title on the comments page");
        String commentsPageTitle = driver.findElement(COMMENTS_PAGE_TITLE).getText().trim();

        LOGGER.info("Getting comments count on the comments page"); // Objazateljno text perevesti v cifri, toestj otbrositj skobki..
        int commentsPageComments = Integer.parseInt(driver.findElement(COMMENTS_PAGE_COMMENTS).getText().substring(1,driver.findElement(COMMENTS_PAGE_COMMENTS).getText().length() -1));

        LOGGER.info("Checking title and comments count");
        Assertions.assertEquals(homePageTitle,commentsPageTitle,"Wrong Title");
        Assertions.assertEquals(homePageComments,commentsPageComments,"Wrong comments count");

    }

    private int parseComment(String textToParse){
        // int, a ne void potomu, 4to nam vernetsja 4islo kommentov!

        LOGGER.info("Leave the number without parentheses");
        textToParse = textToParse.substring(1,textToParse.length() -1);

        // substring - eto funkcija, kot-aja iz odnoj str viderajet podstroku. To estj u nas (18)
        //kommentov pervaja skobka = 0, 1=1,8=2 i poslednaja skobka =3.
        // 1 mi ostavljajem, t.k. eto pervoje 4islo. V svjazi s tem, 4to 4islo mozet sostojatj iz mnozestva
        // 4isel = mi pi6em -1= mol ubirajem posledniuju skobku.
        // otbrosili skobki, teperj peredelivajen v integer

        return Integer.parseInt(textToParse); // parseInt = metod (za4em mi vozvra6ajem rezuljtat - ne o4enj ponjala(
        //Nuzno li eto vse delatj, esli net kom-ov?

    }
}
