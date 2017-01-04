package ua.sample.list;

import ua.sample.list.entity.TextItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TextItem> items = (List<TextItem>) req.getSession().getAttribute("itemList");
        String param = req.getParameter("id");
        for(TextItem item : items) {
            if (item.getId().toString().equals(param) ) {
                items.remove(item);
                req.getSession().setAttribute("itemList", items);
                resp.sendRedirect("/list");
                return;
            }
        }
    }
}
