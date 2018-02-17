package pl.hotowy.housingdev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hotowy.housingdev.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

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
        return (HousingCommunity)query.getSingleResult();
    }
    public House selectHouse(String id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select hc from House hc WHERE id ="+id);
        return (House)query.getSingleResult();
    }
    public Habitant selectHabitant(String id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select hc from Habitant hc WHERE id ="+id);
        return (Habitant) query.getSingleResult();
    }
    public Flat selectFlat(String id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select hc from Flat hc WHERE id ="+id);
        return (Flat) query.getSingleResult();
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


}
