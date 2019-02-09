package metube.web.filters;

import metube.domain.model.binding.TubeUploadBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/upload")
public class TubeUploadFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getMethod().toLowerCase().equals("post")) {
            TubeUploadBindingModel tubeUploadBindingModel = new TubeUploadBindingModel();


            tubeUploadBindingModel.setAuthor(req.getParameter("author"));
            tubeUploadBindingModel.setTitle(req.getParameter("title"));
            tubeUploadBindingModel.setYoutubeLink(req.getParameter("youtube-link"));
            tubeUploadBindingModel.setDescription(req.getParameter("description"));
            tubeUploadBindingModel.setUploader((String) req.getSession().getAttribute("username"));

            req.setAttribute("model", tubeUploadBindingModel);
        }

        chain.doFilter(req, res);
        return;
    }
}