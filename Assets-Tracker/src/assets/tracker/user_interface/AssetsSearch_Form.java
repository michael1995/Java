/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.user_interface;

import assets.tracker.Business_logic_layer.DaoFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import assets.tracker.data_Access_layer.Assets;
import java.awt.event.MouseAdapter;
/**
 *
 * @author michaelborodin
 */
public class AssetsSearch_Form extends JFrame implements WindowListener, ActionListener {

    JButton btn_close, btn_Search;
    JLabel lbl_AssetName, lbl_RecordsCount, lbl_AssetType;
    JTextField txt_AssetName, txt_AssetType, txt_recordsCount;
    String[] header = {"ID", "Asset Name", "Asset Type", "Manufacturer"};
    JTable jtable;
    DefaultTableModel model;
    private DaoFactory _daoFactory;

    public void run() {
        setpUpFrame();
        _daoFactory = new DaoFactory();
        searchAsset("","");
        
    }
    
    
    
    public void setpUpFrame() {
        SpringLayout layout = new SpringLayout();
        frameSettings(layout, 750, 550, "Assets Search");
        LocateButtons(layout);
        LocateATextFields(layout);
        LocateLabels(layout);
        displayTable(layout);
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


        btn_close = displayButton(layout, btn_close, "CLOSE", 570, 140, 150, 35);
        btn_Search = displayButton(layout, btn_close, "Search", 400, 140, 150, 35);



    }

    public JButton displayButton(SpringLayout buttonLayout, JButton button, String ButtonCaption, int x, int y, int w, int h) {    //The LocateButtons class pass through here and return a Button
        button = new JButton(ButtonCaption);
        add(button);
        button.addActionListener(this);
        buttonLayout.putConstraint(SpringLayout.WEST, button, x, SpringLayout.WEST, this);
        buttonLayout.putConstraint(SpringLayout.NORTH, button, y, SpringLayout.NORTH, this);
        button.setPreferredSize(new Dimension(w, h));
        return button;
    }

    public void LocateLabels(SpringLayout myLabelLayout) {

        

        lbl_AssetName = LocateALabel(myLabelLayout, lbl_AssetName, "Asset Name:", 30, 75);
        lbl_AssetType = LocateALabel(myLabelLayout, lbl_AssetType, "Asset Type:", 30, 110);
        lbl_RecordsCount = LocateALabel(myLabelLayout, lbl_RecordsCount, "Records Found:", 115, 150);
        

    }

    public JLabel LocateALabel(SpringLayout myLabelLayout, JLabel myLabel, String LabelCaption, int x, int y) {

        myLabel = new JLabel(LabelCaption);
        add(myLabel);
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }

    public void LocateATextFields(SpringLayout myTextFieldLayout) {

        //JTextField txt_FirstName, txt_LastName;
        txt_AssetName = LocateATextField(myTextFieldLayout, txt_AssetName, 50, 110, 70);
        txt_AssetType = LocateATextField(myTextFieldLayout, txt_AssetType, 50, 110, 105);
        txt_recordsCount = LocateATextField(myTextFieldLayout, txt_recordsCount, 10, 215, 145);
        txt_recordsCount.setEnabled(false);

    }

    public JTextField LocateATextField(SpringLayout myTextFieldLayout, JTextField mytextField, int width, int x, int y) {
        mytextField = new JTextField(width);
        add(mytextField);
        myTextFieldLayout.putConstraint(SpringLayout.WEST, mytextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, mytextField, y, SpringLayout.NORTH, this);

        return mytextField;
    }

    private void displayTable(SpringLayout layout) {
        model = tomodelSetup(model, header, 100, 3);
        jtable = jtableSetup(jtable, layout, model, 10, 200, 730, 250, 23);

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
    private void searchAsset(String assetName, String assetType) {
        try {
            List<Assets> contactsFound = _daoFactory.getAssetDao().getAssetsLikeAssetNameAndAssetType(assetName, assetType);

            txt_recordsCount.setText(Integer.toString(contactsFound.size()));
            
            //reset all rows
            model.setRowCount(0);
            //loop over our contacts and add them to the table
            for (Assets assets : contactsFound) {

                model.addRow(new Object[]{
                            assets.getAssetID(),
                            assets.getAssetName(),
                            assets.getAssetType(),
                            assets.getManufacturer(),
                            
                });
            }
        
        } catch (org.hibernate.HibernateException he) {
           JOptionPane.showMessageDialog(this, he.getMessage());
       }
        
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
        if (e.getSource() == btn_Search){
            
            searchAsset(txt_AssetName.getText(), txt_AssetType.getText());
        }
        
       
    }
}
