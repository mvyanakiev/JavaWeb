package metube.web.filters;

import metube.domain.model.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class UserRegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getMethod().toLowerCase().equals("post")) {
            UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
            userRegisterBindingModel.setUsername(req.getParameter("username"));
            userRegisterBindingModel.setPassword(req.getParameter("password"));
            userRegisterBindingModel.setConfirmPassword(req.getParameter("confirmPassword"));
            userRegisterBindingModel.setEmail(req.getParameter("email"));

            req.setAttribute("model", userRegisterBindingModel);
        }

        chain.doFilter(req, res);
        return;
    }
}
