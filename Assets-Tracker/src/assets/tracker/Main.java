/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker;

import assets.tracker.user_interface.HomeScreen_Form;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Hibernate;
import assets.tracker.Business_logic_layer.HibernateUtil;
import assets.tracker.data_Access_layer.Assets;

/**
 *
 * @author michaelborodin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        

        try {
            Properties props = new Properties();
            props.load(new FileInputStream("resources/log4j.properties"));
            PropertyConfigurator.configure(props);
        }
        catch (IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        try {
            HibernateUtil.getInstance().getCurrentSession().isOpen();
        }
        catch (Exception ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        HomeScreen_Form frm = new HomeScreen_Form();
        frm.onload();
        
        
        
       
       



    }
}
