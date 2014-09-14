/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.user_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import assets.tracker.Business_logic_layer.DaoFactory;

import javax.swing.JOptionPane;
import assets.tracker.Business_logic_layer.HibernateUtil;
import assets.tracker.data_Access_layer.Assets;
import static java.awt.image.ImageObserver.WIDTH;
import assets.tracker.data_Access_layer.AssetsMaintenance;

/**
 *
 * @author michaelborodin
 */
public class HomeScreen_Form extends JFrame implements WindowListener, ActionListener {

    JTable jtable;
    DefaultTableModel model;
    String[] header = {"Maintenance Type", "AssetID", "Scheduled Date", "Completed Date"};
    JButton btn_close, btn_Search, btn_new, btn_save, btn_delete, btn_previous, btn_first, btn_next, btn_last, btn_Settings, btn_Maintenance, btn_AssetMaintenance;
    JTextField txt_Id, txt_AssetName, txt_AssetType, txt_Manufacturer, txt_ProductNumber, txt_SerialNumber, txt_ShortDescription, txt_RecordsFound;
    JLabel lbl_Id, lbl_AssetName, lbl_AssetType, lbl_Manufacturer, lbl_ProductNumber, lbl_SerialNumber, lbl_shortDescritpion, lbl_AssetsMaintenanceTypeTable, lbl_RecordsFound;
    String[] toolTip;
    private DaoFactory _DaoFactory;
    private Assets _Assets;

