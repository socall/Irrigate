/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author ofer
 */
public class UserTableModel  extends AbstractListModel implements ComboBoxModel{
    
   // private String [] columnNames = {"firstname", "lastname", "id", "type"};
    
    private ArrayList<User> users_data;
    
    public UserTableModel(ArrayList<User> ud){
        users_data = ud;
    }


    @Override
    public int getSize() {
        return users_data.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getElementAt(int i) {
        return users_data.get(i).firstname + " " + users_data.get(i).lastname;
    }

    @Override
    public void setSelectedItem(Object o) {
        return;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getSelectedItem() {
        return users_data.get(0).firstname;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
