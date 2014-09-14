/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.Business_logic_layer_dao;

import assets.tracker.Business_logic_layer.HibernateDaoFactory;
import assets.tracker.Business_logic_layer.HibernateUtil;
import assets.tracker.data_Access_layer.AssetsMaintenanceType;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author michaelborodin
 */
public class AssetsMaintenanceTypeDao extends HibernateDaoFactory<AssetsMaintenanceType> {

    public AssetsMaintenanceTypeDao() {
        super(AssetsMaintenanceType.class);
    }

    public Object getRowCount() {
        return HibernateUtil.getInstance().getCurrentSession()
                .createCriteria(AssetsMaintenanceType.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public Object getFirstRecord() {
        //gets the current session to create and run our query
        return HibernateUtil.getInstance().getCurrentSession()
                //creates the criteria based on the contact class
                .createCriteria(AssetsMaintenanceType.class)
                //sets the projection to find the minimum contactID 
                .setProjection(Projections.min("maintenanceTypeID"))
                //return a unique contactID as a number
                .uniqueResult();
    }

    /**
     * @return The ID of the last record found in the contact table
     */
    public Object getLastRecord() {

        //gets the current session to create and run our query
        return HibernateUtil.getInstance().getCurrentSession()
                //creates the criteria based on the contact class
                .createCriteria(AssetsMaintenanceType.class)
                //sets the projection to find the max contactID 
                .setProjection(Projections.max("maintenanceTypeID"))
                //return a unique contactID as a number
                .uniqueResult();
    }

    public Object getNextRecord(int maintenanceTypeID) {

        //gets the current session to create and run our query
        return HibernateUtil.getInstance().getCurrentSession()
                //creates the criteria based on the contact class
                .createCriteria(AssetsMaintenanceType.class)
                //add a restriction to grab records greater than the current contact ID
                .add(Restrictions.gt("maintenanceTypeID", maintenanceTypeID))
                //limit our results to only 1
                .setMaxResults(1)
                //return a unique contact
                .uniqueResult();
    }

    /**
     * @param contactID of the current contact ID
     * @return the previous contact
     */
    public Object getPrevRecord(int maintenanceTypeID) {

        //gets the current session to create and run our query
        return HibernateUtil.getInstance().getCurrentSession()
                //creates the criteria based on the contact class
                .createCriteria(AssetsMaintenanceType.class)
                //add a restriction to grab records less than the current contact ID
                .add(Restrictions.lt("maintenanceTypeID", maintenanceTypeID))
                //order the list by descending
                .addOrder(Order.desc("maintenanceTypeID"))
                //limit our results to only 1
                .setMaxResults(1)
                //return a unique contact
                .uniqueResult();
    }

    public void refresh(AssetsMaintenanceType AssetsMaintenanceType) {
        super.refresh(AssetsMaintenanceType);
    }
}
