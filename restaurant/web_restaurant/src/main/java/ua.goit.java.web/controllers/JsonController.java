package ua.goit.java.web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.java.db.Employee;
import ua.goit.java.db.Menu;
import ua.goit.java.db.Order;
import ua.goit.java.db.Views;
import ua.goit.java.web.services.EmployeeService;
import ua.goit.java.web.services.MenuService;
import ua.goit.java.web.services.OrderService;

import java.util.List;


@RestController
public class JsonController {

    private MenuService menuService;
    private OrderService orderService;
    private EmployeeService employeeService;

    @JsonView(Views.Menus.class)
    @RequestMapping(value = "/menu", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public List<Menu> menu() {
        return menuService.getAllMenus();
    }

    @JsonView(Views.DishesInMenu.class)
    @RequestMapping(value = "/menu/{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public Menu menuByID(@PathVariable int id) {
        return menuService.getMenuByID(id);
    }

    @JsonView(Views.DishesInMenu.class)
    @RequestMapping(value = "/menu/name={name}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public List<Menu> menuByName(@PathVariable String name) {
        return menuService.findMenuByName(name);
    }

    @JsonView(Views.Orders.class)
    @RequestMapping(value = "/order", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public List<Order> order() {
        return orderService.getOrders();
    }

    @JsonView(Views.Orders.class)
    @RequestMapping(value = "/order/open", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public List<Order> orderOpen() {
        return orderService.getOpen();
    }

    @JsonView(Views.Orders.class)
    @RequestMapping(value = "/order/close", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public List<Order> orderClosed() {
        return orderService.getClosed();
    }

    @JsonView(Views.Orders.class)
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public Order orderByID(@PathVariable int id) {
        return orderService.getOrderById(id);
    }


    @JsonView(Views.Employees.class)
    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public List<Employee> employee() {
        return employeeService.getAll();
    }

    @JsonView(Views.Employees.class)
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public Employee employeeByID(@PathVariable int id) {
        return employeeService.getById(id);
    }

    @JsonView(Views.Employees.class)
    @RequestMapping(value = "/employee/name={name}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public List<Employee> employeeByID(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}

