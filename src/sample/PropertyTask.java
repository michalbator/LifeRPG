package sample;

import javafx.beans.property.SimpleStringProperty;

public class PropertyTask {
    private final SimpleStringProperty name;
    private final SimpleStringProperty points;
    PropertyTask(Task task){
        this.name = new SimpleStringProperty(task.getName());
        this.points = new SimpleStringProperty(Integer.toString(task.getPoints()));
    }
    SimpleStringProperty getName(){
        return this.name;
    }
    SimpleStringProperty getPoints(){
        return this.points;
    }
}
