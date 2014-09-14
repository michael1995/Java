/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.Business_logic_layer_dao;

import assets.tracker.Business_logic_layer.HibernateDaoFactory;
import assets.tracker.data_Access_layer.AssetsMaintenance;


/**
 *
 * @author michaelborodin
 */
public class AssetsMaintenanceDao extends  HibernateDaoFactory<AssetsMaintenance>{

    public AssetsMaintenanceDao() {
        super(AssetsMaintenance.class);
    }
    
}
