package pl.hotowy.housingdev.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class House implements Identifiable{

    @Id
    @GeneratedValue
    private long id;

    private String adress;

    @OneToMany(mappedBy = "house")
    private List<Flat> flats;

    @OneToOne
    private HousingCommunity housingCommunity;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void merge(Object object) {
        House newData = (House) object;
        this.setAdress(newData.getAdress());
        this.setFlats(newData.getFlats());
        this.setHousingCommunity(newData.getHousingCommunity());
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    public HousingCommunity getHousingCommunity() {
        return housingCommunity;
    }

    public void setHousingCommunity(HousingCommunity housingCommunity) {
        this.housingCommunity = housingCommunity;
    }
}
