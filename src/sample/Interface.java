package sample;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Interface {
    private Stage primaryStage;
    private Controller controller;
    private TextField nameTF;
    private TextField pointsTF;
    private TextArea resultTF;
    private TableView<PropertyTask> table;

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
        addButton.setOnAction(event -> {
            String name = nameTF.getText();
            String points = pointsTF.getText();
            controller.addTask(name, points);
            resultTF.setText("'" + name + "' added");
            nameTF.clear();
            pointsTF.clear();
            table.refresh();
        });
        Button selectButton = new Button("Show");
        selectButton.setOnAction(event -> {
            String name = nameTF.getText();
            String points = pointsTF.getText();
            String result = controller.addValues(name, points);
            resultTF.setText(result);
            nameTF.clear();
            pointsTF.clear();
        });
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(event -> {
            nameTF.clear();
            pointsTF.clear();
            resultTF.clear();
        });
        TextField nameEdit = new TextField();
        TextField resultEdit = new TextField();
        Button addResultButton = new Button("Add");
        addResultButton.setOnAction(event ->{
            int id = Integer.parseInt(table.getSelectionModel().getSelectedItem().getId().get());
            String name = table.getSelectionModel().getSelectedItem().getName().get();
            String result = resultEdit.getText();
            System.out.println(Integer.toString(id) + "\t" + name + "\t" + result);
        });
        table = new TableView<>();
        TableColumn<PropertyTask, String> taskColumn = new TableColumn<>("Task");
        TableColumn<PropertyTask, String> resultColumn = new TableColumn<>("Result");
        TableColumn<PropertyTask, String> idColumn = new TableColumn<>("ID");
        table.getColumns().addAll(taskColumn, resultColumn, idColumn);;
        ObservableList<PropertyTask> listOfPropertyTask = controller.allPropertyTask();
        taskColumn.setCellValueFactory(data -> data.getValue().getName());
        resultColumn.setCellValueFactory(data -> data.getValue().getPoints());
        idColumn.setCellValueFactory(data -> data.getValue().getId());
        table.setItems(listOfPropertyTask);
        table.setOnMouseClicked(event -> {
            nameEdit.setText(table.getSelectionModel().getSelectedItem().getName().get());
            resultEdit.clear();
        });
        VBox vBox = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        HBox hbox4 = new HBox();
        hbox1.getChildren().addAll(new Label("name"), nameTF);
        hbox2.getChildren().addAll(new Label("points"), pointsTF);
        hbox3.getChildren().addAll(addButton, selectButton, clearButton);
        hbox4.getChildren().addAll(nameEdit, resultEdit, addResultButton);
        vBox.getChildren().addAll(hbox1, hbox2, hbox3, resultTF, table, hbox4);
        Scene scene = new Scene(vBox, 400, 500);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
}
