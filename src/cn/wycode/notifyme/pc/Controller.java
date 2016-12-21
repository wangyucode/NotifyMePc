package cn.wycode.notifyme.pc;

import cn.wycode.notifyme.pc.bean.Notification;
import cn.wycode.notifyme.pc.bean.Page;
import cn.wycode.notifyme.pc.bean.WyResult;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Controller {

    @FXML
    TextField tf_queryId;
    @FXML
    ProgressIndicator progress;
    @FXML
    Button btn_refresh;
    @FXML
    TableView<Notification> table;
    @FXML
    TableColumn<Notification, String> column_id;
    @FXML
    TableColumn<Notification, String> column_name;
    @FXML
    TableColumn<Notification, String> column_title;
    @FXML
    TableColumn<Notification, String> column_text;
    @FXML
    TableColumn<Notification, String> column_time;

    private static final String url = "http://wycode.cn/api/notification/get?size=20&queryId=";

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
        btn_refresh.setDisable(true);
        new HttpTask(tf_queryId.getText()).run();
    }

    @FXML
    protected void handleWycodeClicked(MouseEvent event){
        URI url = null;
        try {
            url = new URI("http://wycode.cn");
            Desktop.getDesktop().browse(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    class HttpTask extends Task<WyResult<Page>> {

        private String queryId;

        HttpTask(String queryId) {
            this.queryId = queryId;
        }

        @Override
        protected WyResult<Page> call() throws Exception {
            String resultString = HttpUtil.Get(url + queryId).toString();
            WyResult<Page> resultBean;
            resultBean = JsonUtil.toJavaBean(resultString, Page.class);
            return resultBean;
        }


        @Override
        protected void succeeded() {
            super.succeeded();
            WyResult<Page> resultBean = getValue();
            if (resultBean.code != 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, resultBean.message);
                alert.show();
            } else {
                ObservableList<Notification> notifications = new ObservableListWrapper<>(resultBean.data.content);
                table.setItems(notifications);
                column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                column_name.setCellValueFactory(new PropertyValueFactory<>("appName"));
                column_title.setCellValueFactory(new PropertyValueFactory<>("title"));
                column_text.setCellValueFactory(new PropertyValueFactory<>("text"));
                column_time.setCellValueFactory(new PropertyValueFactory<>("when"));
                table.getColumns().setAll(column_id, column_name, column_title, column_text, column_time);
            }
            progress.setVisible(false);
            btn_refresh.setDisable(false);
        }

        @Override
        protected void failed() {
            super.failed();
            progress.setVisible(false);
            btn_refresh.setDisable(false);
        }
    }

}
