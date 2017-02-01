package ua.goit.java.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.Menu;
import ua.goit.java.db.dao.MenuDao;

import java.util.List;

@Service
public class MenuService {

    private MenuDao menuDao;

    @Transactional
    public Menu getMenu() {
       return menuDao.getAll().get(0);
    }

    @Transactional
    public List<Menu> getAllMenus() {
        return menuDao.getAll();
    }

    @Transactional
    public Menu getMenuByID(int id) {
        return menuDao.getById(id);
    }

    @Transactional
    public List<Menu> findMenuByName(String name) {
        return menuDao.findByName(name);
    }

    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }


}
