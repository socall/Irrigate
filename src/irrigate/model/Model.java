/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author ofer
 */
public interface Model {
     public AbstractListModel getCompModel();
     public ComboBoxModel getUserModel();   
}
