package util;

import javafx.scene.control.Alert;

public class AlertBuilder {

    public static Alert build(String strType, String title, String header, String msg) {

        Alert.AlertType type = Alert.AlertType.INFORMATION;

        if(strType.equals("error")){
            type = Alert.AlertType.ERROR;
        }

        if(strType.equals("warning")){
            type = Alert.AlertType.WARNING;
        }

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg);

        return alert;

    }

}