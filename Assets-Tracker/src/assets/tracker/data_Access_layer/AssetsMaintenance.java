/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.data_Access_layer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author michaelborodin
 */
@Entity
@Table(name = "AssetsMaintenance")
public class AssetsMaintenance {
    
    
    
    private int MaintenanceTypeId;
    //add in an embedded field for the maintenance type 
    private Assets Assets;
    private AssetsMaintenanceType Maintenance;
    private String ScheduledDate;
    private String CompletedDate;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "MaintenanceTypeID")
    public int getMaintenanceType() {
        return MaintenanceTypeId;
    }

    public void setMaintenanceType(int MaintenanceType) {
        this.MaintenanceTypeId = MaintenanceType;
    }

    @Column(name = "ScheduledDate")
    public String getScheduledDate() {
        return ScheduledDate;
    }

    public void setScheduledDate(String ScheduledDate) {
        this.ScheduledDate = ScheduledDate;
    }

    /**
     *
     * @return
     */
    @Column(name = "CompleteDate")
    public String getCompletedDate() {
        return CompletedDate;
    }

    public void setCompletedDate(String CompletedDate) {
        this.CompletedDate = CompletedDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AssetID", referencedColumnName = "AssetID")
    public Assets getAsset() {
        return Assets;
    }
    
    public void setAsset(Assets Assets) {
        this.Assets = Assets;
    }

    @ManyToOne
    public AssetsMaintenanceType getMaintenance() {
        return Maintenance;
    }

    public void setMaintenance(AssetsMaintenanceType Maintenance) {
        this.Maintenance = Maintenance;
    }
    
    
    
    public void update(String ScheduledDate, String CompletedDate){
         
        
        this.ScheduledDate = ScheduledDate;
        this.CompletedDate = CompletedDate;
    }
    
    
}
