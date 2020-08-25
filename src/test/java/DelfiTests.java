import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class DelfiTests {
    private final By ARTICLE = By.tagName("article"); // final eto constanta, locator - by i nazvanije ARTICLE, tak kak constanta, to s bolj6oj bukvi
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'd-inline')]");

    private final By COMMENTS_COUNT = By.xpath(".//a[contains(@class,'d-print-none')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath("");

    private WebDriver driver; // nam eta peremennnaja nuzna toljko v predelax etogo klassa, pojetomu private
    // driver fioletovij - ozna4aet dostupna na urovne vsego klassa


    @BeforeEach
    public void preconditions () {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rus.delfi.lv");
        //Web driver dostupen toljko v odnom metode, poetomu v ostaljnix slu4ajax driver - krasnij
    }

    @Test
    public void firstDelfiTest() {
        List<WebElement> textes = driver.findElements(By.xpath(".//h1[contains(@class, 'headline__title')]"));

        for (int i = 0; i < textes.size(); i++) {
            System.out.println((i + 1) + ": " + textes.get(i).getText());
        }
    }

    @Test
    public void firstDelfiTestHomeWork() {
        String text = driver.findElement(By.xpath(".//h1[contains(@class, 'headline__title')]")).getText();
        // Tak kak tam to4ka stoit i get text, to eto String

        // Hometask 12.07.2020

        // Find 1st title element
        WebElement firstTitle = driver.findElement(By.xpath(".//h1[contains(@class, 'headline__title')]"));
        // tak kak tut to4ki net, to eto Web Element

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

        // Find first article' comments amount
        WebElement articleCommentsAmount = driver.findElement(By.xpath(".//a[contains(@class, 'comment-count')]"));

        // @Before Each - открывается браузер, preconditions
        // @After Each - закрывается браузер, сам текст

    }


    @Test
    public void workingWithLists() {
        List<String> studentNames = new ArrayList<String>();

        System.out.println(studentNames.isEmpty());

        studentNames.add("Zoja");
        studentNames.add("Vovan");
        studentNames.add("Petja");

        // dal6e mi mozem delatj proverki..
        System.out.println(studentNames.isEmpty());
        System.out.println(studentNames.size());
        System.out.println(studentNames.get(1));


    }

    @Test
    public void titleTest() {
        final String TITLE_TO_FIND = "Северная Корея заявила о возможном первом случае случае заражения Covid-19";  // eto to 4no nam nado naiti

        // get all articles // pered etim sdelatj locator
        List<WebElement> articles = driver.findElements(ARTICLE); // dolzni soxranitj v peremennnuju (list web elementov

        // find given article by text
        boolean isFound = false; // esli ne na6li statju
        for (WebElement article : articles) {
            // teperj nuzno naiti title, dlja etogo nuzen lokator, lokator naverxu napisala
            if (article.findElement(TITLE).getText().equals(TITLE_TO_FIND)) { // iz statji berem zagolovok, to
                article.findElement(TITLE).click();
                isFound = true;
                break; // ozna4aet viiti iz cikla
            }
        }

        Assertions.assertTrue(isFound,"Article is not found "); // posle cikla delajem proerku< 4to naidet tekst

        // click on it -> mi uze kliknuli na verxu, pojetomu ne nado pisath

        // find article's title
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();

        // check (сравнить)
        Assertions.assertEquals(TITLE_TO_FIND, articlePageTitle,"Titles are not equal!");

    }


    @AfterEach
    private void closeBrowser() {

        driver.close();
    }
}
