/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package irrigate.controller;

import irrigate.model.Server;
import irrigate.view.ViewFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;

/**
 *
 * @author ofer
 */
public class Controller {
    
    private Server model;
    private ViewFrame view;
    
    Controller(){
        model = Server.getInstance();
        
        view = new ViewFrame();
        view.setVisible(true);

        view.setUserList(model.getUserModel());
        
        //Set Listner for Components Map view:
        ActionListener mapViewListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO add data from model
                view.setComponentList(model.getCompModel());
            }
        };        
        
        view.getViewMap().addActionListener(mapViewListener);

        
        
//        //Set Listner for users view:
//        ActionListener userViewListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("sadasdsdasdasas");
//                
//            }
//        };        
//        
//        view.getUserView().addActionListener(mapViewListener);

        
        
        //        ViewFrame vf = new ViewFrame((ComboBoxModel)model.getUserModel());
//        
//        vf.setVisible(true);
        
        
    }
    
}