/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ofer
 */
public class UserTableModel extends AbstractTableModel{
    
    private String [] columnNames = {"firstname", "lastname", "id", "type"};
    
    private ArrayList<User> users_data;
    
    public UserTableModel(ArrayList<User> ud){
        users_data = ud;
    }

    @Override
    public int getRowCount() {
        return users_data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User my_user = users_data.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return my_user.firstname;
            case 1:
                return my_user.lastname;
            case 2:
                return my_user.id;
            case 3:
                return my_user.type.toString();
            default:
                throw new UnsupportedOperationException("Not supported column!");    
        }
        
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
}