    /**
     *
     */
    public void onload() {

        try {
            setupFrame();
            _DaoFactory = new DaoFactory();

            if (_DaoFactory.getAssetDao().getFirstRecord() != null) {
                _Assets = _DaoFactory.getAssetDao().getById((Integer) _DaoFactory.getAssetDao().getFirstRecord());

                setAsset(_Assets);
            } else {
                _Assets = new Assets();
            }
            txt_RecordsFound.setText(Long.toString((Long) _DaoFactory.getAssetDao().getRowCount()));

        } catch (org.hibernate.HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage());
        }

    }

    public void setAsset(Assets asset) {
        _Assets = asset;
        txt_Id.setText(Integer.toString(asset.getAssetID()));
        txt_AssetName.setText(asset.getAssetName());
        txt_AssetType.setText(asset.getAssetType());
        txt_Manufacturer.setText(asset.getManufacturer());
        txt_ProductNumber.setText(asset.getProductNumber());
        txt_SerialNumber.setText(asset.getSerialNumber());
        txt_ShortDescription.setText(asset.getShortDescription());

        setTableAsset(asset);
        HibernateUtil.getInstance().commitTransaction();

    }

    public void setTableAsset(Assets asset) {
        try {
            model.setRowCount(0);
            for (AssetsMaintenance notes : asset.getAssetMaintenance()) {
                model.addRow(new Object[]{
                    notes.getMaintenanceType(),
                    notes.getScheduledDate(),
                    notes.getCompletedDate(),
                    notes.getAsset().getAssetID()});
            }
        } catch (Exception ex) {
            System.out.println("no table data: " + ex.getMessage());
        }
    }

    public void setupFrame() {
        SpringLayout layout = new SpringLayout();
        frameSettings(layout, 1100, 700, "Reporting System");
        LocateButtons(layout);
        displayLeftPanel(layout);
        displayTable(layout);
        LocateATextFields(layout);
        LocateLabels(layout);
        this.addWindowListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void frameSettings(SpringLayout layout, int w, int h, String title) {
        setSize(w, h);
        setResizable(false);
        setTitle(title);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setLayout(layout);
    }

    private void displayLeftPanel(SpringLayout layout) {
        LeftPanelSetUp(layout, 250, 680);
    }

    private JPanel LeftPanelSetUp(SpringLayout layout, int w, int h) {
        JPanel panel = new JPanel(layout);
        panel.setPreferredSize(new Dimension(w, h));
        panel.setBackground(Color.lightGray);
        add(panel);
        panel.setLayout(layout);
        LeftPanelAddButton(panel);
        return panel;
    }

    private JPanel LeftPanelAddButton(JPanel panel) {
        btn_Settings = new JButton("Settings");
        panel.add(btn_Settings);
        btn_Settings.addActionListener(this);

        return panel;
    }

    public void LocateButtons(SpringLayout layout) {

        btn_close = displayButton(layout, btn_close, "CLOSE", 860, 610, 150, 35, "Close button");
        btn_Search = displayButton(layout, btn_close, "Assets Search", 280, 610, 150, 35, "Search button");
        btn_Maintenance = displayButton(layout, btn_Maintenance, "Maintenance Type", 430, 610, 150, 35, "Maintenance Type form");
        btn_new = displayButton(layout, btn_new, "New", 860, 360, 150, 35, "New button");
        btn_save = displayButton(layout, btn_save, "Save", 860, 395, 150, 35, "Save button");
        btn_delete = displayButton(layout, btn_delete, "Delete", 860, 430, 150, 35, "Delete button");
        btn_first = displayButton(layout, btn_first, "|<", 853, 170, 40, 35, "First Entry button");
        btn_previous = displayButton(layout, btn_previous, "<", 893, 170, 40, 35, "Previous Entry button");
        btn_next = displayButton(layout, btn_next, ">", 933, 170, 40, 35, "Next Entry button");
        btn_last = displayButton(layout, btn_last, ">|", 973, 170, 40, 35, "Last Entry button");
        btn_AssetMaintenance = displayButton(layout, btn_AssetMaintenance, "Asset Maintenace", 580, 610, 150, 35, "Asset Maintenace form");

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

        lbl_Id = LocateALabel(myLabelLayout, lbl_Id, "ID", 260, 50);
        lbl_AssetName = LocateALabel(myLabelLayout, lbl_AssetName, "AssetName", 260, 90);
        lbl_AssetType = LocateALabel(myLabelLayout, lbl_AssetType, "AssetType", 260, 130);
        lbl_Manufacturer = LocateALabel(myLabelLayout, lbl_Manufacturer, "Manufacturer", 260, 170);
        lbl_ProductNumber = LocateALabel(myLabelLayout, lbl_ProductNumber, "ProductNumber", 260, 210);
        lbl_SerialNumber = LocateALabel(myLabelLayout, lbl_SerialNumber, "SerialNumber", 640, 90);
        lbl_shortDescritpion = LocateALabel(myLabelLayout, lbl_shortDescritpion, "ShortDescritpion", 640, 130);
        lbl_AssetsMaintenanceTypeTable = LocateALabel(myLabelLayout, lbl_AssetsMaintenanceTypeTable, "Maintenance Table ", 280, 280);
        lbl_RecordsFound = LocateALabel(myLabelLayout, lbl_RecordsFound, "Records Found", 765, 20);
        lbl_AssetsMaintenanceTypeTable.setForeground(Color.RED);
    }

    public JLabel LocateALabel(SpringLayout myLabelLayout, JLabel myLabel, String LabelCaption, int x, int y) {

        myLabel = new JLabel(LabelCaption);
        add(myLabel);
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }

    public void LocateATextFields(SpringLayout myTextFieldLayout) {
        txt_Id = LocateATextField(myTextFieldLayout, txt_Id, 10, 370, 45, false);
        txt_AssetName = LocateATextField(myTextFieldLayout, txt_AssetName, 20, 370, 85, true);
        txt_AssetType = LocateATextField(myTextFieldLayout, txt_AssetType, 20, 370, 125, true);
        txt_Manufacturer = LocateATextField(myTextFieldLayout, txt_Manufacturer, 20, 370, 165, true);
        txt_ProductNumber = LocateATextField(myTextFieldLayout, txt_ProductNumber, 20, 370, 205, true);
        txt_SerialNumber = LocateATextField(myTextFieldLayout, txt_SerialNumber, 20, 760, 85, true);
        txt_ShortDescription = LocateATextField(myTextFieldLayout, txt_ShortDescription, 20, 760, 125, true);
        txt_RecordsFound = LocateATextField(myTextFieldLayout, txt_RecordsFound, 10, 880, 15, false);

    }

    public JTextField LocateATextField(SpringLayout myTextFieldLayout, JTextField mytextField, int width, int x, int y, boolean isEditable) {
        mytextField = new JTextField(width);
        add(mytextField);
        myTextFieldLayout.putConstraint(SpringLayout.WEST, mytextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, mytextField, y, SpringLayout.NORTH, this);
        mytextField.setEditable(isEditable);
        return mytextField;
    }

    private void displayTable(SpringLayout layout) {
        model = tomodelSetup(model, header, 100, 3);
        jtable = jtableSetup(jtable, layout, model, 280, 300, 560, 300, 23);

    }

    private DefaultTableModel tomodelSetup(DefaultTableModel dtm, String[] tHeader, int rowcount, int ColumnCount) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColumnIndex) {
                return false;
            }
        };
        dtm.setRowCount(rowcount);
        for (int i = 0; i < ColumnCount + 1; i++) {
            dtm.addColumn(tHeader[i]);
        }
        return dtm;

    }

    //add a combo box into the first columns
    private JTable jtableSetup(JTable table, SpringLayout layout, DefaultTableModel dtm, int x, int y, int w, int h, int rowHeight) {
        table = new JTable(dtm); //Adding a table model to the JTable.
        JScrollPane sp = new JScrollPane(table); //Adding the Jtable to a Scroll Pane.
        add(sp);//Adding Scroll pane to the screen
        layout.putConstraint(SpringLayout.WEST, sp, x, SpringLayout.WEST, this);//Setting the location of the Scroll pane
        layout.putConstraint(SpringLayout.NORTH, sp, y, SpringLayout.NORTH, this);
        sp.setPreferredSize(new Dimension(w, h));//Setting size of the Scroll pane       
        table.setRowHeight(rowHeight);//Setting the height of the Row/Cell.        
        table.getTableHeader().setBackground(new Color(00, 00, 99));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setReorderingAllowed(false);//Stops the user from moving the columns around. 
        //table.addKeyListener(this);//Adding a KeyListener to the table. Waiting for a key change.
        table.setBackground(Color.lightGray);
        return table;
    }

    private void getPrevRecord(int id) {

        //make sure the prev record is not null
        if (_DaoFactory.getAssetDao().getPrevRecord(id) != null) {
            _Assets = (Assets) _DaoFactory.getAssetDao().getPrevRecord(id);
            setAsset(_Assets);
        }
    }

    private void getNextRecord(int id) {

        //make sure the next record is not null
        if (_DaoFactory.getAssetDao().getNextRecord(id) != null) {
            _Assets = (Assets) _DaoFactory.getAssetDao().getNextRecord(id);
            setAsset(_Assets);
        }
    }

    private void clearAssetForm() {
        txt_Id.setText(null);
        txt_AssetName.setText(null);
        txt_AssetType.setText(null);
        txt_Manufacturer.setText(null);
        txt_ProductNumber.setText(null);
        txt_SerialNumber.setText(null);
        txt_ShortDescription.setText(null);
        txt_RecordsFound.setText(null);

        model.setRowCount(0);

        _Assets = new Assets();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
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
        if (e.getSource() == btn_close) {
            System.exit(0);
        }
        if (e.getSource() == btn_Search) {
            AssetsSearch_Form frm = new AssetsSearch_Form();
            frm.run();
        }
        if (e.getSource() == btn_Settings) {
            Settings_Form frm = new Settings_Form();
            frm.onLoad();
        }
        if (e.getSource() == btn_AssetMaintenance) {
            AssetsMaintenance_Form frm = new AssetsMaintenance_Form();
            frm.onload();
        }
        if (e.getSource() == btn_Maintenance) {
            AssetsMaintenanceType_Form frm = new AssetsMaintenanceType_Form();
            frm.onLoad();
        }
        if (e.getSource() == btn_first) {
            if (_Assets != null) {
                //loads the first record from the database
                _Assets = _DaoFactory.getAssetDao().getById(
                        (Integer) _DaoFactory.getAssetDao().getFirstRecord());
                setAsset(_Assets);
            }
        }
        if (e.getSource() == btn_previous) {
            if (_Assets != null) {
                getPrevRecord(_Assets.getAssetID());
                setAsset(_Assets);
            }
        }
        if (e.getSource() == btn_next) {
            if (_Assets != null) {
                getNextRecord(_Assets.getAssetID());
                setAsset(_Assets);
            }
        }
        if (e.getSource() == btn_last) {
            if (_Assets != null) {
                //loads the last record from the database
                _Assets = _DaoFactory.getAssetDao().getById(
                        (Integer) _DaoFactory.getAssetDao().getLastRecord());
                setAsset(_Assets);
            }
        }
        if (e.getSource() == btn_save) {
            try {

                if (_Assets != null) {
                    //updates the contact with the current form fields
                    _Assets.update(txt_AssetName.getText(), txt_AssetType.getText(), txt_Manufacturer.getText(),
                            txt_ProductNumber.getText(), txt_SerialNumber.getText(), txt_ShortDescription.getText());

                    //saves the contact to the database
                    _DaoFactory.getAssetDao().saveOrUpdate(_Assets);

                    setAsset(_Assets);
                    //resets the record count
                    txt_RecordsFound.setText(Long.toString((Long) _DaoFactory.getAssetDao().getRowCount()));
                }
            } catch (org.hibernate.HibernateException he) {
                JOptionPane.showMessageDialog(this, he.getMessage());
            }
        }
        if (e.getSource() == btn_new) {
            clearAssetForm();
            //just to try
            txt_RecordsFound.setText(Long.toString((Long) _DaoFactory.getAssetDao().getRowCount()));
        }
        if (e.getSource() == btn_delete) {
            if (_Assets != null) {
                //deletes the contact from the database
                if (_Assets.getAssetID() != 0) {

                    //confirms that we want to delete
                    if (JOptionPane.showOptionDialog(this, "Are you sure you want to delete this contact?", "Delete", WIDTH, JOptionPane.WARNING_MESSAGE, null, null, null)
                            == JOptionPane.YES_OPTION) {

                        //grab the id of the deleting contact because we want to move left or right
                        //after we delete the contact
                        int deletingAssetID = _Assets.getAssetID();

                        //delete the contact
                        _DaoFactory.getAssetDao().delete(_Assets);

                        //move left, right or clear the form
                        if (_DaoFactory.getAssetDao().getPrevRecord(deletingAssetID) != null) {
                            getPrevRecord(deletingAssetID);
                        } else if (_DaoFactory.getAssetDao().getNextRecord(deletingAssetID) != null) {
                            getNextRecord(deletingAssetID);
                        } else {
                            clearAssetForm();
                        }
                    }
                }
                //resets the record count
                txt_RecordsFound.setText(Long.toString((Long) _DaoFactory.getAssetDao().getRowCount()));
            }
        }

    }
}
