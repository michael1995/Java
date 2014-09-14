/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.data_Access_layer;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author michaelborodin
 */
@Entity
@Table(name = "AssetsMaintenanceType")
public class AssetsMaintenanceType {

    private int maintenanceTypeID;
    private String maintenanceType;
    private String mthsToNextMaintenance;

    private Set<AssetsMaintenance> AssetsMaintenance;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaintenanceTypeID")
    public int getMaintenanceTypeID() {
        return maintenanceTypeID;
    }

    public void setMaintenanceTypeID(int maintenanceTypeID) {
        this.maintenanceTypeID = maintenanceTypeID;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getMthsToNextMaintenance() {
        return mthsToNextMaintenance;
    }

    public void setMthsToNextMaintenance(String mthsToNextMaintenance) {
        this.mthsToNextMaintenance = mthsToNextMaintenance;
    }
    
    @OneToMany
    public Set<AssetsMaintenance> getAssetsMaintenance() {
        return AssetsMaintenance;
    }

    public void setAssetsMaintenance(Set<AssetsMaintenance> AssetsMaintenance) {
        this.AssetsMaintenance = AssetsMaintenance;
    }

 
    public void update(String maintenanceType, String mthsToNextMaintenance ){
      
        this.maintenanceType = maintenanceType;
        this.mthsToNextMaintenance = mthsToNextMaintenance;
    }
}
