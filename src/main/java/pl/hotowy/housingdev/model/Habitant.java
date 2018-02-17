package pl.hotowy.housingdev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Habitant implements Identifiable{

    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;
    private Gender gender;

    @ManyToOne
    private Flat flat;


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void merge(Object object) {
        Habitant newData = (Habitant) object;
        this.setFirstName(newData.getFirstName());
        this.setLastName(newData.getLastName());
        this.setFlat(newData.getFlat());
        this.setGender(newData.getGender());
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }
}
