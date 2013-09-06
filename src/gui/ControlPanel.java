package gui;

import logic.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlPanel extends MyJPanel {

    WorkWithDB db = new WorkWithDB();
    // userBox elements
    JLabel userJLable = new JLabel("User:");
    JList<User> userJList = new JList<User>();
    JButton userAddJButton = new JButton("Add");
    JButton userUpDateJButton = new JButton("Update");
    JButton userDelJButton = new JButton("Delete");

    JLabel userNameJLable = new JLabel("Name:");
    JTextField userNameJTextField = new JTextField(20);
    JLabel userSurnameJLable = new JLabel("Surname:");
    JTextField userSurnameJTextField = new JTextField(20);
    JLabel userPasswordJLable = new JLabel("Password:");
    JPasswordField passwordJPassField = new JPasswordField(20);
    JLabel userRoleJLable = new JLabel("Role:");
    JComboBox<String> userRoleJComboBox = new  JComboBox<String>();

    // testBox elements
    JLabel testJLable = new JLabel("Test:");
    JList<Test> testJList = new JList<Test>();

    JLabel testThemeJLable = new JLabel("Theme:");
    JTextField testThemeJTextField = new JTextField(20);

    JButton testAddJButton = new JButton("Add");
    JButton testUpDateJButton = new JButton("Update");
    JButton testDelJButton = new JButton("Delete");

    // questionBox elements
    JLabel questJLable = new JLabel("Question:");
    JList<Question> questJList = new JList<Question>();

    JLabel questTextJLable = new JLabel("Text:");
    JTextField questJTextField = new JTextField(20);

    JLabel answJLable = new JLabel("Answers");
    JLabel corrJLable = new JLabel("Correct");

    JLabel firstAnswJLable = new JLabel("1.)");
    JTextField firstAnswJTextField = new JTextField(20);
    JCheckBox firstAnswJCheckBox = new JCheckBox();
    JLabel secAnswJLable = new JLabel("2.)");
    JTextField secAnswJTextField = new JTextField(20);
    JCheckBox secAnswJCheckBox = new JCheckBox();
    JLabel thirdAnswJLable = new JLabel("3.)");
    JTextField thirdAnswJTextField = new JTextField(20);
    JCheckBox thirdAnswJCheckBox = new JCheckBox();
    JLabel fourthAnswJLable = new JLabel("4.)");
    JTextField fourthAnswJTextField = new JTextField(20);
    JCheckBox fourthAnswJCheckBox = new JCheckBox();

    JButton questAddJButton = new JButton("Add");
    JButton questUpDateJButton = new JButton("Update");
    JButton questDelJButton = new JButton("Delete");


    public ControlPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // userBox elements add
        Box userBox = Box.createVerticalBox();
        userBox.setBorder(new EmptyBorder(12, 12, 12, 12));

        JScrollPane userScroller = new JScrollPane();
        userScroller.setHorizontalScrollBarPolicy(userScroller.HORIZONTAL_SCROLLBAR_ALWAYS);
        userScroller.setVerticalScrollBarPolicy(userScroller.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane testScroller = new JScrollPane();
        testScroller.setHorizontalScrollBarPolicy(testScroller.HORIZONTAL_SCROLLBAR_ALWAYS);
        testScroller.setVerticalScrollBarPolicy(testScroller.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane questScroller = new JScrollPane();
        questScroller.setHorizontalScrollBarPolicy(questScroller.HORIZONTAL_SCROLLBAR_ALWAYS);
        questScroller.setVerticalScrollBarPolicy(questScroller.VERTICAL_SCROLLBAR_ALWAYS);

        userBox.add(userJLable);
        userBox.add(Box.createVerticalStrut(6));
        Box userScroll = Box.createVerticalBox();
        userJList.setFixedCellWidth(150);
        userJList.setVisibleRowCount(3);
        userJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userScroll.add(userJList);
        userScroll.add(userScroller);
        userScroller.setViewportView(userJList);
        userBox.add(userScroll);
        userBox.add(Box.createVerticalStrut(12));
        userDelJButton.setMinimumSize(new Dimension(75, 25));
        userDelJButton.setPreferredSize(new Dimension(75, 25));
        userDelJButton.setMaximumSize(new Dimension(75, 25));
        userBox.add(userDelJButton);
        userBox.add(Box.createVerticalStrut(12));
        userBox.add(userNameJLable);
        userBox.add(Box.createVerticalStrut(6));
        // 1
        userNameJTextField.setMaximumSize(new Dimension(100, 20));
        userNameJLable.setPreferredSize(new Dimension(65, 20));
        userNameJLable.setMinimumSize(new Dimension(65, 20));
        Box userNameBox= Box.createHorizontalBox();
        userNameBox.add(userNameJLable);
        userNameBox.add(Box.createHorizontalStrut(6));
        userNameBox.add(userNameJTextField);
        userNameBox.add(Box.createHorizontalGlue());
        userBox.add(userNameBox);
        userBox.add(Box.createVerticalStrut(12));
        // 2
        userSurnameJTextField.setMaximumSize(new Dimension(100, 20));
        userSurnameJLable.setPreferredSize(new Dimension(65, 20));
        userSurnameJLable.setMinimumSize(new Dimension(65, 20));
        Box userSurnameBox = Box.createHorizontalBox();
        userSurnameBox.add(userSurnameJLable);
        userSurnameBox.add(Box.createHorizontalStrut(6));
        userSurnameBox.add(userSurnameJTextField);
        userSurnameBox.add(Box.createHorizontalGlue());
        userBox.add(userSurnameBox);
        userBox.add(Box.createVerticalStrut(12));
        // 3
        passwordJPassField.setMaximumSize(new Dimension(100, 20));
        userPasswordJLable.setPreferredSize(new Dimension(65, 20));
        userPasswordJLable.setMinimumSize(new Dimension(65, 20));
        Box passBox = Box.createHorizontalBox();
        passBox.add(userPasswordJLable);
        passBox.add(Box.createHorizontalStrut(6));
        passBox.add(passwordJPassField);
        passBox.add(Box.createHorizontalGlue());
        userBox.add(passBox);
        userBox.add(Box.createVerticalStrut(12));
        // 4
        userRoleJComboBox.setMaximumSize(new Dimension(100, 20));
        userRoleJLable.setPreferredSize(new Dimension(65, 20));
        userRoleJLable.setMinimumSize(new Dimension(65, 20));
        Box roleBox = Box.createHorizontalBox();
        roleBox.add(userRoleJLable);
        roleBox.add(Box.createHorizontalStrut(6));
        roleBox.add(userRoleJComboBox);
        roleBox.add(Box.createHorizontalGlue());
        userBox.add(roleBox);
        userBox.add(Box.createVerticalStrut(12));

        userAddJButton.setMinimumSize(new Dimension(75, 25));
        userAddJButton.setPreferredSize(new Dimension(75, 25));
        userAddJButton.setMaximumSize(new Dimension(75, 25));
        userUpDateJButton.setMinimumSize(new Dimension(75, 25));
        userUpDateJButton.setPreferredSize(new Dimension(75, 25));
        userUpDateJButton.setMaximumSize(new Dimension(75, 25));
        userBox.add(userAddJButton);
        userBox.add(Box.createVerticalStrut(12));
        userBox.add(userUpDateJButton);
        userBox.add(Box.createVerticalGlue());

        // testBox elements add
        Box testBox = Box.createVerticalBox();
        testBox.setBorder(new EmptyBorder(12, 12, 12, 12));

        testBox.add(testJLable);
        testBox.add(Box.createVerticalStrut(6));
        testJList.setFixedCellWidth(150);
        testJList.setVisibleRowCount(3);
        testJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Box testScroll = Box.createVerticalBox();
        testScroll.add(testJList);
        testScroll.add(testScroller);
        testScroller.setViewportView(testJList);
        testBox.add(testScroll);
        testBox.add(Box.createVerticalStrut(12));
        testDelJButton.setMinimumSize(new Dimension(75, 25));
        testDelJButton.setPreferredSize(new Dimension(75, 25));
        testDelJButton.setMaximumSize(new Dimension(75, 25));
        testBox.add(testDelJButton);
        testBox.add(Box.createVerticalStrut(12));
        // Test Theme Field
        testThemeJTextField.setMaximumSize(new Dimension(100, 20));
        testThemeJLable.setPreferredSize(new Dimension(65, 20));
        testThemeJLable.setMinimumSize(new Dimension(65, 20));
        Box testThemeBox = Box.createHorizontalBox();
        testThemeBox.add(testThemeJLable);
        testThemeBox.add(Box.createHorizontalStrut(6));
        testThemeBox.add(testThemeJTextField);
        testThemeBox.add(Box.createHorizontalGlue());
        testBox.add(testThemeBox);
        // Test buttons
        testBox.add(Box.createVerticalStrut(12));
        testAddJButton.setMinimumSize(new Dimension(75, 25));
        testAddJButton.setPreferredSize(new Dimension(75, 25));
        testAddJButton.setMaximumSize(new Dimension(75, 25));
        testUpDateJButton.setMinimumSize(new Dimension(75, 25));
        testUpDateJButton.setPreferredSize(new Dimension(75, 25));
        testUpDateJButton.setMaximumSize(new Dimension(75, 25));
        testBox.add(testAddJButton);
        testBox.add(Box.createVerticalStrut(12));
        testBox.add(testUpDateJButton);
        testBox.add(Box.createVerticalGlue());

        // questionBox elements add
        Box questBox = Box.createVerticalBox();
        questBox.setBorder(new EmptyBorder(12, 12, 12, 12));

        Box questScroll = Box.createVerticalBox();
        questJList.setFixedCellWidth(150);
        questJList.setVisibleRowCount(3);
        questJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questScroll.add(questJLable);
        questScroll.add(Box.createVerticalStrut(6));
        questScroll.add(questJList);
        questScroll.add(questScroller);
        questScroller.setViewportView(questJList);
        questBox.add(questScroll);

        questBox.add(Box.createVerticalStrut(12));
        questDelJButton.setMinimumSize(new Dimension(75, 25));
        questDelJButton.setPreferredSize(new Dimension(75, 25));
        questDelJButton.setMaximumSize(new Dimension(75, 25));
        questBox.add(questDelJButton);
        questBox.add(Box.createVerticalStrut(12));

        questJTextField.setMaximumSize(new Dimension(405, 20));
        questTextJLable.setPreferredSize(new Dimension(65, 20));
        questTextJLable.setMinimumSize(new Dimension(20, 20));
        Box questTextBox = Box.createVerticalBox();

        Box horQuestTextBox = Box.createHorizontalBox();
        questTextBox.add(questTextJLable);
        questTextBox.add(Box.createVerticalStrut(6));
        questTextBox.add(questJTextField);
        questTextBox.add(Box.createVerticalGlue());
        horQuestTextBox.add(Box.createHorizontalStrut(26));
        horQuestTextBox.add(questTextBox);
        horQuestTextBox.add(Box.createHorizontalGlue());

        questBox.add(horQuestTextBox);

        questBox.add(Box.createVerticalStrut(12));
        Box answersHeader = Box.createHorizontalBox();
        answersHeader.add(Box.createHorizontalGlue());
        answersHeader.add(answJLable);
        answersHeader.add(Box.createHorizontalStrut(70));
        answersHeader.add(corrJLable);

        Box firstAnswBox = Box.createHorizontalBox();
        firstAnswJTextField.setMaximumSize(new Dimension(100, 20));
        firstAnswJLable.setPreferredSize(new Dimension(20, 20));
        firstAnswJLable.setMinimumSize(new Dimension(20, 20));
        firstAnswBox.add(firstAnswJLable);
        firstAnswBox.add(Box.createHorizontalStrut(6));
        firstAnswBox.add(firstAnswJTextField);
        firstAnswBox.add(Box.createHorizontalStrut(6));
        firstAnswBox.add(firstAnswJCheckBox);
        firstAnswBox.add(Box.createHorizontalGlue());

        Box secondAnswBox = Box.createHorizontalBox();
        secAnswJTextField.setMaximumSize(new Dimension(100, 20));
        secAnswJLable.setPreferredSize(new Dimension(20, 20));
        secAnswJLable.setMinimumSize(new Dimension(20, 20));
        secondAnswBox.add(secAnswJLable);
        secondAnswBox.add(Box.createHorizontalStrut(6));
        secondAnswBox.add(secAnswJTextField);
        secondAnswBox.add(Box.createHorizontalStrut(6));
        secondAnswBox.add(secAnswJCheckBox);
        secondAnswBox.add(Box.createHorizontalGlue());

        Box thirdAnswBox = Box.createHorizontalBox();
        thirdAnswJTextField.setMaximumSize(new Dimension(100, 20));
        thirdAnswJLable.setPreferredSize(new Dimension(20, 20));
        thirdAnswJLable.setMinimumSize(new Dimension(20, 20));
        thirdAnswBox.add(thirdAnswJLable);
        thirdAnswBox.add(Box.createHorizontalStrut(6));
        thirdAnswBox.add(thirdAnswJTextField);
        thirdAnswBox.add(Box.createHorizontalStrut(6));
        thirdAnswBox.add(thirdAnswJCheckBox);
        thirdAnswBox.add(Box.createHorizontalGlue());

        Box fourthAnswBox = Box.createHorizontalBox();
        fourthAnswJTextField.setMaximumSize(new Dimension(100, 20));
        fourthAnswJLable.setPreferredSize(new Dimension(20, 20));
        fourthAnswJLable.setMinimumSize(new Dimension(20, 20));
        fourthAnswBox.add(fourthAnswJLable);
        fourthAnswBox.add(Box.createHorizontalStrut(6));
        fourthAnswBox.add(fourthAnswJTextField);
        fourthAnswBox.add(Box.createHorizontalStrut(6));
        fourthAnswBox.add(fourthAnswJCheckBox);
        fourthAnswBox.add(Box.createHorizontalGlue());

        questBox.add(answersHeader);
        questBox.add(Box.createVerticalStrut(6));
        questBox.add(firstAnswBox);
        questBox.add(Box.createVerticalStrut(12));
        questBox.add(secondAnswBox);
        questBox.add(Box.createVerticalStrut(12));
        questBox.add(thirdAnswBox);
        questBox.add(Box.createVerticalStrut(12));
        questBox.add(fourthAnswBox);

        questBox.add(Box.createVerticalStrut(12));
        questAddJButton.setMinimumSize(new Dimension(75, 25));
        questAddJButton.setPreferredSize(new Dimension(75, 25));
        questAddJButton.setMaximumSize(new Dimension(75, 25));
        questUpDateJButton.setMinimumSize(new Dimension(75, 25));
        questUpDateJButton.setPreferredSize(new Dimension(75, 25));
        questUpDateJButton.setMaximumSize(new Dimension(75, 25));
        questBox.add(questAddJButton);
        questBox.add(Box.createVerticalStrut(12));
        questBox.add(questUpDateJButton);
        questBox.add(Box.createVerticalGlue());

        // Add processors for User

        userJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                userNameJTextField.setText(userJList.getSelectedValue().getUserName());
                userSurnameJTextField.setText(userJList.getSelectedValue().getUserSurname());
                passwordJPassField.setText(userJList.getSelectedValue().getUserPassword());
                userRoleJComboBox.setSelectedItem(userJList.getSelectedValue().getUserRole());
                showTestForUser();
            }
        });

        userAddJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String insertStatement = "INSERT INTO sys_user (user_name, user_surname, user_password, user_role) VALUES " +
                        "('" + userNameJTextField.getText() + "', " +
                        "'" + userSurnameJTextField.getText() + "', " +
                        "'" + passwordJPassField.getText() + "', " +
                        "'" + userRoleJComboBox.getSelectedItem() + "')";
                db.connectDatabase(WorkWithDB.CONNECT_STRING);
                db.executeInsertStatement(insertStatement);
                db.disconnectDatabase();
                selUsers();
            }
        });

        userDelJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = userJList.getSelectedValue().getUserId();
                if (userId == db.getUserId())
                    showWarning("You can't delete yourself!");
                else {
                    String deleteQuest = "DELETE FROM questions WHERE test_id IN " +
                            "(SELECT test_id FROM test WHERE user_id = " + userJList.getSelectedValue().getUserId() + ")";
                    String deleteAnsw = "DELETE FROM answers WHERE question_id IN " +
                            "(SELECT question_id FROM questions WHERE test_id IN " +
                            "(SELECT test_id FROM test WHERE user_id = " + userJList.getSelectedValue().getUserId() + "))";
                    deleteQuestions(deleteQuest, deleteAnsw);
                    String deleteTests = "DELETE FROM test WHERE user_id = " + userJList.getSelectedValue().getUserId();
                    db.connectDatabase(WorkWithDB.CONNECT_STRING);
                    db.executeInsertStatement(deleteTests);
                    db.disconnectDatabase();
                    String deleteUser = "DELETE FROM sys_user WHERE user_id = " + userJList.getSelectedValue().getUserId();
                    db.connectDatabase(WorkWithDB.CONNECT_STRING);
                    db.executeInsertStatement(deleteUser);
                    db.disconnectDatabase();
                    selUsers();
                }
            }
        });

        userUpDateJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String update = "UPDATE sys_user " +
                        "SET user_name = '" + userNameJTextField.getText() + "'," +
                        "user_surname = '" + userSurnameJTextField.getText() + "'," +
                        "user_password = '" + passwordJPassField.getText() + "'," +
                        "user_role = '" + userRoleJComboBox.getSelectedItem() + "'" +
                        "WHERE user_id = " + userJList.getSelectedValue().getUserId();
                db.connectDatabase(WorkWithDB.CONNECT_STRING);
                db.executeInsertStatement(update);
                db.disconnectDatabase();
                selUsers();
            }
        });

        // Add processors for Test

        testJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                testThemeJTextField.setText(testJList.getSelectedValue().getTestTheme());
                showQuestForTest();
            }
        });

        testAddJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String testInsertStatement = "INSERT INTO test (test_theme, user_id, test_status) VALUES ('" + testThemeJTextField.getText()
                        + "', " + userJList.getSelectedValue().getUserId() + ", false )";
                db.connectDatabase(WorkWithDB.CONNECT_STRING);
                db.executeInsertStatement(testInsertStatement);
                db.disconnectDatabase();
                testThemeJTextField.setText("");
                showTestForUser();
            }
        });

        testDelJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deleteQuest = "DELETE FROM questions WHERE test_id = " + testJList.getSelectedValue().getTestId();
                String deleteAnsw = "DELETE FROM answers WHERE question_id IN " +
                        "(SELECT question_id FROM questions WHERE test_id = " + testJList.getSelectedValue().getTestId() + ")";
                deleteQuestions(deleteQuest, deleteAnsw);
                String delete = "DELETE FROM test WHERE test_id = " + testJList.getSelectedValue().getTestId();
                db.connectDatabase(WorkWithDB.CONNECT_STRING);
                db.executeInsertStatement(delete);
                db.disconnectDatabase();
                showTestForUser();
            }
        });

        testUpDateJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String update = "UPDATE test " +
                        "SET test_theme = '" + testThemeJTextField.getText() + "'" +
                        "WHERE test_theme = '" + testJList.getSelectedValue().getTestTheme() + "' AND " +
                        "user_id = " + userJList.getSelectedValue().getUserId();
                db.connectDatabase(WorkWithDB.CONNECT_STRING);
                db.executeInsertStatement(update);
                db.disconnectDatabase();
                showTestForUser();
            }
        });

        // Add processors for Questions
        questJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                showAnswerForQuest();
            }
        });

        questAddJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int questionId = 0;
                String firstCorr, secondCorr, thirdCorr, fourthCorr;
                if (firstAnswJCheckBox.isSelected())
                    firstCorr = "right";
                else firstCorr = "wrong";
                if (secAnswJCheckBox.isSelected())
                    secondCorr = "right";
                else secondCorr = "wrong";
                if (thirdAnswJCheckBox.isSelected())
                    thirdCorr = "right";
                else thirdCorr = "wrong";
                if (fourthAnswJCheckBox.isSelected())
                    fourthCorr = "right";
                else fourthCorr = "wrong";

                if (firstCorr.equals("wrong")&&secondCorr.equals("wrong")&&thirdCorr.equals("wrong")&&fourthCorr.equals("wrong"))
                    showWarning("You should choose at least one correct answer");
                else {
                    String questInsertStatement = "INSERT INTO questions (question_text, test_id) VALUES ('" + questJTextField.getText()
                            + "', " + testJList.getSelectedValue().getTestId() + " )";

                    String selectInsertedQuestId = "SELECT question_id FROM questions WHERE " +
                            "question_text = '" + questJTextField.getText() + "' AND " +
                            "test_id = " + testJList.getSelectedValue().getTestId();
                    db.connectDatabase(WorkWithDB.CONNECT_STRING);
                    db.executeInsertStatement(questInsertStatement);

                    try {
                        ResultSet rs = db.executeSelect(selectInsertedQuestId);
                        while (rs.next()) {
                            questionId = rs.getInt("question_id");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    String answInsertStatement1 = "INSERT INTO answers (answer_text, answer_correct, question_id) VALUES (" +
                            "'" + firstAnswJTextField.getText() + "','" + firstCorr + "'," + questionId + ")";
                    String answInsertStatement2 = "INSERT INTO answers (answer_text, answer_correct, question_id) VALUES (" +
                            "'" + secAnswJTextField.getText() + "','" + secondCorr + "'," + questionId + ")";
                    String answInsertStatement3 = "INSERT INTO answers (answer_text, answer_correct, question_id) VALUES (" +
                            "'" + thirdAnswJTextField.getText() + "','" + thirdCorr + "'," + questionId + ")";
                    String answInsertStatement4 = "INSERT INTO answers (answer_text, answer_correct, question_id) VALUES (" +
                            "'" + fourthAnswJTextField.getText() + "','" + fourthCorr + "'," + questionId + ")";
                    db.executeInsertStatement(answInsertStatement1);
                    db.executeInsertStatement(answInsertStatement2);
                    db.executeInsertStatement(answInsertStatement3);
                    db.executeInsertStatement(answInsertStatement4);
                    db.disconnectDatabase();

                    firstAnswJTextField.setText("");
                    secAnswJTextField.setText("");
                    thirdAnswJTextField.setText("");
                    fourthAnswJTextField.setText("");
                    firstAnswJCheckBox.setSelected(false);
                    secAnswJCheckBox.setSelected(false);
                    thirdAnswJCheckBox.setSelected(false);
                    fourthAnswJCheckBox.setSelected(false);

                    questJTextField.setText("");
                    showQuestForTest();
                }
            }
        });

        questDelJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deleteQuest = "DELETE FROM questions WHERE question_id = " + questJList.getSelectedValue().getQuestionId();
                String deleteAnsw = "DELETE FROM answers WHERE question_id = " + questJList.getSelectedValue().getQuestionId();
                deleteQuestions(deleteQuest, deleteAnsw);
                showQuestForTest();
            }
        });

        questUpDateJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String corr = "";
                String updateQuest = "UPDATE questions " +
                        "SET question_text = '" + questJTextField.getText() + "' " +
                        "WHERE question_id = " + questJList.getSelectedValue().getQuestionId();
                if (firstAnswJCheckBox.isSelected())
                    corr = "right";
                else
                    corr = "wrong";
                String updateFirstAnswer = "UPDATE answers " +
                        "SET answer_text = '" + firstAnswJTextField.getText() + "', " +
                        "answer_correct = '" + corr + "' " +
                        "WHERE answer_id = " + questJList.getSelectedValue().getAnswers()[0].getAnswerId();
                if (secAnswJCheckBox.isSelected())
                    corr = "right";
                else
                    corr = "wrong";
                String updateSecondAnswer = "UPDATE answers " +
                        "SET answer_text = '" + secAnswJTextField.getText() + "', " +
                        "answer_correct = '" + corr + "' " +
                        "WHERE answer_id = " + questJList.getSelectedValue().getAnswers()[1].getAnswerId();
                if (thirdAnswJCheckBox.isSelected())
                    corr = "right";
                else
                    corr = "wrong";
                String updateThirdAnswer = "UPDATE answers " +
                        "SET answer_text = '" + thirdAnswJTextField.getText() + "', " +
                        "answer_correct = '" + corr + "' " +
                        "WHERE answer_id = " + questJList.getSelectedValue().getAnswers()[2].getAnswerId();
                if (fourthAnswJCheckBox.isSelected())
                    corr = "right";
                else
                    corr = "wrong";
                String updateFourthAnswer = "UPDATE answers " +
                        "SET answer_text = '" + fourthAnswJTextField.getText() + "', " +
                        "answer_correct = '" + corr + "' " +
                        "WHERE answer_id = " + questJList.getSelectedValue().getAnswers()[3].getAnswerId();
                db.connectDatabase(WorkWithDB.CONNECT_STRING);
                db.executeInsertStatement(updateQuest);
                db.executeInsertStatement(updateFirstAnswer);
                db.executeInsertStatement(updateSecondAnswer);
                db.executeInsertStatement(updateThirdAnswer);
                db.executeInsertStatement(updateFourthAnswer);
                db.disconnectDatabase();
                showQuestForTest();
            }
        });

        firstAnswJCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secAnswJCheckBox.isSelected() || thirdAnswJCheckBox.isSelected() || fourthAnswJCheckBox.isSelected()) {
                    firstAnswJCheckBox.setSelected(false);
                }
            }
        });
        secAnswJCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstAnswJCheckBox.isSelected() || thirdAnswJCheckBox.isSelected() || fourthAnswJCheckBox.isSelected()) {
                    secAnswJCheckBox.setSelected(false);
                }
            }
        });
        thirdAnswJCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstAnswJCheckBox.isSelected() || secAnswJCheckBox.isSelected() || fourthAnswJCheckBox.isSelected()) {
                    thirdAnswJCheckBox.setSelected(false);
                }
            }
        });
        fourthAnswJCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstAnswJCheckBox.isSelected() || secAnswJCheckBox.isSelected() || thirdAnswJCheckBox.isSelected()) {
                    fourthAnswJCheckBox.setSelected(false);
                }
            }
        });

        selUsers();
        selRoles();
        this.add(userBox);
        this.add(testBox);
        this.add(questBox);
        userJList.setSelectedIndex(0);
    }

    @Override
    public void showPanel() {
        this.setVisible(true);
    }

    @Override
    public void hidePanel() {
        this.setVisible(false);
    }

    @Override
    public void fillPanel() {

    }

    @Override
    public void redrawPanel() {

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
                userBuffer.setUserPassword(usersResultSet.getString("user_password"));
                userBuffer.setUserRole(usersResultSet.getString("user_role"));
                users.addElement(userBuffer);
                System.out.println(userBuffer.getUserName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userJList.setModel(users);
    }

    public void selRoles() {
        String selRoles = "SELECT * FROM roles";
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        try {
            ResultSet rolesSet = db.executeSelect(selRoles);
            while (rolesSet.next()) {
                comboBoxModel.addElement(rolesSet.getString("role_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnectDatabase();
        userRoleJComboBox.setModel(comboBoxModel);
    }

    public void showTestForUser() {
        String selTestForUser = "SELECT * FROM test WHERE user_id = " + userJList.getSelectedValue().getUserId();

        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        DefaultListModel<Test> testModel = new DefaultListModel<Test>();
        ResultSet testResultSet = null;
        try {
            testResultSet = db.executeSelect(selTestForUser);
            while (testResultSet.next()) {
                Test testBuffer = new Test();
                testBuffer.setTestId(testResultSet.getInt("test_id"));
                testBuffer.setTestStatus(testResultSet.getBoolean("test_status"));
                testBuffer.setTestTheme(testResultSet.getString("test_theme"));
                testBuffer.setTestTryNom(testResultSet.getInt("test_try_nom"));
                System.out.println(testBuffer);
                testModel.addElement(testBuffer);
            }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        testJList.setModel(testModel);
        testJList.setSelectedIndex(0);
    }

    public void showQuestForTest() {
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        DefaultListModel<Question> quesModel = new DefaultListModel<Question>();
        String selQuestForTest = "SELECT * FROM questions WHERE test_id = " + testJList.getSelectedValue().getTestId();
        ResultSet quesResultSet = null;
        try {
            quesResultSet = db.executeSelect(selQuestForTest);

            while (quesResultSet.next()) {
                Question quesBuffer = new Question();
                quesBuffer.setQuestionId(quesResultSet.getInt("question_id"));
                quesBuffer.setQuestionText(quesResultSet.getString("question_text"));
                System.out.println(quesBuffer);
                ResultSet answResultSet = null;
                String selAnswerForQuest = "SELECT * FROM answers WHERE question_id = " + quesBuffer.getQuestionId();
                answResultSet = db.executeSelect(selAnswerForQuest);
                Answer[] answers = new Answer[4];
                int i = 0;
                while (answResultSet.next()) {
                    answers[i] = new Answer();
                    Answer answBuffer = new Answer();
                    answBuffer.setAnswerId(answResultSet.getInt("answer_id"));
                    answBuffer.setAnswerText(answResultSet.getString("answer_text"));
                    answBuffer.setCorrectAnswer(answResultSet.getString("answer_correct"));
                    System.out.println(answBuffer);
                    answers[i] = answBuffer;
                    i++;
                }
                quesBuffer.setAnswers(answers);
                quesModel.addElement(quesBuffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnectDatabase();
        questJList.setModel(quesModel);
    }

    public void showAnswerForQuest() {
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        ResultSet answResultSet = null;
        String selAnswerForQuest = "SELECT * FROM answers WHERE question_id = " + questJList.getSelectedValue().getQuestionId();
        Answer[] answBuffer = new Answer[4];
        try {
            answResultSet = db.executeSelect(selAnswerForQuest);
            int i = 0;
            while (answResultSet.next() && i < 4) {
                answBuffer[i] = new Answer();
                answBuffer[i].setAnswerId(answResultSet.getInt("answer_id"));
                answBuffer[i].setAnswerText(answResultSet.getString("answer_text"));
                answBuffer[i].setCorrectAnswer(answResultSet.getString("answer_correct"));
                System.out.println(answBuffer[i]);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnectDatabase();
        for (int i=0; i<4; i++) {
            switch(i) {
                default: break;
                case 0: firstAnswJTextField.setText(answBuffer[i].getAnswerText());
                        firstAnswJCheckBox.setSelected(answBuffer[i].getCorrectAnswer().equals("right"));
                        break;
                case 1: secAnswJTextField.setText(answBuffer[i].getAnswerText());
                        secAnswJCheckBox.setSelected(answBuffer[i].getCorrectAnswer().equals("right"));
                        break;
                case 2: thirdAnswJTextField.setText(answBuffer[i].getAnswerText());
                        thirdAnswJCheckBox.setSelected(answBuffer[i].getCorrectAnswer().equals("right"));
                        break;
                case 3: fourthAnswJTextField.setText(answBuffer[i].getAnswerText());
                        fourthAnswJCheckBox.setSelected(answBuffer[i].getCorrectAnswer().equals("right"));
                        break;
            }
        }
        questJTextField.setText(questJList.getSelectedValue().getQuestionText());
    }

    public void deleteQuestions(String questDelString, String answDelString) {
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        db.executeInsertStatement(answDelString);
        db.executeInsertStatement(questDelString);
        db.disconnectDatabase();
        firstAnswJCheckBox.setSelected(false);
        secAnswJCheckBox.setSelected(false);
        thirdAnswJCheckBox.setSelected(false);
        fourthAnswJCheckBox.setSelected(false);
        firstAnswJTextField.setText("");
        secAnswJTextField.setText("");
        thirdAnswJTextField.setText("");
        fourthAnswJTextField.setText("");
        questJTextField.setText("");

    }

    public void showWarning(String warning) {
        JOptionPane.showMessageDialog(this, warning, "Warning", JOptionPane.DEFAULT_OPTION);
    }
}
