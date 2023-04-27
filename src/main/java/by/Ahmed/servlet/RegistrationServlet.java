package by.Ahmed.servlet;

import by.Ahmed.dto.CreateAuthorDto;
import by.Ahmed.entity.CheckStatus;
import by.Ahmed.entity.Gender;
import by.Ahmed.service.AuthorService;
import by.Ahmed.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import by.Ahmed.exceptions.ValidationException;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final AuthorService authorService = AuthorService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genders", Gender.values());
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var authorDto = CreateAuthorDto.builder()
                .firstName(req.getParameter("first_name"))
                .lastName(req.getParameter("last_name"))
                .gender(req.getParameter("gender"))
                .birthDate(req.getParameter("birth_date"))
                .occupation(req.getParameter("occupation"))
                .jobTitle(req.getParameter("job_title"))
                .checkStatus(String.valueOf(CheckStatus.UNCHECKED))
                .about(req.getParameter("about"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
        try {
            authorService.create(authorDto);
            resp.sendRedirect("/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
