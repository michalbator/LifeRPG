package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class Controller {
    private DBConnector DBc;
    private Interface view;
    Controller(){
        DBc = new DBConnector();
        this.view = view;
    }
    public void setView(Interface view){
        this.view = view;
    }
    public void addTask(String name, String points){
        DBc.addTask(name, points);
    }
    private String listOfTasksToString(ArrayList<Task> listOfTasks){
        String result = "";
        for(Task task : listOfTasks){
            result += task.getName() + " | " + task.getPoints() + "\r\n";
        }
        //result.substring(0, result.length()-1);
        return result;
    }
    public String addValues(String name, String points){
        String resultText = "";
        ArrayList<Task> resultTask = new ArrayList<>();
        try {
            if (!(name.equals(""))){
                resultTask.addAll(DBc.getTaskByName(name));
            }
            else if(!(points.equals(""))){
                resultTask.addAll(DBc.getTaskByPoints(Integer.parseInt(points)));
            }
            else{
                throw new SQLException();
            }
            resultText = listOfTasksToString(resultTask);
            return resultText;
        }
        catch(SQLException e){
            return "Error";
        }
    }
    public ObservableList<String> allTasks(){
        ArrayList<Task> listOfTasks = null;
        try{
            listOfTasks = DBc.getAllTask();
        }
        catch(SQLException e){
            return FXCollections.observableArrayList(Arrays.asList("Error", e.getMessage()));
        }
        ObservableList<String> resultList = FXCollections.observableArrayList();
        for(Task task : listOfTasks){
            resultList.add(task.getName() + " | " + task.getPoints());
        }
        return resultList;
    }

}