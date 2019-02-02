package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/all")
public class CatAllServlet extends HttpServlet {

    private final static String CAT_All_HTML_FILE_PATH = "/Users/milko/Documents/GitHub/JavaWeb/04_JavaEE/src/main/resources/views/all-cats.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatAllServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }


    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Cat> cats = null;

        cats = ((Map<String, Cat>) req.getSession().getAttribute("cats"));

        String htmlFileContent = "";

        if (cats != null ) {
            StringBuilder sb = new StringBuilder();

            for (String catName : cats.keySet()) {

                String catLink = String.format("/cats/profile?catName=%s", catName);

                sb.append(String.format("<h4><a href=%s>%s<a/></h4>", catLink, catName));

                htmlFileContent = this.htmlReader
                        .readHtmlFile(CAT_All_HTML_FILE_PATH)
                        .replace("{{Content}}", sb.toString());
            }

        } else {

            htmlFileContent = this.htmlReader
                    .readHtmlFile(CAT_All_HTML_FILE_PATH)
                    .replace("{{Content}}", "<h3>There are no cats. <a href=\"/cats/create\"> Create some!<a/></h3>");
        }

        resp.getWriter().println(htmlFileContent);
    }
}
