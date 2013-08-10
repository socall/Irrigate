/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;

/**
 *
 * @author ofer
 */
class compListModel extends AbstractListModel implements ListModel {
    
    ArrayList<Component> comps;
    
    compListModel(ArrayList<Component> _comps){
        comps = _comps;
    }
    
    @Override
    public int getSize() {
        return comps.size();
    }

    @Override
    public Object getElementAt(int i) {
        return comps.get(i).id;
    }
    
}
