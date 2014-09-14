 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.tracker.user_interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SpringLayout;

/**
 *
 * @author michaelborodin
 */
public class Settings_Form extends JFrame implements WindowListener, ActionListener {

    JMenuBar menuBar;
    JMenuItem item_Close;
    

    public void onLoad() {
        SpringLayout layout = new SpringLayout();
        frameSettings(layout, 700, 400, "Settings");
        displayMenuBar(layout);
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

    public void displayMenuBar(SpringLayout layout) {
        menuBarSetUp(layout);
    }

    public JMenuBar menuBarSetUp(SpringLayout layout) {
        JMenuBar menu = new JMenuBar();

        //setting the string array for the items
        String[] FileOptions = {"new", "save", "delete", "close"};
        item_Close = new JMenuItem(FileOptions[3]);


        //adding the menu items
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        //calling the methods of the menubars 
        menuBarAddItems(menu, file, edit);
        menuBarActionEvents(menu);
        return menu;

    }

    public JMenuBar menuBarAddItems(JMenuBar menu, JMenu file, JMenu edit) {

        menu.add(file);
        menu.add(edit);
        setJMenuBar(menu);
        file.add(item_Close);

        return menu;
    }

    public JMenuBar menuBarActionEvents(JMenuBar menu) {
        item_Close.addActionListener(this);

        return menu;
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
        if (e.getSource() == item_Close) {
          this.dispose();
        }
    }
}
