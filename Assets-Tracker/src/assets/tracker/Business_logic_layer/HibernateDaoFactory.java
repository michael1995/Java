/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.Business_logic_layer;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author michaelborodin
 */
public class HibernateDaoFactory<T> {

    public Session _session;
    private Class<T> type;

    public HibernateDaoFactory(Class<T> type) {
        _session = HibernateUtil.getInstance().getCurrentSession();
        this.type = type;
    }

   
    
    /**
     * This method will create an insert query to the database
     * @param object we want to insert 
     * @return the newly created object
     */
    public T save (Object object) {
        try {
           _session.save(object);
            HibernateUtil.getInstance().commitTransaction();
        } catch (RuntimeException ex){
            System.err.println(ex);
            HibernateUtil.getInstance().rollbackTransaction();
            _session.close();
            throw ex;
        }
        return (T)object;
    }
    
     /**
     * This method will create an update query to the database
     * @param object we want to update 
     * @return the updated object
     */
    public T update (Object object) {
        try {
           _session.update(object);
            HibernateUtil.getInstance().commitTransaction();
        } catch (RuntimeException ex){
            System.err.println(ex);
            HibernateUtil.getInstance().rollbackTransaction();
            _session.close();
            throw ex;
        }
        return (T)object;
    }
    
    
    /**
     * @param object that will be delete from the database
     */
    public void delete(Object object) {

        try {
            _session.delete(object);
           HibernateUtil.getInstance().commitTransaction();
        } catch (RuntimeException ex){
             System.err.println(ex); 

             //Roll back any database changes 
             //that have occurred during our transaction
            HibernateUtil.getInstance().rollbackTransaction();

            //close the session
            _session.close();

            //throw the error back to the method calling the delete
            throw ex;
        }
    }
      
     /**
     * @param object we want to insert or update to the database
     * @return object from the database
     */
    public T saveOrUpdate(Object object) {
        try {
           _session.saveOrUpdate(object);
            HibernateUtil.getInstance().commitTransaction();
        } catch (RuntimeException ex){
            System.err.println(ex);
            HibernateUtil.getInstance().rollbackTransaction();
            _session.close();
            throw ex;
        }
        return (T)object;
    }
    
    
    /**
     * 
     * @param id of the object we want to grab from the database
     * @return the object that matches the id
     */
    public T getById(int id) {
        Object entity;
        try {
            entity = (T)_session.get(type, id);  
           
        } catch (RuntimeException ex){
            System.err.println(ex);
            HibernateUtil.getInstance().rollbackTransaction();
            _session.close();
            throw ex;
        }
        return (T)entity;
    }
    
    
    public List<T> getAll() {
        Criteria criteria;
        try {
           criteria = _session.createCriteria(type);  
        } catch (RuntimeException ex){
             System.err.println(ex);
            HibernateUtil.getInstance().rollbackTransaction();
            _session.close();
            throw ex;
        }
        
        return criteria.list(); 
    }
    
    
    public void refresh(Object object) {
        try {
            _session.refresh(object); 
        } catch (RuntimeException ex){
             System.err.println(ex);
            HibernateUtil.getInstance().rollbackTransaction();
            _session.close();
            throw ex;
        }
    }
}
