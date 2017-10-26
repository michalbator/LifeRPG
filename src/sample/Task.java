package sample;

public class Task {
    private String name;
    private int points;

    Task(String name, int points){
        this.name = name;
        this.points = points;
    }

    public String getName(){
        return this.name;
    }
    public int getPoints() {
        return this.points;
    }
}
