package logic;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class WorkWithDB {

    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "administrator";
    public static final String USER = "ssanchozz";
    public static final String PASSWORD = "grenad";
    public static final String CONNECT_STRING = "jdbc:derby:TestDB;create=true";

    private Connection conn = null;
    private static int userId = 1;
    private static String userName = "";
    private static String userRole = "";

    private ArrayList<Test> testDataStructure = new ArrayList<Test>();

    public void connectDatabase(String connectString) {

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        try {
            Class.forName(driver);
            System.out.println(driver + " loaded. ");
        } catch(java.lang.ClassNotFoundException e)     {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
        }

        try {
            System.out.println("Getting connection with this connect string: " + connectString);
            conn = DriverManager.getConnection(connectString);
        } catch (SQLException e) {
            System.err.print("SQLException: ");
            System.err.println(e.getMessage());
            System.out.println("\n    >>> Please check your connectString   <<<\n");
        }
        System.out.println("Connected to database ");
    }

    public void disconnectDatabase() {
        try {
            conn.commit();
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeInsertStatement(String statement) {
        Statement s;
        try {
            s = conn.createStatement();
            s.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeCreateStatement(String statement) {
        Statement s;
        try {
            s = conn.createStatement();
            s.execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeSelect (String selectStatement) throws SQLException {
        Statement s = conn.createStatement();
        s.execute(selectStatement);
        ResultSet data;
        data = s.getResultSet();
        System.out.println("Selected!");
        return data;
    }

    public boolean login(String name, String password) {
        try {
            connectDatabase(CONNECT_STRING);
            ResultSet data = executeSelect("SELECT * FROM sys_user " +
                    "WHERE user_name = '" + name + "'");
            if (data.next()) {
                if (data.getString("user_password").equals(password)) {
                    setUserId(data.getInt("user_id"));
                    setUserName(data.getString("user_name"));
                    setUserRole(data.getString("user_role"));
                    System.out.println("Hello " + name + "!");
                    disconnectDatabase();
                    return true;
                } else {
                    disconnectDatabase();
                    System.out.println("Disconnected from DB, check password");
                    return false;
                }
            } else {
                disconnectDatabase();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public ArrayList<Test> getTestDataStructure() {
        return testDataStructure;
    }

    public void createTables() {
        WorkWithDB db = new WorkWithDB();
        String createUser = "CREATE TABLE sys_user\n" +
                "(\n" +
                "user_id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,\n" +
                " INCREMENT BY 1),\n" +
                "user_name VARCHAR(24) NOT NULL,\n" +
                "user_surname VARCHAR(26),\n" +
                "user_password VARCHAR(24) NOT NULL,\n" +
                "user_role VARCHAR(20) NOT NULL\n" +
                ")";
        String createTest = "CREATE TABLE test\n" +
                "(\n" +
                "test_id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,\n" +
                " INCREMENT BY 1),\n" +
                "test_theme VARCHAR(200) NOT NULL,\n" +
                "test_status VARCHAR(5),\n" +
                "test_try_nom INTEGER,\n" +
                "user_id INTEGER NOT NULL CONSTRAINT test_user_id_fk REFERENCES sys_user\n" +
                ")";
        String createQuestion = "CREATE TABLE questions\n" +
                "(\n" +
                "question_id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,\n" +
                " INCREMENT BY 1),\n" +
                "question_text VARCHAR(1000) NOT NULL,\n" +
                "test_id INTEGER NOT NULL CONSTRAINT question_test_id_fk REFERENCES test\n" +
                ")";
        String createAnswer = "CREATE TABLE answers\n" +
                "(\n" +
                "answer_id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,\n" +
                " INCREMENT BY 1),\n" +
                "answer_text VARCHAR(1000) NOT NULL,\n" +
                "answer_correct VARCHAR(5) NOT NULL,\n" +
                "question_id INTEGER NOT NULL CONSTRAINT answers_question_id_fk REFERENCES questions\n" +
                ")";
        String createLog = "CREATE TABLE test_log\n" +
                "(\n" +
                "test_log_id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,\n" +
                " INCREMENT BY 1),\n" +
                "test_try_nom INTEGER, " +
                "user_id INTEGER NOT NULL CONSTRAINT test_log_user_id_fk REFERENCES sys_user,\n" +
                "test_id INTEGER NOT NULL CONSTRAINT test_log_test_id_fk REFERENCES test,\n" +
                "question_id INTEGER NOT NULL CONSTRAINT test_log_question_id_fk REFERENCES questions,\n" +
                "answer_id INTEGER NOT NULL CONSTRAINT test_log_answer_id_fk REFERENCES answers\n" +
                ")";
        String createRoles = "CREATE TABLE roles " +
                "(role_id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), " +
                "role_name VARCHAR(20))";
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        db.executeCreateStatement(createUser);
        db.executeCreateStatement(createTest);
        db.executeCreateStatement(createQuestion);
        db.executeCreateStatement(createAnswer);
        db.executeCreateStatement(createLog);
        db.executeCreateStatement(createRoles);
        db.disconnectDatabase();
    }
}
