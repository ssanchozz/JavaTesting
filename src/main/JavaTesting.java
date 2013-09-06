package main;

import gui.Gui;
import logic.WorkWithDB;

public class JavaTesting {
    public static void main (String[] args) {
        Gui gui = new Gui();
        //createDb cDb = new createDb();
        //cDb.insertData();
    }
}

class createDb {
    private WorkWithDB db = new WorkWithDB();
    createDb() {
        db.createTables();
    }

    public void insertData() {
        String insertUser = "INSERT INTO sys_user(user_name, user_surname, user_password, user_role) " +
                "VALUES ('ssanchozz', 'Bogdanov', '123', 'administrator')";
        String insertRoles1 = "INSERT INTO roles (role_name) VALUES ('user')";
        String insertRoles2 = "INSERT INTO roles (role_name) VALUES ('administrator')";
        db.connectDatabase(WorkWithDB.CONNECT_STRING);
        db.executeInsertStatement(insertUser);
        db.executeInsertStatement(insertRoles1);
        db.executeInsertStatement(insertRoles2);
        db.disconnectDatabase();
    }

}
