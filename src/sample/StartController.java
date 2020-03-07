package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class StartController {


    static Class appClass;
    static Connection con;
    static Statement stmt;
    public TextField login;
    public TextField password;
    public Button enter;
    public Button reg;
    public static Stage stage;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:userDB");
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public Label error;

    public void auth(ActionEvent actionEvent) {
        String login = "'" + this.login.getText() + "'";
        String password = "'" + this.password.getText() + "'";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("select * from User where login = " + login +
                    " and password = " + password + ";");
            int cnt = 0;
            User user = null;
            while (rs.next()) {
                user = new User(rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
                cnt++;
            }
            if (user == null) {
                error.setText("Пользователь не существует");
            } else {
                //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                FXMLLoader loader = new FXMLLoader(appClass.getResource("main.fxml"));
                MainController.user = user;
                //controller.init();
                stage.close();
                Stage primaryStage = new Stage();
                primaryStage.setTitle("INFO");
                primaryStage.setScene(new Scene(loader.load(getClass().getResource("main.fxml")), 600, 800));
                MainController controller = loader.getController();
                System.out.println(controller);
                //controller.init();
                primaryStage.show();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    public void regPage(ActionEvent actionEvent) {
        Parent root = null;
        try {
            stage.close();
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
            Controller.stage = primaryStage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
