/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.Business_logic_layer_dao;

import assets.tracker.Business_logic_layer.HibernateDaoFactory;
import assets.tracker.Business_logic_layer.HibernateUtil;
import assets.tracker.data_Access_layer.Assets;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author michaelborodin
 */
public class AssetsDao extends HibernateDaoFactory<Assets>{
    
    public AssetsDao(){
        super(Assets.class);
    }
    public Object getRowCount(){
        return HibernateUtil.getInstance().getCurrentSession()
                .createCriteria(Assets.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
    public List<Assets> getAssetsThatEqualAssetType(String assetType){
        return HibernateUtil.getInstance().getCurrentSession()
                .createCriteria(Assets.class)
                .add(Restrictions.eq("assetType", assetType))
                .list();
    }
      public List<Assets> getAssetsThatContainsAssetType(String assetType) {

        //gets the current session to create and run our query
        return  HibernateUtil.getInstance().getCurrentSession()

                //creates the criteria based on the contact class
                .createCriteria(Assets.class)

                //add the restriction where the last name contains the parameter
               .add(Restrictions.like("assetType", "%" + assetType + "%"))

                //return the list of contacts that match the criteria
                .list();

    }
        public List<Assets> getAssetsLikeAssetNameAndAssetType(String assetName, String assetType) {

        //gets the current session to create and run our query
        return  HibernateUtil.getInstance().getCurrentSession()

                //creates the criteria based on the contact class
                .createCriteria(Assets.class)

                //add the restriction where the first name contains the parameter
                 .add(Restrictions.like("assetName", "%" + assetName + "%"))
                
                //add the restriction where the last name contains the parameter
                .add(Restrictions.like("assetType", "%" + assetType + "%"))

                //return the list of contacts that match the criteria
                .list();

    }
        public Object getFirstRecord() {
          //gets the current session to create and run our query
         return  HibernateUtil.getInstance().getCurrentSession()

                 //creates the criteria based on the contact class
                 .createCriteria(Assets.class)

                 //sets the projection to find the minimum contactID 
                 .setProjection(Projections.min("assetID"))

                  //return a unique contactID as a number
                 .uniqueResult();
                 
        }
     
        /**
         * @return The  ID of the last record found in the contact table
         */
         public Object getLastRecord() {

            //gets the current session to create and run our query
           return  HibernateUtil.getInstance().getCurrentSession()

                   //creates the criteria based on the contact class
                   .createCriteria(Assets.class)

                   //sets the projection to find the max contactID 
                   .setProjection(Projections.max("assetID"))

                    //return a unique contactID as a number
                   .uniqueResult();
        }
         public Object getNextRecord(int assetID) {

           //gets the current session to create and run our query
            return  HibernateUtil.getInstance().getCurrentSession()

                     //creates the criteria based on the contact class
                    .createCriteria(Assets.class)

                    //add a restriction to grab records greater than the current contact ID
                    .add(Restrictions.gt("assetID", assetID))

                    //limit our results to only 1
                    .setMaxResults(1)

                    //return a unique contact
                    .uniqueResult();
        }
    
        /**
         * @param contactID of the current contact ID
         * @return the previous contact
         */
        public Object getPrevRecord(int assetID) {

            //gets the current session to create and run our query
           return HibernateUtil.getInstance().getCurrentSession()

                    //creates the criteria based on the contact class
                   .createCriteria(Assets.class)

                   //add a restriction to grab records less than the current contact ID
                   .add(Restrictions.lt("assetID", assetID))

                   //order the list by descending
                   .addOrder(Order.desc("assetID"))

                   //limit our results to only 1
                   .setMaxResults(1)

                   //return a unique contact
                   .uniqueResult();
        }
    

    public void refresh(Assets asset) {
        super.refresh(asset);
    }
    
    
    
    
    
    
    
    
}
