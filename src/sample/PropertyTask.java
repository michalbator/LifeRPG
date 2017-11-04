package sample;

import javafx.beans.property.SimpleStringProperty;

public class PropertyTask {
    private final SimpleStringProperty name;
    private final SimpleStringProperty points;
    private final SimpleStringProperty id;
    PropertyTask(Task task){
        this.name = new SimpleStringProperty(task.getName());
        this.points = new SimpleStringProperty(Integer.toString(task.getPoints()));
        this.id = new SimpleStringProperty(Integer.toString(task.getId()));
    }
    SimpleStringProperty getName(){
        return this.name;
    }
    SimpleStringProperty getPoints(){
        return this.points;
    }
    SimpleStringProperty getId(){
        return this.id;
    }
}
