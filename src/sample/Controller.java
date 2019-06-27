package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.tools.JavaCompiler;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
            etJson.setText(etPojo.getText());
            }

            if(INP_TYPE ==1){
                etPojo.setText(etJson.getText());
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

    }
}
