package page.object.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import page.object.BaseFunc;
import page.object.pages.ArticlePage;
import page.object.pages.CommentsPage;
import page.object.pages.HomePage;

import static org.junit.jupiter.api.Assertions.*;

public class PageObjectTests {
    // private final int ID = 2;
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    @Test
    public void pageObjectTest() {
        LOGGER.info("Text will check article title  on Home,Article and Comments pages");

        baseFunc = new BaseFunc(); // создали копию BaseFunc/конструктора
        baseFunc.openPage("delfi.lv"); // переход на дом.стр

        HomePage homePage = new HomePage(baseFunc);
        String homePageTitle = homePage.getTitleById(2);
        ArticlePage articlePage = homePage.openArticle(2);

 //       ArticlePage articlePage = new ArticlePage(baseFunc);
        // proverku delajem:,nikogda ne sozdajem kopiju asser = t.k. eto statika
        // pervuju 4astj mozno ubratj - "Assertions" = alt+enter i nazimajem enter
        assertEquals(homePageTitle,articlePage.getTitle(),"Wrong title on article page");//ozidajem, 4to bilo na glavnoj str i zagolovok na teku6ej str

        CommentsPage commentsPage = articlePage.openComments();
        assertEquals(homePageTitle,commentsPage.getTitle(), "Wrong title");
    }

    @Test
    public void pageObjectCommentsTest() {
        LOGGER.info("Test will check article comments on Home,Article and Comments pages");

        baseFunc = new BaseFunc(); // создали копию BaseFunc/конструктора
        baseFunc.openPage("delfi.lv");

        HomePage homePage = new HomePage(baseFunc); //создаем копию главной стр
        int homePageComments = homePage.getCommentCount(2);
        ArticlePage articlePage = homePage.openArticle(2);
        assertEquals(homePageComments,articlePage.getComments(),"Wrong comments count on article page");

        CommentsPage commentsPage = articlePage.openComments();
        assertEquals(homePageComments,commentsPage.getComments(2), "Wrong comments count");

    }

    @AfterEach
    public void close(){
        baseFunc.closeWebPage();
    }
}


