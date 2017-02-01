package ua.goit.java.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.web.services.DishService;
import ua.goit.java.web.services.EmployeeService;
import ua.goit.java.web.services.MenuService;

import java.util.Map;

@Controller
public class MainController {

    private MenuService menuService;
    private DishService dishService;
    private EmployeeService employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView menu() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("menuName", menuService.getMenu().getName());
        modelAndView.addObject("dishes", menuService.getMenu().getDishes());
        return modelAndView;
    }


    @RequestMapping(value = "/research", method = RequestMethod.POST)
    public ModelAndView research(@RequestParam String dishName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("menuName", menuService.getMenu().getName());
        modelAndView.addObject("dishes", dishService.getDishesByName(dishName));
        return modelAndView;
    }

    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public ModelAndView dish(@RequestParam("dishName") String dishName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dish", dishService.getDishesByName(dishName));
        modelAndView.setViewName("dish");
        return modelAndView;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employee(Map<String, Object> model) {
        model.put("waiters", employeeService.getWaiters());
        return "employees";
    }

    @RequestMapping(value = "/scheme", method = RequestMethod.GET)
    public String scheme() {
        return "scheme";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contact() {
        return "contacts";
    }


    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}

