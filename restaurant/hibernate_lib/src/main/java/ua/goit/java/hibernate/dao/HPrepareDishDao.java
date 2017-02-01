package ua.goit.java.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.db.*;
import ua.goit.java.db.dao.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class HPrepareDishDao implements PrepareDishDao {

    private SessionFactory sessionFactory;

    private DishDao dishDao;
    private EmployeeDao employeeDao;
    private OrderDao orderDao;
    private StoreDao storeDao;

    public HPrepareDishDao(SessionFactory sessionFactory, DishDao dishDao, EmployeeDao employeeDao, OrderDao orderDao, StoreDao storeDao) {
        this.sessionFactory = sessionFactory;
        this.dishDao = dishDao;
        this.employeeDao = employeeDao;
        this.orderDao = orderDao;
        this.storeDao = storeDao;
    }

    public HPrepareDishDao() {
    }

    @Transactional
    @Override
    public int add(int dishId, int employeeId, int orderId, Date prepareDate) {
        if (enoughIngredientToPrepare(dishId)) {
            PrepareDish prepareDish = new PrepareDish();
            prepareDish.setDish(dishDao.getById(dishId));
            prepareDish.setEmployee(employeeDao.getById(employeeId));
            prepareDish.setOrder(orderDao.getById(orderId));
            prepareDish.setPrepareDate(prepareDate.toLocalDate());
            int result = (int) sessionFactory.getCurrentSession().save(prepareDish);
            decreaseIngredientInStoreForPrepareDish(dishId);
            return result;
        } else {
            return 0;
        }
    }

    @Transactional
    private float getQuantityOfIngredient(int dishId, int ingredientId) {
        Query query = sessionFactory.getCurrentSession().createNativeQuery("select ingredient_quantity " +
                "from dish_ingredients where dish_id = :dishId and ingredient_id = :ingredientId");
        query.setParameter("dishId", dishId);
        query.setParameter("ingredientId", ingredientId);
        return (float) query.uniqueResult();
    }

    @Transactional
    private boolean enoughIngredientToPrepare(int dishId) {
        Dish dish = dishDao.getById(dishId);
        List<Ingredient> list = dish.getIngredients();
        for (Ingredient ingredient : list) {
            Float quantityNeedToDish = getQuantityOfIngredient(dishId, ingredient.getId());
            Float quantityInStore = storeDao.findByIngredientId(ingredient.getId()).getQuantity();
            if (quantityInStore < quantityNeedToDish) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    private void decreaseIngredientInStoreForPrepareDish(int dishId) {
        Dish dish = dishDao.getById(dishId);
        List<Ingredient> list = dish.getIngredients();
        for (Ingredient ingredient : list) {
            Float quantatyNeedToDish = getQuantityOfIngredient(dishId, ingredient.getId());
            Float quantityInStore = storeDao.findByIngredientId(ingredient.getId()).getQuantity();
            if (quantityInStore > quantatyNeedToDish) {
                storeDao.findByIngredientId(ingredient.getId()).setQuantity(quantityInStore - quantatyNeedToDish);
            }
        }
    }

    @Transactional
    @Override
    public List<PrepareDish> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from PrepareDish p", PrepareDish.class).list();

    }

    @Transactional(readOnly = true)
    @Override
    public List<PrepareDish> getByOrderId(int orderId) {
        Query<PrepareDish> query = sessionFactory.getCurrentSession().createQuery("select d from PrepareDish d" +
                " where d.order.orderId = :id", PrepareDish.class);
        query.setParameter("id", orderId);
        return query.list();
    }

    @Transactional
    public List<Dish> needToPrepare() {
        List<Order> orders = orderDao.getAll();
        List<OrderedDish> orderedDishes = new ArrayList<>();
        for (Order order : orders) {
            orderedDishes.addAll(order.getOrderedDish());
        }
        List<Dish> dishes = new ArrayList<>();
        for (OrderedDish orderedDish : orderedDishes) {
            for (int i = 1; i <= orderedDish.getQuantity(); i++) {
                dishes.add(orderedDish.getDish());
            }
        }

        List<PrepareDish> prepareDishes = getAll();
        List<Dish> preparedDish = new ArrayList<>();
        for (PrepareDish prepareDish : prepareDishes) {
            preparedDish.add(prepareDish.getDish());
        }

        for (Dish dish : preparedDish) {
            if(dishes.contains(dish)) {
                dishes.remove(dish);
            }
        }
        return dishes;
    }
}
