package cn.wycode.notifyme.pc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    TextField tf_queryId;
    @FXML
    ProgressIndicator progress;

    @FXML
    protected void handleRefreshPressed(ActionEvent event) {
        Alert alert;
        try {
            if (tf_queryId.getText().length() < 4 || Integer.parseInt(tf_queryId.getText()) < 1000) {
                throw new NumberFormatException("查询码小于4位");
            }
        } catch (NumberFormatException e) {
            alert = new Alert(Alert.AlertType.WARNING, "请输入正确的查询码!");
            alert.show();
            tf_queryId.setText("");
            return;
        }
        System.out.println("handleRefreshPressed = " + tf_queryId.getText());
        progress.setVisible(true);
        Button refresh = (Button) event.getSource();
        refresh.setDisable(true);

    }

}