package ua.goit.java.db;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cook extends Employee {

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<PrepareDish> prepareDishes;

    public List<PrepareDish> getPrepareDishes() {
        return prepareDishes;
    }

    public void setPrepareDishes(List<PrepareDish> prepareDishes) {
        this.prepareDishes = prepareDishes;
    }

    @Override
    public String toString() {
        return "Cook{" +
                "prepareDishes=" + prepareDishes +
                '}';
    }
}

