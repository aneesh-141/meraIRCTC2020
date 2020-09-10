package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Stninfo {

    @FXML AnchorPane tnanc;
    @FXML TextField tnsearch;
    @FXML Label stnnm;
    @FXML Label hindi;
    @FXML Label longi;
    @FXML Label lati;
    @FXML Label olddd;

    API api2 = new API();
    DBHelper dbHelper = new DBHelper();

    String email;


    public void logoutstn(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        tnanc.getChildren().setAll(root);
    }

    public void display(String snum, String ee){
        this.olddd.setText(snum);
        email = ee;
    }

    public void prev(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        tnanc.getChildren().setAll(root);

        Profile pfx = loader.getController();
        String name = dbHelper.searchname(email);
        String uid = dbHelper.searchuid(email);

        pfx.display2(name, uid, email);
    }

    public void gostn(MouseEvent mouseEvent) {

        String response  = api2.fetchDataFromApi(("https://indianrailapi.com/api/v2/StationCodeOrName/apikey/02e9a1bdb0ac088c111b564864d99840/SearchText/" + tnsearch.getText() +"/"));
        JSONObject parob = new JSONObject(response.toString());
        JSONArray jsonArray3 = parob.getJSONArray("Station");

        dbHelper.insertstn(tnsearch.getText(), email);

        JSONObject childob = jsonArray3.getJSONObject(0);
        String namestn = (String) childob.get("NameEn");
        stnnm.setText(namestn);

        String hindinm = (String) childob.get("NameHn");
        hindi.setText(hindinm);

        String Lo = (String) childob.get("Longitude");
        longi.setText(Lo);

        String lat = (String) childob.get("Latitude");
        lati.setText(lat);

    }

}
