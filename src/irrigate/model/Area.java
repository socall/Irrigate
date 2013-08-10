/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package irrigate.model;

import java.util.Date;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author ofer
 */
public class Area {
    private int id;
    //Not sire if place string or cordinates
    private Point position;
    private int size;
    //private (???) preferd_hours;
    private Date start_time;
    private Date end_time;
    private int max_precipitation;
    private ArrayList<Crop> crops;
    private ArrayList<Zone> zones;
}


