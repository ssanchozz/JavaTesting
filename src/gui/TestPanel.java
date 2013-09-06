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
import java.util.ArrayList;

public class TestPanel extends MyJPanel {
    private JLabel testJLable = new JLabel("Test theme:");
    private JLabel questionJLable = new JLabel("Question:");
    private JList<Test> testNamesJList = new JList<Test>();
    private JList<Question> questionNamesJList = new JList<Question>();
    private JPanel questionJPanel = new JPanel();
    private JLabel questionNumberJLable = new JLabel();
    private JLabel questionTextJLable = new JLabel();
    private ButtonGroup answersRadioButtons = new ButtonGroup();
    private JRadioButtonMenuItem firstAnswer = new JRadioButtonMenuItem();
    private JRadioButtonMenuItem secondAnswer = new JRadioButtonMenuItem();
    private JRadioButtonMenuItem thirdAnswer = new JRadioButtonMenuItem();
    private JRadioButtonMenuItem fourthAnswer = new JRadioButtonMenuItem();
    private Color backGroundColor = firstAnswer.getBackground();

    private int[] answersLogArray;
    private WorkWithDB db = new WorkWithDB();
    private boolean testIsOver = false;
    private boolean testSuccess = true;

    public TestPanel() {
        JScrollPane scrollerForQuest = new JScrollPane();
        scrollerForQuest.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollerForQuest.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollerForTest = new JScrollPane();
        scrollerForTest.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollerForTest.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        //testNamesJList.setFixedCellWidth(150);
        //questionNamesJList.setFixedCellWidth(150);
        //testNamesJList.getPreferredScrollableViewportSize();
        //questionNamesJList.getPreferredScrollableViewportSize();
        if (testNamesJList.getPreferredScrollableViewportSize().width > questionNamesJList.getPreferredScrollableViewportSize().width) {
            testNamesJList.setFixedCellWidth(testNamesJList.getPreferredScrollableViewportSize().width);
            questionNamesJList.setFixedCellWidth(testNamesJList.getPreferredScrollableViewportSize().width);
        } else {
            testNamesJList.setFixedCellWidth(questionNamesJList.getPreferredScrollableViewportSize().width);
            questionNamesJList.setFixedCellWidth(questionNamesJList.getPreferredScrollableViewportSize().width);
        }
        testNamesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testNamesJList.setFixedCellWidth(150);
        Box testBox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        box1.add(testJLable);
        box1.add(Box.createHorizontalGlue());
        testBox.add(box1);
        testBox.add(Box.createVerticalStrut(6));
        box2.add(testNamesJList);
        box2.add(scrollerForTest);
        box2.add(Box.createHorizontalGlue());
        scrollerForTest.setViewportView(testNamesJList);
        testBox.add(box2);
        testBox.add(Box.createVerticalStrut(12));

        questionNamesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionNamesJList.setFixedCellWidth(150);
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        box3.add(questionJLable);
        box3.add(Box.createHorizontalGlue());
        testBox.add(box3);
        testBox.add(Box.createVerticalStrut(6));
        box4.add(questionNamesJList);
        box4.add(scrollerForQuest);
        box4.add(Box.createHorizontalGlue());
        scrollerForQuest.setViewportView(questionNamesJList);
        testBox.add(box4);
        testBox.add(Box.createVerticalGlue());

        testBox.setBorder(new EmptyBorder(12, 12, 12, 12));

        answersRadioButtons.add(firstAnswer);
        answersRadioButtons.add(secondAnswer);
        answersRadioButtons.add(thirdAnswer);
        answersRadioButtons.add(fourthAnswer);

        Box questionBox = Box.createVerticalBox();
        Box qBox1 = Box.createHorizontalBox();
        qBox1.add(questionTextJLable);
        qBox1.add(Box.createHorizontalGlue());
        Box aBox1 = Box.createHorizontalBox();
        Box aBox2 = Box.createHorizontalBox();
        Box aBox3 = Box.createHorizontalBox();
        Box aBox4 = Box.createHorizontalBox();
        aBox1.add(firstAnswer);
        aBox1.add(Box.createHorizontalGlue());
        aBox2.add(secondAnswer);
        aBox2.add(Box.createHorizontalGlue());
        aBox3.add(thirdAnswer);
        aBox3.add(Box.createHorizontalGlue());
        aBox4.add(fourthAnswer);
        aBox4.add(Box.createHorizontalGlue());

        questionBox.add(qBox1);
        questionBox.add(Box.createVerticalStrut(10));
        questionBox.add(aBox1);
        questionBox.add(Box.createVerticalStrut(5));
        questionBox.add(aBox2);
        questionBox.add(Box.createVerticalStrut(5));
        questionBox.add(aBox3);
        questionBox.add(Box.createVerticalStrut(5));
        questionBox.add(aBox4);
        questionBox.add(Box.createVerticalStrut(5));
        questionBox.add(Box.createVerticalGlue());

        questionBox.setBorder(new EmptyBorder(12, 12, 12, 12));
      //  this.add(questionJPanel);

       /* questionJPanel.setMinimumSize(new Dimension(500, 300));
        questionJPanel.setLayout(new BoxLayout(questionJPanel, BoxLayout.Y_AXIS));
        questionJPanel.add(questionNumberJLable);
        questionJPanel.add(questionTextJLable);
        questionJPanel.add(firstAnswer);
        questionJPanel.add(secondAnswer);
        questionJPanel.add(thirdAnswer);
        questionJPanel.add(fourthAnswer);*/

        this.setSize(Gui.PANEL_WIDTH, Gui.PANEL_HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(testBox);
        this.add(questionBox);

        testNamesJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                showQuestForTest();
                answersLogArray = new int[questionNamesJList.getModel().getSize()];
            }
        });

        questionNamesJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                showAnswerForQuest();
            }
        });
        firstAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You are in firstAnswer.addActionListener(new ActionListener()");
                answersRadioButtons.clearSelection();
                questionNamesJList.getSelectedValue().setAnswered(true);
                questionNamesJList.getSelectedValue().setChosenAnswer(saveIdOfAnswerText(firstAnswer.getText()));
                if (!questionNamesJList.getSelectedValue().getAnswers()[0].getCorrectAnswer().equals("right")) testSuccess = false;
                goToNextQuestion();
            }
        });
        secondAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You are in secondAnswer.addActionListener(new ActionListener()");
                answersRadioButtons.clearSelection();
                questionNamesJList.getSelectedValue().setAnswered(true);
                questionNamesJList.getSelectedValue().setChosenAnswer(saveIdOfAnswerText(secondAnswer.getText()));
                if (!questionNamesJList.getSelectedValue().getAnswers()[1].getCorrectAnswer().equals("right")) testSuccess = false;
                goToNextQuestion();
            }
        });
        thirdAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You are in thirdAnswer.addActionListener(new ActionListener()");
                answersRadioButtons.clearSelection();
                questionNamesJList.getSelectedValue().setAnswered(true);
                questionNamesJList.getSelectedValue().setChosenAnswer(saveIdOfAnswerText(thirdAnswer.getText()));
                if (!questionNamesJList.getSelectedValue().getAnswers()[2].getCorrectAnswer().equals("right")) testSuccess = false;
                goToNextQuestion();
            }
        });
        fourthAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You are in fourthAnswer.addActionListener(new ActionListener()");
                answersRadioButtons.clearSelection();
                questionNamesJList.getSelectedValue().setAnswered(true);
                questionNamesJList.getSelectedValue().setChosenAnswer(saveIdOfAnswerText(fourthAnswer.getText()));
                if (!questionNamesJList.getSelectedValue().getAnswers()[3].getCorrectAnswer().equals("right")) testSuccess = false;
                goToNextQuestion();
            }
        });
        fillPanel();
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
        String selTestForUser = "SELECT * FROM test WHERE user_id = " + db.getUserId();

        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        DefaultListModel<Test> testModel = new DefaultListModel<Test>();
        DefaultListModel<Question> quesModel = new DefaultListModel<Question>();
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
                String selQuestForTest = "SELECT * FROM questions WHERE test_id = " + testBuffer.getTestId();
                ResultSet quesResultSet = null;
                quesResultSet = db.executeSelect(selQuestForTest);
                while (quesResultSet.next()) {
                    Question quesBuffer = new Question();
                    quesBuffer.setQuestionId(quesResultSet.getInt("question_id"));
                    quesBuffer.setQuestionText(quesResultSet.getString("question_text"));
                    System.out.println(quesBuffer);
                    ResultSet answResultSet = null;
                    String selAnswerForQuest = "SELECT * FROM answers WHERE question_id = " + quesBuffer.getQuestionId();
                    answResultSet = db.executeSelect(selAnswerForQuest);
                    Answer[] answersBuffArr = new Answer[4];
                    int i = 0;
                    while (answResultSet.next()) {
                        Answer answBuffer = new Answer();
                        answBuffer.setAnswerId(answResultSet.getInt("answer_id"));
                        answBuffer.setAnswerText(answResultSet.getString("answer_text"));
                        answBuffer.setCorrectAnswer(answResultSet.getString("answer_correct"));
                        answersBuffArr[i] = answBuffer;
                        System.out.println(answBuffer);
                        i++;
                    }
                    quesBuffer.setAnswers(answersBuffArr);
                    quesModel.addElement(quesBuffer);
                }
                testModel.addElement(testBuffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnectDatabase();

        testNamesJList.setModel(testModel);
        questionNamesJList.setModel(quesModel);
        testNamesJList.setSelectedIndex(0);
        questionNamesJList.setSelectedIndex(0);
    }

    public void showQuestForTest() {
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        DefaultListModel<Question> quesModel = new DefaultListModel<Question>();
        String selQuestForTest = "SELECT * FROM questions WHERE test_id = " + testNamesJList.getSelectedValue().getTestId();
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
            Answer[] answersBuffArr = new Answer[4];
            int i = 0;
            while (answResultSet.next()) {
                Answer answBuffer = new Answer();
                answBuffer.setAnswerId(answResultSet.getInt("answer_id"));
                answBuffer.setAnswerText(answResultSet.getString("answer_text"));
                answBuffer.setCorrectAnswer(answResultSet.getString("answer_correct"));
                answersBuffArr[i] = answBuffer;
                System.out.println(answBuffer);
                i++;
            }
            quesBuffer.setAnswers(answersBuffArr);
            quesModel.addElement(quesBuffer);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnectDatabase();
        questionNamesJList.setModel(quesModel);
        questionNamesJList.setSelectedIndex(0);
    }

    public void showAnswerForQuest() {
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        ResultSet answResultSet = null;
        String selAnswerForQuest = "SELECT * FROM answers WHERE question_id = " + questionNamesJList.getSelectedValue().getQuestionId();
        try {
            answResultSet = db.executeSelect(selAnswerForQuest);
        int i = 0;
        while (answResultSet.next()) {
            Answer answBuffer = new Answer();
            answBuffer.setAnswerId(answResultSet.getInt("answer_id"));
            answBuffer.setAnswerText(answResultSet.getString("answer_text"));
            answBuffer.setCorrectAnswer(answResultSet.getString("answer_correct"));
            switch (i) {
                case 0: firstAnswer.setText(answResultSet.getString("answer_text")); break;
                case 1: secondAnswer.setText(answResultSet.getString("answer_text")); break;
                case 2: thirdAnswer.setText(answResultSet.getString("answer_text")); break;
                case 3: fourthAnswer.setText(answResultSet.getString("answer_text")); break;
                default: break;
            }
            i++;
            System.out.println(answBuffer);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        questionTextJLable.setText("Question №" + (questionNamesJList.getSelectedIndex() + 1) + "\n" +
                questionNamesJList.getSelectedValue().getQuestionText());

        //todo
        if (questionNamesJList.getSelectedValue().isAnswered()) {
            firstAnswer.setEnabled(false);
            secondAnswer.setEnabled(false);
            thirdAnswer.setEnabled(false);
            fourthAnswer.setEnabled(false);
        } else {
            firstAnswer.setEnabled(true);
            secondAnswer.setEnabled(true);
            thirdAnswer.setEnabled(true);
            fourthAnswer.setEnabled(true);
            firstAnswer.setBackground(backGroundColor);
            secondAnswer.setBackground(backGroundColor);
            thirdAnswer.setBackground(backGroundColor);
            fourthAnswer.setBackground(backGroundColor);
        }
        db.disconnectDatabase();
    }

    @Override
    public void redrawPanel() {

    }

    public Answer saveIdOfAnswerText(String answText) {
        Answer chosenAnswer = new Answer();
        String selAnswer = "SELECT * FROM answers WHERE answer_text = '" + answText + "' AND question_id = " +
                                                                questionNamesJList.getSelectedValue().getQuestionId();
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        try {
            ResultSet answerResultSet = db.executeSelect(selAnswer);
            if (answerResultSet.next()){
                answersLogArray[questionNamesJList.getSelectedIndex()] = answerResultSet.getInt("answer_id");
                chosenAnswer.setAnswerText(answerResultSet.getString("answer_text"));
                chosenAnswer.setAnswerId(answerResultSet.getInt("answer_id"));
                chosenAnswer.setCorrectAnswer(answerResultSet.getString("answer_correct"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chosenAnswer;
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(this, "Test is over! See your results in the Result tab", "Congratulations", JOptionPane.DEFAULT_OPTION);
    }

    public void goToNextQuestion() {
        if (questionNamesJList.getSelectedIndex() + 2 <= questionNamesJList.getModel().getSize() &&
                !questionNamesJList.getModel().getElementAt(questionNamesJList.getSelectedIndex() + 1).isAnswered())

            questionNamesJList.setSelectedIndex(questionNamesJList.getSelectedIndex() + 1);
        else {
            for (int i = 0; i < answersLogArray.length; i++) {
                if (answersLogArray[i] == 0) {
                    questionNamesJList.setSelectedIndex(i);
                    testIsOver = false;
                    break;
                } else {
                    testIsOver = true;
                }
            }
            if (testIsOver) {
                System.out.println("Test is over!");
                updateTestStatus();
                saveCurrentWayToDb(answersLogArray);
                showMessage();
              //  testNamesJList.setSelectedIndex(0);
                firstAnswer.setEnabled(false);
                secondAnswer.setEnabled(false);
                thirdAnswer.setEnabled(false);
                fourthAnswer.setEnabled(false);
                firstAnswer.setSelected(false);
                secondAnswer.setSelected(false);
                thirdAnswer.setSelected(false);
                fourthAnswer.setSelected(false);
                showQuestForTest();
                showAnswerForQuest();
            }
        }
    }

    public void saveCurrentWayToDb(int[] answersId) {
        int test_id = 0;
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        for (int i = 0; i < answersId.length; i++) {
            String selectLogDataStr = "SELECT test_id, question_id FROM questions " +
                    "WHERE question_id = (SELECT question_id FROM answers WHERE answer_id = " + answersId[i] + ")";
            ResultSet logDataSet = null;
            int question_id = 0;
            try {
                logDataSet = db.executeSelect(selectLogDataStr);
                while (logDataSet.next()) {
                    test_id = logDataSet.getInt("test_id");
                    question_id = logDataSet.getInt("question_id");
                    String logInsertStr = "INSERT INTO test_log (user_id, test_id, question_id, answer_id, test_try_nom) VALUES " +
                            "(" + db.getUserId() + ", " + test_id + ", " + question_id + ", " + answersId[i] + ", " + testNamesJList.getSelectedValue().getTestTryNom() + ")";
                    db.executeInsertStatement(logInsertStr);
                    System.out.println("Data inserted!");
                }
                ResultSet rs = db.executeSelect("SELECT * FROM test_log");
                while (rs.next()) {
                    System.out.print(rs.getInt("test_log_id") + " ");
                }
                System.out.println("-----------------------------");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            answersId[i] = 0;
        }
        db.disconnectDatabase();
    }

    void updateTestStatus() {
        testNamesJList.getSelectedValue().setTestTryNom(testNamesJList.getSelectedValue().getTestTryNom() + 1);
        String updateTryNom = "UPDATE test SET " +
                "test_try_nom = " + testNamesJList.getSelectedValue().getTestTryNom() +
                "WHERE test_id = " + testNamesJList.getSelectedValue().getTestId();
        String updateTestStatus = "";
        if (testSuccess) {
            updateTestStatus = "UPDATE test SET " +
                    "test_status = " + testSuccess + " " +
                    "WHERE test_id = " + testNamesJList.getSelectedValue().getTestId();
        }   else testSuccess = true;

        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        db.executeInsertStatement(updateTryNom);
        if (!updateTestStatus.equals("")) {
            db.executeInsertStatement(updateTestStatus);
        }
        db.disconnectDatabase();
    }

}
