/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.controller;

import irrigate.model.Server;

/**
 *
 * @author ofer
 */
public class Controller {
    
    private Server model;
    
    Controller(){
        model = Server.getInstance();
    }
    
}