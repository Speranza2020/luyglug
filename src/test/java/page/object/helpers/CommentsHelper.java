package page.object.helpers;

import static java.lang.Integer.parseInt;

public class CommentsHelper {
    public static int parseCommentCount(String text) {
        return parseInt(text.substring(1,text.length()-1));
    }
}
