package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String args[]){
        launch(args);
    }
    public void start(Stage primaryStage){
        Controller controller = new Controller();
        Interface view = new Interface(primaryStage, controller);
        view.start();
    }
}