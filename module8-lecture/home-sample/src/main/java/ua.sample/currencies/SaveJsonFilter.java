package ua.sample.currencies;

import org.json.JSONObject;

import javax.servlet.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class SaveJsonFilter implements Filter {

    private void saveOrganizationsToApplicationScope(ServletRequest req) throws IOException {
        if (req.getServletContext().getAttribute("list") == null) {
            List<Organization> organizations = Application.getOrganizations(new JSONObject(Application.getCurrencyJSONPro()));
            req.getServletContext().setAttribute("list", Application.getOrganizations(new JSONObject(Application.getCurrencyJSONPro())));

            req.getServletContext().setAttribute("timeStamp", LocalDateTime.now());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        saveOrganizationsToApplicationScope(request);
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
