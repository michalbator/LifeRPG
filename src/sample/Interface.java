package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class Interface {
    private Stage primaryStage;
    private Controller controller;
    private TextField nameTF;
    private TextField pointsTF;
    private TextArea resultTF;
    private ListView listOfTasks;
    Interface(Stage primaryStage, Controller controller){
        this.controller = controller;
        this.primaryStage = primaryStage;
        nameTF = new TextField();
        resultTF = new TextArea();
        pointsTF = new TextField();
    }
    public void start(){
        resultTF.setText("No result");
        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameTF.getText();
                String points = pointsTF.getText();
                controller.addTask(name, points);
                resultTF.setText("'" + name + "' added");
                nameTF.clear();
                pointsTF.clear();
            }
        });
        Button selectButton = new Button("Show");
        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameTF.getText();
                String points = pointsTF.getText();
                String result = controller.addValues(name, points);
                resultTF.setText(result);
                nameTF.clear();
                pointsTF.clear();
            }
        });
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nameTF.clear();
                pointsTF.clear();
                resultTF.clear();
            }
        });
        listOfTasks = new ListView<String>();
        listOfTasks.setItems(FXCollections.observableArrayList(controller.allTasks()));
        VBox vBox = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        hbox1.getChildren().addAll(new Label("name"), nameTF);
        hbox2.getChildren().addAll(new Label("points"), pointsTF);
        hbox3.getChildren().addAll(addButton, selectButton, clearButton);
        vBox.getChildren().addAll(hbox1, hbox2, hbox3, resultTF, listOfTasks);
        Scene scene = new Scene(vBox, 400, 500);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
}
