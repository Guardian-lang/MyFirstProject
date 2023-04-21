package by.Ahmed.servlet;

import by.Ahmed.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Long articleId = Long.valueOf(req.getParameter("id"));

        req.setAttribute("articles", ArticleService.findAll());
        req.getRequestDispatcher("/WEB-INF/JSP/tickets.jsp").forward(req, resp);
    }
}
