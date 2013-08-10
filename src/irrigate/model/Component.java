/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author ofer
 */

@Entity
public class Component extends saveable{
    @Id @GeneratedValue
    
    int id;
    private Area area;
    private ArrayList<Sensor> sensors;
    private ArrayList<Tap> taps;
    private ArrayList<IrrigPlan> plans;

    Component(int _id) {
        this.id = _id;
    }
}
