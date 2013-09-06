package gui;

import logic.WorkWithDB;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JTabbedPane {
    private JPanel testPanel;
    private JPanel resultPanel;
    private JPanel controlPanel;

    public Menu() {
        super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
    }

    public void showMenu() {
        this.setVisible(true);
    }

    public void hideMenu() {
        this.setVisible(false);
    }

    public JPanel getTestPanel() {
        return testPanel;
    }

    public void setTestPanel(JPanel testPanel) {
        this.testPanel = testPanel;
    }

    public JPanel getResultPanel() {
        return this.resultPanel;
    }

    public void setResultPanel(JPanel resultPanel) {
        this.resultPanel = resultPanel;
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(JPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void addPanels() {
        this.addTab("Test", testPanel);
        this.addTab("Result", resultPanel);
        this.addTab("Control", controlPanel);
    }

    public void hideControlPanel() {
        this.getControlPanel().setVisible(false);
    }
    public void showControlPanel() {
        this.getControlPanel().setVisible(true);
    }
}

