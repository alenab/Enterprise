package ua.sample.currencies;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Servlet extends HttpServlet {

    private List<Organization> getOrganizations (HttpServletRequest req) {
        return  (List<Organization>) req.getServletContext().getAttribute("list");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bankId = req.getParameter("bank");
        if (bankId != null) {
            req.setAttribute("organization", getOrganizations(req).get(Integer.parseInt(bankId)));
        }

        req.getRequestDispatcher("/exchangeRates.jsp").forward(req, resp);
        resp.sendRedirect("exchangeRates.jsp");
    }
}
