package ua.goit.java.db;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 8236435786982841655L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "measurement")
    private String measurement;

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

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measurement='" + measurement + '\'' +
                '}';
    }
}
