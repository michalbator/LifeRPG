package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnector {
    private Connection connection;
    DBConnector(){
        String url = "jdbc:sqlite:C:/Users/Michal/Downloads/DB/test.db";
        try{
            this.connection = DriverManager.getConnection(url);
        }
        catch(SQLException e){
            e.getMessage();
        }
    }
    public ArrayList<Task> getTaskByPoints(int points) throws SQLException{
        String sql = "SELECT * FROM tasks WHERE points = " + Integer.toString(points) + ";";
        return getListOfTasks(sql);
    }
    public Task getTaskByID(int id) throws SQLException{
        String sql = "SELECT * FROM tasks WHERE id = " + Integer.toString(id) + ";";
        ArrayList<Task> taskAsList = getListOfTasks(sql);
        return taskAsList.get(0);
    }
    private ArrayList<Task> getListOfTasks(String sql){
        ResultSet resultSet;
        Statement statement;
        ArrayList<Task> result = new ArrayList<>();
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nm = resultSet.getString("name");
                int pt = resultSet.getInt("points");
                result.add(new Task(nm, pt, id));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    ArrayList<Task>  getTaskByName(String name) throws SQLException{
        String sql = "SELECT * FROM tasks WHERE name = '" + name + "';";
        return getListOfTasks(sql);
    }
    public void addTask(String name, String points){
        String sql = "INSERT INTO tasks (name, points) VALUES ('" + name + "', " + points + ");";
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    ArrayList<Task> getAllTask() throws  SQLException{
        String sql = "SELECT * FROM tasks";
        return getListOfTasks(sql);
    }
}
