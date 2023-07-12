package Application;

import java.util.ArrayList;

public class User {
    
    public Double hourlySalary;
    public ArrayList<Supplement> supplements;
    public ArrayList<WorkDay> workDays;

    public User(Double hourlySalary, ArrayList<Supplement> supplements) {
        this.hourlySalary = hourlySalary;
        this.supplements = supplements;
        this.workDays = new ArrayList<>();
    }
}
