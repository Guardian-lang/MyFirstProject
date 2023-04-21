package by.Ahmed.servlet;

import by.Ahmed.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private static final ArticleService articleService = ArticleService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (Writer writer = resp.getWriter()) {
            writer.write("""
                    <h1>Zalupa-news</h1>""");

            articleService.findAll().stream().forEach(articleDto -> {
                try {
                    writer.write("""
                            <h2>%s</h2>
                            <a href='src/main/resources/%s'>Читать далее</a>
                            """.formatted(articleDto.getTitle(), articleDto.getText())
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            writer.write("""
                    """);
        }
    }
}
