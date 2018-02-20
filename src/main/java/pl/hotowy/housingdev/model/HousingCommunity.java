package pl.hotowy.housingdev.model;

import javax.persistence.*;

@Entity
public class HousingCommunity implements Identifiable, Mergeable{

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private House house;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "HousingCommunity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", house=" + house +
                '}';
    }


    @Override
    public void merge(Object object) {
        HousingCommunity newData = (HousingCommunity) object;
        this.setName(newData.getName());
        this.setHouse(newData.getHouse());
    }
}
