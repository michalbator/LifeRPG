package sample;

public class Task {
    private String name;
    private int points;
    private final int id;

    Task(String name, int points, int id){
        this.name = name;
        this.points = points;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public int getPoints() {
        return this.points;
    }
    public int getId() {return this.id;}
}
