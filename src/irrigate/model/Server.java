/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;


import java.util.ArrayList;


import javax.persistence.*;
import java.util.*;

/**
 *
 * @author ofer
 */
public class Server {
    
    private ArrayList<Component> components;
    private ArrayList<User> users;
    private UserTableModel usersTable;
    private User active_user = null;
    private ArrayList<CropType> crop_types;
    
    
    private Server() {
        initUsers();
    }
    
    private void initUsers(){
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/irrig.odb");
        EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
//        for (int i = 0; i < 1000; i++) {
//            Point p = new Point(i, i);
//            em.persist(p);
//        }
//        System.out.println("sadsas");
//        
//        User nu;
//        nu = new User("Olivia", "Dunham", 11342, UserType.WORKER);
//        em.persist(nu);
//        nu = new User("John", "Nash", 13416, UserType.MANAGER);
//        em.persist(nu);
//        nu = new User("Alen", "Dilon", 54421, UserType.ACCOUNTENT);
//        em.persist(nu);
//        nu = new User("Nina", "Sharp", 62654, UserType.WORKER);
//        em.persist(nu);
//        nu = new User("Walter", "Bishop", 14711, UserType.WORKER);
//        em.persist(nu);
//
//        em.getTransaction().commit();
 
//        // Find the number of Point objects in the database:
//        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
//        System.out.println("Total Points: " + q1.getSingleResult());
// 
//        // Find the average X value:
//        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
//        System.out.println("Average X: " + q2.getSingleResult());
 
        // Retrieve all the Point objects from the database:
        users = new ArrayList<User>();
        
        TypedQuery<User> query =
            em.createQuery("SELECT u FROM User u", User.class);
        List<User> results = query.getResultList();
        for (User u : results) {
            users.add(u);
        }
        
        usersTable = new UserTableModel(users);
        
 
        // Close the database connection:
        em.close();
        emf.close();
    }
    
    public static Server getInstance() {
        return ServerHolder.INSTANCE;
    }
    
    private static class ServerHolder {

        private static final Server INSTANCE = new Server();
    }
}
