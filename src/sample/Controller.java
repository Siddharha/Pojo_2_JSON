package sample;

import com.google.gson.Gson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private AppUtil appUtil;
    private int INP_TYPE;

    @FXML
    TextArea etPojo;

    @FXML
    TextArea etJson;

    @FXML
    Button btnConvert;


    private void onActionPerform() {
        btnConvert.setOnAction(e -> {
            if(INP_TYPE == 0){
               // String s =new Gson().toJson(etPojo.getText());

                try {
                    G obj =(G)appUtil.createDynamicObj(etPojo.getText());

                    etJson.setText(new Gson().toJson(obj));
                } catch (IOException | NoSuchMethodException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }

            }

            if(INP_TYPE ==1){
                Object o =new Gson().fromJson(etJson.getText(), Object.class);
                etPojo.setText(/*etJson.getText()*/o.toString());
            }
        });

        etJson.focusedProperty().addListener((observableValue, aBoolean, t1) -> {

            if (t1){
                INP_TYPE = 1;
            }
        });

        etPojo.focusedProperty().addListener((observableValue, aBoolean, t1) -> {

            if (t1){
                INP_TYPE = 0;
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initView();
        onActionPerform();
    }

    private void initView() {
        INP_TYPE = 0;
        appUtil = new AppUtil();
    }
}
