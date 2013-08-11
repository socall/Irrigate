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
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ofer
 */
public class Server implements Model{
    
    private ArrayList<Component> components;
    private ArrayList<User> users;
    private UserListModel usersTable;
    private compListModel compList;
    private User active_user = null;
    private ArrayList<CropType> crop_types;
    private DefaultTableModel cropTypeTable;
    
    
    private Server() {
        initUsers();
        initComp();
    }
    
    
    //--------------------------------------------------//
    //                  MODEL methods implementation    //
    //--------------------------------------------------//
    @Override
    public AbstractListModel getCompModel(){
        return compList;
    }
    
    @Override
    public ComboBoxModel getUserModel() {
        return usersTable;
    }
    //--------------------------------------------------//
    
    
    
    
    //---------------Load from DB (to be refactored)---------------------//
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

    
    private void loadCompFromDB(){
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/irrig.odb");
        EntityManager em = emf.createEntityManager();
 
        TypedQuery<Component> query = em.createQuery("SELECT u FROM Component u", Component.class);
        components = new ArrayList(query.getResultList());
        
        em.close();
        emf.close();

    }
    
    private void loadCropTypeFromDB(){
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/irrig.odb");
        EntityManager em = emf.createEntityManager();
 
        TypedQuery<CropType> query = em.createQuery("SELECT u FROM CropType u", CropType.class);
        crop_types = new ArrayList(query.getResultList());
        
        em.close();
        emf.close();

    }
    //---------------------------------------------------//

    
    private void clearFromDB(String class_name){
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/irrig.odb");
        EntityManager em = emf.createEntityManager();
 
        // Code for clearing the User table:
        em.getTransaction().begin();        
        em.createQuery("DELETE FROM " + class_name + " u").executeUpdate();
        em.getTransaction().commit();    

       
        em.close();
        emf.close();        
    }
    
    
    //------------------Init Stuff (to be refactored)-----------------------//
    private void initUsers(){ 
        //this.clearUsersFromDB("User");
        this.loadUsersFromDB();
        
        
        // If DB is empty init from file:
        if(users.isEmpty()){
            initUsersFromFile();
            System.out.println("sadasd");
        }
        
        usersTable = new UserListModel(users);
    }

    private void initComp(){
 
        this.clearFromDB("Component");
        this.loadCompFromDB();
        
        
        // If DB is empty init from file:
        if(components.isEmpty()){
            initCompFromFile();
            System.out.println("sadasd");
        }
        

        this.compList = new compListModel(this.components);
    }
    
    private void initCropType(){
 
        //this.clearFromDB("CropType");
        this.loadCompFromDB();
        
        
        // If DB is empty init from file:
        if(crop_types.isEmpty()){
            initCompFromFile();
            //System.out.println("sadasd");
        }
        

        //this.compList = new compListModel(this.components);
    }
    
    //----------------------------------------------------------------//
    
    
        //Need some rework.
    private void initUsersFromFile(){
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/staff2013.txt";
        //String fileName = "/Users/ofer/NetBeansProjects/Irrigate/staff2013.txt";
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
            if(data.size() >= 4){
                User user = new User(data.get(0), data.get(1), Integer.parseInt(data.get(2)), UserType.values()[Integer.parseInt(data.get(3)) - 1]);
                user.save();
                users.add(user);
            }
        }

        in.close();
    }
    
            //Need some rework.
    private void initCompFromFile(){
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/irrComp.txt";
        //String fileName = "/Users/ofer/NetBeansProjects/Irrigate/staff2013.txt";
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner in = new Scanner(reader);
        StringTokenizer st;
        in.nextLine();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            st = new StringTokenizer(line);
            ArrayList<String> data = new ArrayList<String>();
            while (st.hasMoreTokens()) {
                data.add(st.nextToken());
            }
            if(data.size() >= 1){
                Component comp = new Component(Integer.parseInt(data.get(0)));
                comp.save();
                components.add(comp);
            }
        }

        in.close();
    }
    
        private void initCropTypeFromFile(){
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/irrComp.txt";
        //String fileName = "/Users/ofer/NetBeansProjects/Irrigate/staff2013.txt";
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner in = new Scanner(reader);
        StringTokenizer st;
        in.nextLine();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            st = new StringTokenizer(line);
            ArrayList<String> data = new ArrayList<String>();
            while (st.hasMoreTokens()) {
                data.add(st.nextToken());
            }
            if(data.size() >= 1){
                Component comp = new Component(Integer.parseInt(data.get(0)));
                comp.save();
                components.add(comp);
            }
        }

        in.close();
    }
    
        
        
    //--------------Singleton Stuff-----------------------//    
    public static Server getInstance() {
        return ServerHolder.INSTANCE;
    }


    private static class ServerHolder {

        private static final Server INSTANCE = new Server();
    }
    
    //----------------------------------------------//
}
