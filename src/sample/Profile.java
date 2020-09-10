package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Profile {

    @FXML AnchorPane profanc;
    @FXML Label nam;
    @FXML Label em;
    @FXML Label ud;

    String emm;
    DBHelper dbHelper = new DBHelper();

    public void display(String name, String u, String email){
        this.nam.setText(name);
        this.ud.setText(u);
        this.em.setText(email);
        emm = email;
    }

    public void display2(String name, String u, String email){
        this.nam.setText(name);
        this.ud.setText(u);
        this.em.setText(email);
        emm = email;
    }


    public void logout(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        profanc.getChildren().setAll(root);
    }

    public void trainz(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("traininfo.fxml"));
        Parent root = loader.load();
        profanc.getChildren().setAll(root);
        Traininfo tr = loader.getController();
        String train_no = dbHelper.searchtno(emm);
        tr.display(train_no, emm);
    }

    public void stnn(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stninfo.fxml"));
        Parent root = loader.load();
        profanc.getChildren().setAll(root);
        Stninfo st = loader.getController();
        String trainnm = dbHelper.searchstn(emm);
        st.display(trainnm, emm);
    }
}
