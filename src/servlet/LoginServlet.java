package servlet;

import dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.UserService;
import util.JspHelper;
import mapper.UserLoginDTOMapper;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserLoginDTOMapper userLoginDTOMapper = UserLoginDTOMapper.getInstance();
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = userLoginDTOMapper.map(req);
        userService.login(userDTO).ifPresentOrElse(
                user -> onLoginSuccess(user, req, resp),
                () -> onLoginFail(req, resp)
        );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void onLoginSuccess(UserDTO userDTO, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", userDTO);
        resp.sendRedirect("/home");
    }

}
