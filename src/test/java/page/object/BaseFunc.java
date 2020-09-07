package page.object;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Integer.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class BaseFunc {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    public BaseFunc() { // это конструктор, ничего не возвращает = второй части нет "после слова public"
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe"); // т.к. Driver внутри конструктора,
        //то виден он только там
        driver = new ChromeDriver(); // открытие окна браузера
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
    }

    // создаем функцию, которая будет откывать
    public void openPage(String url) {
        LOGGER.info("Try to open page: " + url);

        // делаем проверку,т.к. Selenium не принимает ссылки , к-ые не начинаются с http/https
        if (!url.startsWith("http://") && !url.startsWith("https://")) { //&& = и
            url = "http://" + url; // если ссылка не начинается с http и не начинается с Https, то гда мы добавляем http к сылке
        }
        driver.get(url); // переход по прямой ссылке у driver отвечает get. url = ссылка
    }

    //  Создаем функцию:
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator); // найдем через драйвер все Элементы и вернем назад
    }

    //Функцию click (она дает нам WebElement) выносим в BaseFunc
    // Перед тем, как кликнуть,он подождет что бы элемент стал кликабельным
    public void click(WebElement element) {
        wait.until(elementToBeClickable(element));
        element.click();
    }

    //v odnom klasse 2 odinakovix metoda mozet bitj toljko 2... to estj po kliku u nas Web i lokator
    public void click(By locator) {
        wait.until(elementToBeClickable(locator));
        findElement(locator).click();

      // click(findelemet())
    }

    public String getText(By locator) {
        //1st find element
        //2nd - get text
        LOGGER.info("Looking for text");

        return findElement(locator).getText();
    }

    // funkcija iskatj element:
    public WebElement findElement(By locator) {

        LOGGER.info("Find element");
        wait. until(visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    //
    public boolean isElementPresents(By locator) { // булин отображает:True /false
        try {
            wait.until(presenceOfElementLocated(locator));
            return true;
        } catch (NoSuchElementException e) { //pod e budet xranitsja o6ibka - e=element// catch - pitaemsja otlovitj vot
            //etu o6ibku "No such.."
            // Try catch = perexvativajet o6ibku, esli ona estj, pozvoljajet prisutstvovatj drugim variantam
            return false;
        }

    }

    public void closeWebPage(){
        driver.quit();
    }
}
