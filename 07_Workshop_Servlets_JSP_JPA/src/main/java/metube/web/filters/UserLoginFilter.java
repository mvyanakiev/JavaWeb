package metube.web.filters;

import metube.domain.model.binding.UserLoginBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class UserLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getMethod().toLowerCase().equals("post")) {
            UserLoginBindingModel userRegisterBindingModel = new UserLoginBindingModel();
            userRegisterBindingModel.setUsername(req.getParameter("username"));
            userRegisterBindingModel.setPassword(req.getParameter("password"));


            req.setAttribute("model", userRegisterBindingModel);
        }

        chain.doFilter(req, res);
        return;
    }
}
