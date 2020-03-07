package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.sql.*;

public class MainController {

    static Connection con;
    static Statement stmt;
    static String login, password;
    static User user;

    public void init() {
        name.setText(name.getText() + "   " + user.name);
        surname.setText(surname.getText() + "   " + user.surname);
        fathername.setText(fathername.getText() + "   " + user.fatherName);
        birthdate.setText(birthdate.getText() + "   " + user.birthDate);
    }

    public Label surname;
    public Label name;
    public Label fathername;
    public Label birthdate;

    public void load(ActionEvent actionEvent) {
        name.setText("Имя: " + user.name);
        surname.setText("Фамилия: " + user.surname);
        fathername.setText("Отчество: " + user.fatherName);
        birthdate.setText("Дата рождения: " + user.birthDate);
    }
}
