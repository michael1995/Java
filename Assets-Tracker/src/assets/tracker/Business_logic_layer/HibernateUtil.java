/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.Business_logic_layer;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author michaelborodin
 */


public class HibernateUtil {

     private SessionFactory _sessionFactory = null;
    private static HibernateUtil instance = null;

    /**
     * Set the session factory with the hibernate.cfg.xml file. 
     * We are now ready to access our database
     */
    protected HibernateUtil()  { 
        try {
        //create a file object with the hibernate.cfg.xml file.
        //the file contains all of our configuration (connection string)
        File file = new File("resources/hibernate.cfg.xml");

        //create the config object used by the hibernate's session factory
        //the session factory is used to handle the life of hibernate (sessions to the database)
        Configuration configuration = new Configuration();

        //add our hibernate.cfg.xml file object to the config
        configuration.configure(file);

       //building the service registry required by the session factory
        ServiceRegistry  serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();   

        //and finally we can build the session factory with the configuration
        _sessionFactory = new Configuration().configure(file).buildSessionFactory(serviceRegistry); 

        } 
        catch (RuntimeException ex) {
            // Make sure you log the exception
            Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    /**
     * init the singleton
     * @return the instance created
     */
    public static HibernateUtil getInstance() {
        if (instance == null) {
                instance = new HibernateUtil();
        }
        return instance;
    }
    
    
    public Session getCurrentSession() {
       try {
        if(transctionActive()) {
            return _sessionFactory.getCurrentSession();
        } else {
            beginTransaction();
            return _sessionFactory.getCurrentSession();
        }
       } catch (org.hibernate.HibernateException he) {
           throw he;
       }
    }
    
    /**
     * @return if the transaction is currently active
     */
     public boolean transctionActive() {
        try {
            return _sessionFactory.getCurrentSession().getTransaction().isActive();  
       } catch (org.hibernate.HibernateException he) {
           throw he;
       }
    }
    
    /**
     * if the transaction isn't active, start the transaction to the database
     */
    public void beginTransaction() {
        try {
            if(!transctionActive()) {
                _sessionFactory.getCurrentSession().getTransaction().begin();
            }
         } catch (org.hibernate.HibernateException he) {
           throw he;
        }
    }
    
    /**
     * if the transaction is active, end the transaction to the database
     */
      public void commitTransaction() {
        if(transctionActive()) {
            _sessionFactory.getCurrentSession().getTransaction().commit();  
        }
    }
    
    /**
     * if an error occurs during our transaction, 
     * we will roll back anything changes that are pending.
     */  
    public void rollbackTransaction() {
         _sessionFactory.getCurrentSession().getTransaction().rollback();
    }
  

    
}


