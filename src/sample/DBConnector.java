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
        String sql = "SELECT name, points FROM tasks WHERE points = " + Integer.toString(points) + ";";
        return getListOfTasks(sql);
    }
    private ArrayList<Task> getListOfTasks(String sql){
        ResultSet resultSet = null;
        Statement statement = null;
        ArrayList<Task> result = new ArrayList<>();
        String nm = null;
        int pt;
        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                nm = resultSet.getString("name");
                pt = resultSet.getInt("points");
                result.add(new Task(nm, pt));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    ArrayList<Task>  getTaskByName(String name) throws SQLException{
        String sql = "SELECT name, points FROM tasks WHERE name = '" + name + "';";
        return getListOfTasks(sql);
    }
    public void addTask(String name, String points){
        String sql = "INSERT INTO tasks (name, points) VALUES ('" + name + "', " + points + ");";
        Statement statement = null;
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
