package page.object.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.object.BaseFunc;
import page.object.helpers.CommentsHelper;

import java.util.List;

public class HomePage {
    private final By ARTICLE = By.tagName("article");
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By COMMENTS = By.xpath(".//a[@class ='comment-count text-red-ribbon']");

    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc) { // Конструктор создаем
       this.baseFunc = baseFunc; // этот baseFunc на уровне класса
    }

    public String getTitleById(int id) { // int id = функция должна получить ID
        return getArticleById(id).findElement(TITLE).getText().trim();// мы инт превращаем в текст
    }

    // переход на стр самой статьи (с главной на стр.статьи)

    public ArticlePage openArticle(int id) {
        baseFunc.click(getArticleById(id));
        return new ArticlePage(baseFunc);
    }

    // т.к. дублируются 2 статьи выносим в один метод:
    public WebElement getArticleById(int id) {
        List<WebElement> articles = baseFunc.findElements(ARTICLE);
        //делаем проверку, пустой список или нет:
        Assertions.assertFalse(articles.isEmpty(),"There is not articles");
        return articles.get(id);
    }

    //Getting comments on home page
    public int getCommentCount(int id) {
        int commenCount = 0;
        if (getCommentsById(id).isDisplayed()){
            commenCount = CommentsHelper.parseCommentCount(getCommentsById(id).getText());
        }
        return commenCount;
    }

    public WebElement getCommentsById(int id){
        return getArticleById(id).findElement(COMMENTS);
    }
}
