package ua.goit.java.db;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.swing.text.View;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 2906000748955495458L;

    @JsonView(Views.Menus.class)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(Views.Menus.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.DishesInMenu.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "menu_dish",
            joinColumns = @JoinColumn(name="menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Dish> dishes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes.stream().map(Dish::getName).collect(Collectors.toList()) +
                '}';
    }
}
