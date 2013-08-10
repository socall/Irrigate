/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;


import java.util.ArrayList;


import javax.persistence.*;
import java.util.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    
    private void loadUsersFromDB(){
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/irrig.odb");
        EntityManager em = emf.createEntityManager();
 
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        users = new ArrayList(query.getResultList());
        
        em.close();
        emf.close();

    }
    
    private void clearUsersFromDB(){
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/irrig.odb");
        EntityManager em = emf.createEntityManager();
 
        // Code for clearing the User table:
        em.getTransaction().begin();        
        em.createQuery("DELETE FROM User u").executeUpdate();
        em.getTransaction().commit();    

       
        em.close();
        emf.close();        
    }
    
    private void initUsers(){
        
        this.loadUsersFromDB();
        
        // If DB is empty init from file:
        if(users.isEmpty()){
            initUsersFromFile();
            System.out.println("sadasd");
        }
        
        usersTable = new UserTableModel(users);
        
 

    }
    
    
        //Need some rework.
        private void initUsersFromFile(){
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/staff2013.txt";
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner in = new Scanner(reader);
        StringTokenizer st;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            st = new StringTokenizer(line);
            ArrayList<String> data = new ArrayList<String>();
            while (st.hasMoreTokens()) {
                data.add(st.nextToken());
            }
            if(!data.isEmpty()){
                User user = new User(data.get(0), data.get(1), Integer.parseInt(data.get(2)), UserType.values()[Integer.parseInt(data.get(3)) - 1]);
                user.save();
                users.add(user);
            }
        }

        in.close();
    }
    
    public static Server getInstance() {
        return ServerHolder.INSTANCE;
    }
    
    private static class ServerHolder {

        private static final Server INSTANCE = new Server();
    }
}
