package pl.hotowy.housingdev.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hotowy.housingdev.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseOperator {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public void save(Object object) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public HousingCommunity selectHousingCommunity(String id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select hc from HousingCommunity hc WHERE id ="+id);
        HousingCommunity result = (HousingCommunity)query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }
    public House selectHouse(String id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select hc from House hc WHERE id ="+id);
        House result = (House) query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }
    public Habitant selectHabitant(String id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select hc from Habitant hc WHERE id ="+id);
        Habitant result = (Habitant) query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }
    public Flat selectFlat(String id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select hc from Flat hc WHERE id ="+id);
        Flat result = (Flat) query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public void update(Identifiable object){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Class<?> aClass = object.getClass();

        Query query= entityManager.createQuery("select hc from "+aClass.getName()+" hc WHERE id ="+object.getId());
        Identifiable fromDatabase = (Identifiable) query.getSingleResult();
        fromDatabase.merge(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<HousingCommunity> selectAllHousingCommunities(){
        List<HousingCommunity> result = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select e from HousingCommunity e");
        result.addAll(query.getResultList());
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public List<Object> selectAll(Class objectClass){
        List<Object> result = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select e from "+objectClass.getName()+" e");
        result.addAll(query.getResultList());
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }


}
