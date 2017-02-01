package ua.goit.java.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Dish;
import ua.goit.java.db.dao.DishDao;

import java.util.List;

@Service
public class DishService {

    private DishDao dishDao;

    @Transactional
    public List<Dish> getDishesByName(String dishName) {
        return dishDao.findByName(dishName);
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

}
