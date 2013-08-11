/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




/**
 *
 * @author ofer
 */
@Entity
public class CropType extends saveable{
    @Id @GeneratedValue
    private int id;
    private String name;
    private Season season;
    private int tempeture;
    private int humidity;
    private Precipitation precipitation;
    private int duration;
    private int fertilizer_ammount;
}
