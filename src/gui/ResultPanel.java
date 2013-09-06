package gui;

import logic.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultPanel extends MyJPanel {

    private WorkWithDB db = new WorkWithDB();

    private JLabel userJLable = new JLabel("User: ");
    private JLabel testJLable = new JLabel("Test: ");
    private JList<User> userJList = new JList<User>();
    private JList<Test> testJList = new JList<Test>();
    private JScrollPane scroller = new JScrollPane();
    private JTextArea testResultJTextPane = new JTextArea(30, 35);

    public ResultPanel() {
        JScrollPane scrollerForQuest = new JScrollPane();
        scrollerForQuest.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollerForQuest.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollerForTest = new JScrollPane();
        scrollerForTest.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollerForTest.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        if (userJList.getPreferredScrollableViewportSize().width > testJList.getPreferredScrollableViewportSize().width) {
            userJList.setFixedCellWidth(userJList.getPreferredScrollableViewportSize().width);
            testJList.setFixedCellWidth(userJList.getPreferredScrollableViewportSize().width);
        } else {
            userJList.setFixedCellWidth(testJList.getPreferredScrollableViewportSize().width);
            testJList.setFixedCellWidth(testJList.getPreferredScrollableViewportSize().width);
        }

        testJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Setting up the gui
        Box testBox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        box1.add(userJLable);
        box1.add(Box.createHorizontalGlue());
        testBox.add(box1);
        testBox.add(Box.createVerticalStrut(6));
        box2.add(userJList);
        box2.add(scrollerForTest);
        box2.add(Box.createHorizontalGlue());
        scrollerForTest.setViewportView(userJList);
        testBox.add(box2);
        testBox.add(Box.createVerticalStrut(12));

        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        box3.add(testJLable);
        box3.add(Box.createHorizontalGlue());
        testBox.add(box3);
        testBox.add(Box.createVerticalStrut(6));
        box4.add(testJList);
        box4.add(scrollerForQuest);
        box4.add(Box.createHorizontalGlue());
        scrollerForQuest.setViewportView(testJList);
        testBox.add(box4);
        testBox.add(Box.createVerticalGlue());

        testBox.setBorder(new EmptyBorder(12, 12, 12, 12));

        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        Box resViewBox = Box.createVerticalBox();
        resViewBox.add(testResultJTextPane);
        resViewBox.add(scroller);
        scroller.setViewportView(testResultJTextPane);
        resViewBox.setBorder(new EmptyBorder(12, 12, 12, 12));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(testBox);
        this.add(resViewBox);

        testJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                fillTestResultJTextPane();
            }
        });

        userJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (db.getUserRole().equals("administrator")) {
                    showTestsForUser(userJList.getSelectedValue().getUserId());
                } else userJList.setEnabled(false);
            }
        });

        fillPanel();
    }

    @Override
    public void showPanel() {
        this.setVisible(true);
        if (db.getUserRole().equals("user")) {
            userJList.setEnabled(false);
            userJList.setVisible(false);
            userJLable.setText("Name: " + db.getUserName());
        } else {
            userJList.setEnabled(true);
        }
    }

    @Override
    public void hidePanel() {
        this.setVisible(false);
    }

    @Override
    public void fillPanel() {
        String selTestForUser = "SELECT * FROM test WHERE user_id = " + db.getUserId();

        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        DefaultListModel<Test> testModel = new DefaultListModel<Test>();
        DefaultListModel<User> userModel = new DefaultListModel<User>();
        ResultSet testResultSet = null;
        ResultSet userResultSet = null;
        try {
            testResultSet = db.executeSelect(selTestForUser);
            while (testResultSet.next()) {
                Test testBuffer = new Test();
                testBuffer.setTestId(testResultSet.getInt("test_id"));
                testBuffer.setTestStatus(testResultSet.getBoolean("test_status"));
                testBuffer.setTestTheme(testResultSet.getString("test_theme"));
                testBuffer.setTestTryNom(testResultSet.getInt("test_try_nom"));
                testModel.addElement(testBuffer);
                System.out.println(testBuffer);
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        db.disconnectDatabase();
        testJList.setModel(testModel);
        selUsers();
    }

    @Override
    public void redrawPanel() {
        userJLable.setText("Name: ");
        testResultJTextPane.setText("");
        testJList.setModel(new DefaultListModel<Test>());
    }

    public void fillTestResultJTextPane() {
        //todo admin should see all users

        String selectFromTextLog = "SELECT * FROM test_log WHERE " +
                "user_id = " + (db.getUserRole().equals("user") ? db.getUserId() : userJList.getSelectedValue().getUserId()) + " AND test_id = " + testJList.getSelectedValue().getTestId();
        System.out.println(selectFromTextLog);
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        ResultSet data;
        try {
            data = db.executeSelect(selectFromTextLog);
            ArrayList<TestLog> logs = new ArrayList<TestLog>();
            TestLog log = new TestLog();
            int i = 0;
            while (data.next()) {
                log = new TestLog();
                log.setTryNumber(data.getInt("test_try_nom"));
                log.setQuestionId(data.getInt("question_id"));
                log.setTestTheme(testJList.getSelectedValue().getTestTheme());
                String selectFromQuestions = "SELECT question_text FROM questions WHERE question_id = " + data.getInt("question_id");
                String selectFromAnswers = "SELECT answer_text, answer_correct FROM answers WHERE answer_id = " + data.getInt("answer_id");
                ResultSet quesAnswData;
                quesAnswData = db.executeSelect(selectFromQuestions);
                if (quesAnswData.next()) {
                    log.setQuestion(quesAnswData.getString("question_text"));
                }
                quesAnswData = db.executeSelect(selectFromAnswers);
                if (quesAnswData.next()) {
                    log.setAnswer(quesAnswData.getString("answer_text"));
                    log.setAnswerCorrect(quesAnswData.getString("answer_correct"));
                }
                logs.add(log);
            }
            String s = "";
            logs.trimToSize();
            for (i = 0; i < logs.size(); i++) {
                s += logs.get(i).toString();
            }
            testResultJTextPane.setText(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnectDatabase();
    }

    public void showTestsForUser(int userId) {
        testResultJTextPane.setText("");
        String selTestForUser = "SELECT * FROM test WHERE user_id = " + userId;
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        DefaultListModel<Test> testModel = new DefaultListModel<Test>();
        DefaultListModel<User> userModel = new DefaultListModel<User>();
        ResultSet testResultSet = null;
        ResultSet userResultSet = null;
        try {
            testResultSet = db.executeSelect(selTestForUser);
            while (testResultSet.next()) {
                Test testBuffer = new Test();
                testBuffer.setTestId(testResultSet.getInt("test_id"));
                testBuffer.setTestStatus(testResultSet.getBoolean("test_status"));
                testBuffer.setTestTheme(testResultSet.getString("test_theme"));
                testBuffer.setTestTryNom(testResultSet.getInt("test_try_nom"));
                testModel.addElement(testBuffer);
                System.out.println(testBuffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.disconnectDatabase();
        testJList.setModel(testModel);
    }

    public void selUsers() {
        String selUsers = "SELECT * FROM sys_user";

        DefaultListModel<User> users = new DefaultListModel<User>();
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        try {
            ResultSet usersResultSet = db.executeSelect(selUsers);
            while (usersResultSet.next()) {
                User userBuffer = new User();
                userBuffer.setUserId(usersResultSet.getInt("user_id"));
                userBuffer.setUserName(usersResultSet.getString("user_name"));
                userBuffer.setUserSurname(usersResultSet.getString("user_surname"));
                users.addElement(userBuffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userJList.setModel(users);
    }
    public void setUserListEnable(boolean enable) {
        userJList.setEnabled(enable);
    }
}
