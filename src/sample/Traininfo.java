package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;

import java.io.IOException;

public class Traininfo {

    @FXML AnchorPane anct;
    @FXML TextField tno;
    @FXML
    Label name;
    @FXML Label src;
    @FXML Label dest;
    @FXML Label oldd;

    API api = new API();
    DBHelper dbHelper = new DBHelper();

    String email;

    public void logout(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        anct.getChildren().setAll(root);
    }


    public void display(String tnum, String ee){
        this.oldd.setText(tnum);
        email = ee;
    }

    public void prev(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        anct.getChildren().setAll(root);

        Profile pfx = loader.getController();
        String name = dbHelper.searchname(email);
        String uid = dbHelper.searchuid(email);

        pfx.display2(name, uid, email);
    }


    public void go(MouseEvent mouseEvent) {
        String response = api.fetchDataFromApi("https://indianrailapi.com/api/v2/TrainInformation/apikey/02e9a1bdb0ac088c111b564864d99840/TrainNumber/"+tno.getText()+"/");
        JSONObject parentobj = new JSONObject(response.toString());
        dbHelper.insert(tno.getText(), email);

        String nn = (String) parentobj.get("TrainName");
        name.setText(nn);
        String start = (String) parentobj.getJSONObject("Source").get("Code");
        src.setText(start);
        String des = (String) parentobj.getJSONObject("Destination").get("Code");
        dest.setText(des);
    }
}
