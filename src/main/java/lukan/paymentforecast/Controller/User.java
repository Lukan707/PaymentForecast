package lukan.paymentforecast.Controller;

import java.util.ArrayList;

/*
 * This class represent a user.
 * It contains the users current hourly salary,
 * a list of a users supplement and a list of the days the user worked .
 */
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
