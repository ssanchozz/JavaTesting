package logic;

import java.util.ArrayList;

public class Test {

    private String testTheme;
    private int testId;
    private Boolean testStatus;
    private int testTryNom;
    private ArrayList<Question> questions = new ArrayList<Question>();

    public String getTestTheme() {
        return testTheme;
    }

    public void setTestTheme(String testTheme) {
        this.testTheme = testTheme;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public Boolean getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(Boolean testStatus) {
        this.testStatus = testStatus;
    }

    public int getTestTryNom() {
        return testTryNom;
    }

    public void setTestTryNom(int testTryNom) {
        this.testTryNom = testTryNom;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String toString() {
        return testTheme;
    }

/*public void userGreeting() {
        String name = "";
        String surname = "";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert your name: ");
        name = scanner.nextLine();
        System.out.print("Insert your surname: ");
        surname = scanner.nextLine();

        String selectString = "SELECT t.test_id, t.test_theme, t.test_status, t.test_try_nom" +
                " FROM test t, sys_user su " +
                "WHERE t.user_id = (SELECT user_id FROM sys_user WHERE user_name = '" + name + "' AND " + "user_surname = '" + surname + "')";

        WorkWithDB db = new WorkWithDB();
        db.connectDatabase(Main.CONNECT_STRING);
        //  ResultSet data = db.executeSelect(selectString);
        ResultSet data = null;
        try {
            data = db.executeSelect(selectString);
            System.out.println("Theme of your avaliable tests is:");
            while (data.next()) {
                String testTheme = data.getString("test_theme");
                System.out.println(testTheme + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Hello" + Main.USER);
        System.out.println("Hello" + Main.USER);
    }

    void selectTests() {

    }*/



}
