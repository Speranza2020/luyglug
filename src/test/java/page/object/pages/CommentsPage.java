package page.object.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import page.object.BaseFunc;

public class CommentsPage {
    private final By TITLE = By.xpath(".//h1[@class = 'article-title']");
    private final By COMMENTS = By.xpath(".//span[contains(@class, 'type-cnt')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public CommentsPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        LOGGER.info("Getting this title");
        return baseFunc.getText(TITLE);
    }

    public int getComments(int id) {
        LOGGER.info("To get comments page comments");
        return Integer.parseInt(baseFunc.findElement(COMMENTS).getText().trim());
    }
}
