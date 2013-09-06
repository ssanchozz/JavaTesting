package gui;

import logic.Answer;
import logic.Question;
import logic.Test;
import logic.WorkWithDB;
import main.JavaTesting;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Gui {
    public static final int FRAME_HEIGHT = 400;
    public static final int FRAME_WIDTH = 800;
    public static final int PANEL_HEIGHT = 400;
    public static final int PANEL_WIDTH = 800;

    private WorkWithDB db = new WorkWithDB();

 //   private static JFrame testFrame = new JFrame();
    private LoginWindow  loginWindow = new LoginWindow(Gui.this);

    public Gui() {
        showLoginWindow();
    }

    public void showLoginWindow() {
        loginWindow.setVisible(true);
    }

    public void hideLoginWindow() {
        loginWindow.setVisible(false);
    }
}

class LoginWindow extends JFrame {
    JTextField loginField;
    JPasswordField passwordField;
    private JFrame testFrame;
    private WorkWithDB db = new WorkWithDB();
    private Gui gui;
    public Menu menu = new Menu();
    // panels
    private MyJPanel resultPanel;
    private MyJPanel testPanel;
    private MyJPanel controlPanel;

    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem exitMenuItem = new JMenuItem("Exit");

    LoginWindow(Gui gui){
        super("Authentication");
        this.gui = gui;
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Box box1 = Box.createHorizontalBox();
        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField(15);
        box1.add(loginLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(loginField);

        Box box2 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        box2.add(passwordLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(passwordField);

        Box box3 = Box.createHorizontalBox();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(cancel);

        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(box3);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        this.setLocationRelativeTo(null);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (login(loginField.getText(), passwordField.getText())) {
                    menuBar.add(exitMenuItem);
                    exitMenuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            hideTestFrame();
                            loginField.setText("");
                            passwordField.setText("");
                            menu.removeAll();
                        }
                    });
                    testFrame = new JFrame();
                    testFrame.setJMenuBar(menuBar);
                    testFrame.setSize(Gui.FRAME_WIDTH, Gui.FRAME_HEIGHT);
                    testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    resultPanel = new ResultPanel();
                    testPanel = new TestPanel();
                    controlPanel = new ControlPanel();
                    if (db.getUserRole().equals("administrator")){
                        menu.setControlPanel(controlPanel);
                    }
                    else
                        menu.setControlPanel(null);
                    menu.setResultPanel(resultPanel);
                    menu.setTestPanel(testPanel);
                    menu.addPanels();
                    testFrame.getContentPane().add(menu);
                    testFrame.pack();
                    testFrame.setLocationRelativeTo(null);
                    testFrame.setResizable(false);
                    showTestFrame();
                } else {
                    loginField.setText("check log/pas");
                    passwordField.setText("");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    public boolean login(String name, String password) {
        try {
            db.connectDatabase(WorkWithDB.CONNECT_STRING);
            Properties p = System.getProperties();
            p.setProperty("derby.system.home", "C:\\databases");
            ResultSet data = db.executeSelect("SELECT * FROM sys_user " +
                    "WHERE user_name = '" + name + "'");
            if (data.next()) {
                if (data.getString("user_password").equals(password)) {
                    db.setUserId(data.getInt("user_id"));
                    db.setUserName(data.getString("user_name"));
                    db.setUserRole(data.getString("user_role"));
                    System.out.println("Hello " + name + "!");
                    db.disconnectDatabase();
                    return true;
                } else {
                    db.disconnectDatabase();
                    System.out.println("Disconnected from DB, check password");
                    return false;
                }
            } else {
                db.disconnectDatabase();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showTestFrame() {
        gui.hideLoginWindow();
        testFrame.setVisible(true);
    }

    public void hideTestFrame() {
        gui.showLoginWindow();
        testFrame.setVisible(false);
    }
}
