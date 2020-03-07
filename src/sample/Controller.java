package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller {

    static Connection con;
    static Statement stmt;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:userDB");
            stmt = con.createStatement();
            stmt.execute("create table if not exists User\n" +
                    "(\n" +
                    "    id         INTEGER not null\n" +
                    "        constraint User_pk\n" +
                    "            primary key autoincrement,\n" +
                    "    surname    TEXT,\n" +
                    "    name       TEXT,\n" +
                    "    login       TEXT,\n" +
                    "    password       TEXT,\n" +
                    "    fathername TEXT,\n" +
                    "    birthdate  TEXT\n" +
                    ");");
            ResultSet rs = stmt.executeQuery("select * from User;");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public Button submit;
    public TextField fathername;
    public TextField name;
    public TextField surname;
    public DatePicker birthDate;
    public TextField password;
    public TextField login;
    public static Stage stage;

    public void submit(ActionEvent actionEvent) {
        try {
            String name = "'" + this.name.getText() + "'";
            String login = "'" + this.login.getText() + "'";
            String password = "'" + this.password.getText() + "'";
            String surname = "'" + this.surname.getText() + "'";
            String fathername = "'" + this.fathername.getText() + "'";
            String date = "'"  + birthDate.getEditor().getText() + "'" ;
            stmt.execute("INSERT INTO User(surname, name, login, password, fathername, birthdate) values ( " + surname + ", " + name + ", " + login + ", " + password + ", "+ fathername + ", " + date + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Parent root = null;
        stage.close();
        try {
            root = FXMLLoader.load(getClass().getResource("start.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
