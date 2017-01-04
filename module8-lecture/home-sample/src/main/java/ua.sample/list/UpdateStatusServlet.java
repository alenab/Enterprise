package ua.sample.list;

import ua.sample.list.entity.TextItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<TextItem> items = (List<TextItem>) req.getSession().getAttribute("itemList");
        for (TextItem item : items) {
            item.setDone(req.getParameter(item.getId().toString()) != null);
            String param = req.getParameter("txt_" + item.getId().toString());
            if (param != null) {
               item.setText(param);
            }
        }


        req.getSession().setAttribute("itemList", items);
        resp.sendRedirect("list");
    }
}