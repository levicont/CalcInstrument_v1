package calcinstr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import calcinstr.config.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lena
 */
public class CalcInstrument_v1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/fxml/main_frame.fxml"));
        //setUserAgentStylesheet(STYLESHEET_MODENA);
        
        stage.setTitle(R.ApplicationSettings.APP_TITLE);
        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add("resources/css/main.css");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
