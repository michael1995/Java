/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.user_interface;

import assets.tracker.Business_logic_layer.DaoFactory;
import assets.tracker.Business_logic_layer.HibernateUtil;
import assets.tracker.data_Access_layer.Assets;
import assets.tracker.data_Access_layer.AssetsMaintenance;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author michaelborodin
 */
public class AssetsMaintenance_Form extends JFrame implements WindowListener, ActionListener {

    JButton btn_close, btn_new, btn_save, btn_delete,btn_previous, btn_first, btn_next, btn_last;
    JTextField txt_MaintenanceTypeID, txt_scheduledDate, txt_CompleteDate, txt_AssetID,txt_RecordsFound;
    JLabel lbl_MaintenanceTypeID, lbl_scheduledDate, lbl_CompleteDate, lbl_AssetID,lbl_RecordsFound;
    String[] toolTip;
    private DaoFactory _DaoFactory;
    private AssetsMaintenance _Maintenance;
    private Assets _Assets;

    public void onload() {
        setupFrame();
        _DaoFactory = new DaoFactory();
        if (_Maintenance != null) {
            //we know this is an existing note, so load the note to the form
            setAsset(_Maintenance);
        } else if (_Assets != null) {
            
            //this is a new note, so load the contact details (contactID) only
            fillContactForm(_Assets);
            
            //create a new contact note object
            _Maintenance = new AssetsMaintenance();
        }
    }

    public void setAsset(AssetsMaintenance maintenance) {
        _Maintenance = maintenance;
        txt_MaintenanceTypeID.setText(Integer.toString(maintenance.getMaintenanceType()));
        txt_AssetID.setText(Integer.toString(maintenance.getAsset().getAssetID()));
        txt_scheduledDate.setText(maintenance.getScheduledDate());
        txt_CompleteDate.setText(maintenance.getCompletedDate());
        HibernateUtil.getInstance().commitTransaction();

    }
    private void fillContactForm(Assets asset) {
        txt_AssetID.setText(Integer.toString(asset.getAssetID()));
    }

    public void setupFrame() {
        SpringLayout layout = new SpringLayout();
        frameSettings(layout, 700, 400, "Settings");
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

        lbl_AssetID = LocateALabel(myLabelLayout, lbl_AssetID, " Asset ID", 10, 145);
        lbl_MaintenanceTypeID = LocateALabel(myLabelLayout, lbl_MaintenanceTypeID, "MaintenanceTypeID", 10, 175);
        lbl_CompleteDate = LocateALabel(myLabelLayout, lbl_CompleteDate, "CompleteDate", 10, 235);
        lbl_scheduledDate = LocateALabel(myLabelLayout, lbl_scheduledDate, "scheduledDate", 10, 205);
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


        txt_AssetID = LocateATextField(myTextFieldLayout, txt_AssetID, 10, 150, 140, false);
        txt_MaintenanceTypeID = LocateATextField(myTextFieldLayout, txt_MaintenanceTypeID, 20, 150, 170, true);
        txt_scheduledDate = LocateATextField(myTextFieldLayout, txt_scheduledDate, 20, 150, 200, true);
        txt_CompleteDate = LocateATextField(myTextFieldLayout, txt_CompleteDate, 20, 150, 230, true);
        txt_RecordsFound = LocateATextField(myTextFieldLayout, txt_RecordsFound, 10, 150, 35, false);
    }

    public JTextField LocateATextField(SpringLayout myTextFieldLayout, JTextField mytextField, int width, int x, int y, boolean isEditable) {
        mytextField = new JTextField(width);
        add(mytextField);
        myTextFieldLayout.putConstraint(SpringLayout.WEST, mytextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, mytextField, y, SpringLayout.NORTH, this);
        mytextField.setEditable(isEditable);
        return mytextField;
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
        if (e.getSource() == btn_save) {
            try {

                if (_Maintenance != null) {
                    //updates the contact with the current form fields
                    _Maintenance.update(txt_scheduledDate.getText(), txt_CompleteDate.getText());

                    //saves the contact to the database
                    _DaoFactory.getAssetDao().saveOrUpdate(_Maintenance);

                    //setAsset(_Maintenance);
                    this.dispose();
                    //resets the record count
                    // txt_RecordsFound.setText(Long.toString((Long) _DaoFactory.getAssetDao().getRowCount()));
                }
            } catch (org.hibernate.HibernateException he) {
                JOptionPane.showMessageDialog(this, he.getMessage());
            }

        }
    }
}
