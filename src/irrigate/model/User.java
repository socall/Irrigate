/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;

//import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author ofer
 */
@Entity
public class User extends saveable{
    @Id @GeneratedValue
    int id;
    
    String firstname;
    String lastname;
    UserType type;
    
    public User(){}
    
    User(String _firstname, String _lastname, int _id, UserType _type){
        id = _id;
        firstname = _firstname;
        lastname = _lastname;
        type = _type;
    }
    
//    public void save(){ 
//        //Technical mamabo-jambo:
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/irrig.odb");
//        EntityManager em = emf.createEntityManager();
//        
//        em.getTransaction().begin();
//
//        //Save user to database:
//        em.persist(this);
//        em.getTransaction().commit();
//        
//        // Close the database connection:
//        em.close();
//        emf.close();
//    }
    
   @Override
   public String toString() {
       return String.format("User[%s %s, id: %d, type: %s]", firstname, lastname, id, type.toString());
   }

}
