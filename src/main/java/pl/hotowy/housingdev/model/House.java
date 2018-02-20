package pl.hotowy.housingdev.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Transactional
public class House implements Identifiable{

    @Id
    @GeneratedValue
    private long id;

    private String adress;

    @OneToMany(mappedBy = "house",fetch = FetchType.EAGER)
    private List<Flat> flats;

    @OneToOne(mappedBy = "house",fetch = FetchType.EAGER)
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

    @Override
    public boolean equals(Object o) {
        House other = (House) o;
        if (this.id == other.getId() && this.adress.equals(other.getAdress())){
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, adress, flats, housingCommunity);
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
