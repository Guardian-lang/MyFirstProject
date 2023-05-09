package by.Ahmed.servlet;

import by.Ahmed.dto.ArticleDto;
import by.Ahmed.entity.Gender;
import by.Ahmed.service.ArticleService;
import by.Ahmed.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private static final ArticleDto articleDto = new ArticleDto();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.setAttribute("article", articleDto.getTitle());
        req.getRequestDispatcher(JspHelper.getPath("articles"))
                .forward(req, resp);

        try (Writer writer = resp.getWriter()) {
            writer.write("""
                    <h1>Zalupa-news</h1>""");
        }
    }
}

