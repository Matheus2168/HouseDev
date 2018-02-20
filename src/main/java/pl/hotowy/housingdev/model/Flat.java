package pl.hotowy.housingdev.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Entity
@Transactional
public class Flat implements Identifiable, Comparable<Flat>{

    @Id
    @GeneratedValue
    private long id;

    private int number;

    private double area;

    @ManyToOne(fetch = FetchType.EAGER)
    private House house;

    @OneToMany(mappedBy = "flat",fetch = FetchType.EAGER)
    private List<Habitant> habitants;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void merge(Object object) {
        Flat newData = (Flat) object;
        this.setArea(newData.getArea());
        this.setHabitants(newData.getHabitants());
        this.setHouse(newData.getHouse());
        this.setNumber(newData.getNumber());
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public List<Habitant> getHabitants() {
        return habitants;
    }

    public void setHabitants(List<Habitant> habitants) {
        this.habitants = habitants;
    }

    @Override
    public int compareTo(Flat o) {
        if (this == o){
            return 0;
        }
        else return 1;
    }
}
