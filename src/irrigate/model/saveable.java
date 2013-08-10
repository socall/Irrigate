/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ofer
 */
public class saveable {
     public void save(){ 
        //Technical mamabo-jambo:
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/irrig.odb");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();

        //Save user to database:
        em.persist(this);
        em.getTransaction().commit();
        
        // Close the database connection:
        em.close();
        emf.close();
    }
}
