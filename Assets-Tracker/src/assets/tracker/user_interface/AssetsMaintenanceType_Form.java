/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.user_interface;

import assets.tracker.Business_logic_layer.DaoFactory;
import assets.tracker.Business_logic_layer.HibernateUtil;
import assets.tracker.data_Access_layer.Assets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import assets.tracker.data_Access_layer.AssetsMaintenanceType;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author michaelborodin
 */
public class AssetsMaintenanceType_Form extends JFrame implements WindowListener, ActionListener {

    JButton btn_close, btn_new, btn_save, btn_delete, btn_previous, btn_first, btn_next, btn_last;
    JComboBox comboBox;
    JTextField txt_MaintenanceTypeID, txt_MaintenanceType, txt_MthsToNextMaintenance,txt_RecordsFound;
    JLabel lbl_MaintenanceTypeID, lbl_MaintenanceType, lbl_MthsToNextMaintenance, lbl_RecordsFound;
    String[] toolTip;
    private DaoFactory _DaoFactory;
    private AssetsMaintenanceType _MaintenanceType;
    
    public void onLoad() {
        
        
        try {
            frameSetUp();
            _DaoFactory = new DaoFactory();

            if (_DaoFactory.getMaintenanceTypeDao().getFirstRecord() != null) {
                _MaintenanceType = _DaoFactory.getMaintenanceTypeDao().getById((Integer) _DaoFactory.getMaintenanceTypeDao().getFirstRecord());
             
                setAsset(_MaintenanceType);
            } else {
                _MaintenanceType = new AssetsMaintenanceType();
            }
            txt_RecordsFound.setText(Long.toString((Long) _DaoFactory.getMaintenanceTypeDao().getRowCount()));

        } catch (org.hibernate.HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage());
        }

    }
 public void setAsset(AssetsMaintenanceType assetsMaintenanceType) {
        _MaintenanceType = assetsMaintenanceType;
        txt_MaintenanceTypeID.setText(Integer.toString(assetsMaintenanceType.getMaintenanceTypeID()));
        txt_MaintenanceType.setText(assetsMaintenanceType.getMaintenanceType());
        txt_MthsToNextMaintenance.setText(assetsMaintenanceType.getMthsToNextMaintenance());
       
        HibernateUtil.getInstance().commitTransaction();

    }
    public void frameSetUp() {
        SpringLayout layout = new SpringLayout();
        frameSettings(layout, 650, 400, "Maintenance Form");
        LocateButtons(layout);
        LocateATextFields(layout);
        LocateLabels(layout);
        this.addWindowListener(this);
        setVisible(true);
    }

    private void frameSettings(SpringLayout layout, int w, int h, String title) {
        setSize(w, h);
        setResizable(false);
        setTitle(title);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.lightGray);
        setLayout(layout);

    }
    public void LocateButtons(SpringLayout layout) {

        btn_close = displayButton(layout, btn_close, "CLOSE", 470, 300, 150, 35, "Close button");
        btn_new = displayButton(layout, btn_new, "New", 470, 155, 150, 35, "New button");
        btn_save = displayButton(layout, btn_save, "Save", 470, 190, 150, 35, "Save button");
        btn_delete = displayButton(layout, btn_delete, "Delete", 470, 225, 150, 35, "Delete button");
        btn_first = displayButton(layout, btn_first, "|<", 10, 300, 40, 35, "First Entry button");
        btn_previous = displayButton(layout, btn_previous, "<", 50, 300, 40, 35, "Previous Entry button");
        btn_next = displayButton(layout, btn_next, ">", 90, 300, 40, 35, "Next Entry button");
        btn_last = displayButton(layout, btn_last, ">|", 130, 300, 40, 35, "Last Entry button");
        

    }

    public JButton displayButton(SpringLayout buttonLayout, JButton button, String ButtonCaption, int x, int y, int w, int h, String toolTip) {    //The LocateButtons class pass through here and return a Button

        button = new JButton(ButtonCaption);
        add(button);
        button.addActionListener(this);
        buttonLayout.putConstraint(SpringLayout.WEST, button, x, SpringLayout.WEST, this);
        buttonLayout.putConstraint(SpringLayout.NORTH, button, y, SpringLayout.NORTH, this);
        button.setPreferredSize(new Dimension(w, h));
        button.setToolTipText(toolTip);
        return button;
    }

    public void LocateLabels(SpringLayout myLabelLayout) {

        lbl_MaintenanceTypeID = LocateALabel(myLabelLayout, lbl_MaintenanceTypeID, "Maintenance Type ID", 10, 160);
        lbl_MaintenanceType = LocateALabel(myLabelLayout, lbl_MaintenanceType, "Maintenance Type", 10, 200);
        lbl_MthsToNextMaintenance = LocateALabel(myLabelLayout, lbl_MthsToNextMaintenance, "Months To Next Maintenance", 10, 235);
        lbl_RecordsFound = LocateALabel(myLabelLayout, lbl_RecordsFound, "Records Found", 10, 40);
    }

    public JLabel LocateALabel(SpringLayout myLabelLayout, JLabel myLabel, String LabelCaption, int x, int y) {

        myLabel = new JLabel(LabelCaption);
        add(myLabel);
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }

    public void LocateATextFields(SpringLayout myTextFieldLayout) {
        txt_MaintenanceTypeID = LocateATextField(myTextFieldLayout, txt_MaintenanceTypeID, 10, 195, 155, false);
        txt_MaintenanceType = LocateATextField(myTextFieldLayout, txt_MaintenanceType, 20, 195, 195, true);
        txt_MthsToNextMaintenance = LocateATextField(myTextFieldLayout, txt_MthsToNextMaintenance, 20, 195, 230, true);
        txt_RecordsFound = LocateATextField(myTextFieldLayout, txt_RecordsFound, 10, 195, 35, false);
        
    }

    public JTextField LocateATextField(SpringLayout myTextFieldLayout, JTextField mytextField, int width, int x, int y, boolean isEditable) {
        mytextField = new JTextField(width);
        add(mytextField);
        myTextFieldLayout.putConstraint(SpringLayout.WEST, mytextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, mytextField, y, SpringLayout.NORTH, this);
        mytextField.setEditable(isEditable);
        return mytextField;
    }
   
    private void getPrevRecord(int id) {

        //make sure the prev record is not null
        if (_DaoFactory.getMaintenanceTypeDao().getPrevRecord(id) != null) {
            _MaintenanceType = (AssetsMaintenanceType) _DaoFactory.getMaintenanceTypeDao().getPrevRecord(id);
            setAsset(_MaintenanceType);
        }
    }

    private void getNextRecord(int id) {

        //make sure the next record is not null
        if (_DaoFactory.getMaintenanceTypeDao().getNextRecord(id) != null) {
            _MaintenanceType = (AssetsMaintenanceType) _DaoFactory.getMaintenanceTypeDao().getNextRecord(id);
            setAsset(_MaintenanceType);
        }
    }

    private void clearAssetForm() {
        txt_MaintenanceTypeID.setText(null);
        txt_MaintenanceType.setText(null);
        txt_MthsToNextMaintenance.setText(null);
        txt_RecordsFound.setText(null);

        
        _MaintenanceType = new AssetsMaintenanceType();
    }


    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == btn_close){
           this.dispose();
       }
       if (e.getSource() == btn_first) {
            if (_MaintenanceType != null) {
                //loads the first record from the database
                _MaintenanceType = _DaoFactory.getMaintenanceTypeDao().getById(
                        (Integer) _DaoFactory.getMaintenanceTypeDao().getFirstRecord());
                setAsset(_MaintenanceType);
            }
        }
        if (e.getSource() == btn_previous) {
            if (_MaintenanceType != null) {
                getPrevRecord(_MaintenanceType.getMaintenanceTypeID());
                setAsset(_MaintenanceType);
            }
        }
        if (e.getSource() == btn_next) {
            if (_MaintenanceType != null) {
                getNextRecord(_MaintenanceType.getMaintenanceTypeID());
                setAsset(_MaintenanceType);
            }
        }
        if (e.getSource() == btn_last) {
            if (_MaintenanceType != null) {
                //loads the last record from the database
                _MaintenanceType = _DaoFactory.getMaintenanceTypeDao().getById(
                        (Integer) _DaoFactory.getMaintenanceTypeDao().getLastRecord());
                setAsset(_MaintenanceType);
            }
        }
        if (e.getSource() == btn_save) {
            try {

                if (_MaintenanceType != null) {
                    //updates the contact with the current form fields
                    _MaintenanceType.update(txt_MaintenanceType.getText(), txt_MthsToNextMaintenance.getText());

                    //saves the contact to the database
                    _DaoFactory.getMaintenanceTypeDao().saveOrUpdate(_MaintenanceType);

                    setAsset(_MaintenanceType);
                    //resets the record count
                    txt_RecordsFound.setText(Long.toString((Long) _DaoFactory.getMaintenanceTypeDao().getRowCount()));
                }
            } catch (org.hibernate.HibernateException he) {
                JOptionPane.showMessageDialog(this, he.getMessage());
            }
        }
        if (e.getSource() == btn_new) {
            clearAssetForm();
            //just to try
            txt_RecordsFound.setText(Long.toString((Long) _DaoFactory.getMaintenanceTypeDao().getRowCount()));
        }
        if (e.getSource() == btn_delete) {
            if (_MaintenanceType != null) {
                //deletes the contact from the database
                if (_MaintenanceType.getMaintenanceTypeID()!= 0) {

                    //confirms that we want to delete
                    if (JOptionPane.showOptionDialog(this, "Are you sure you want to delete this contact?", "Delete", WIDTH, JOptionPane.WARNING_MESSAGE, null, null, null)
                            == JOptionPane.YES_OPTION) {

                        //grab the id of the deleting contact because we want to move left or right
                        //after we delete the contact
                        int deletingAssetID = _MaintenanceType.getMaintenanceTypeID();

                        //delete the contact
                        _DaoFactory.getMaintenanceTypeDao().delete(_MaintenanceType);

                        //move left, right or clear the form
                        if (_DaoFactory.getMaintenanceTypeDao().getPrevRecord(deletingAssetID) != null) {
                            getPrevRecord(deletingAssetID);
                        } else if (_DaoFactory.getMaintenanceTypeDao().getNextRecord(deletingAssetID) != null) {
                            getNextRecord(deletingAssetID);
                        } else {
                            clearAssetForm();
                        }
                    }
                }
                //resets the record count
                txt_RecordsFound.setText(Long.toString((Long) _DaoFactory.getMaintenanceTypeDao().getRowCount()));
            }
        }
    }
}
