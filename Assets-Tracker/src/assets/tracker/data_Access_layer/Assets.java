/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.data_Access_layer;

import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author michaelborodin
 */
@Entity
@Table(name = "Assets")
public class Assets {

    private int assetID;
    private String assetName;
    private String assetType;
    private String manufacturer;
    private String productNumber;
    private String serialNumber;
    private String shortDescription;
    private Set<AssetsMaintenance> assetMaintenance;
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AssetID")
    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

   @OneToMany
   @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
   @JoinColumn(name = "AssetID", referencedColumnName = "AssetID")
    public Set<AssetsMaintenance> getAssetMaintenance() {
        return assetMaintenance;
    }

    public void setAssetMaintenance(Set<AssetsMaintenance> assetMaintenance) {
        this.assetMaintenance = assetMaintenance;
    }
    
    
    
    
    public void update(String assetName, String assetType, String manufacturer, String productNumber, String serialNumber, String shortDescription) {
        this.assetName = assetName;
        this.assetType = assetType;
        this.manufacturer = manufacturer;
        this.productNumber = productNumber;
        this.serialNumber = serialNumber;
        this.shortDescription = shortDescription;
    }
}
