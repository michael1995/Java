/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.Business_logic_layer;

import assets.tracker.Business_logic_layer_dao.*;
/**
 *
 * @author michaelborodin
 */
public class DaoFactory {
    
   public AssetsDao getAssetDao(){
       return new AssetsDao();
   }
   public AssetsMaintenanceDao getMaintenanceDao(){
       return new AssetsMaintenanceDao();
   }
   public AssetsMaintenanceTypeDao getMaintenanceTypeDao(){
       return new AssetsMaintenanceTypeDao();
   }
   
}
