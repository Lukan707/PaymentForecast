package lukan.paymentforecast.DomainLogic;

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

    public User(Double hourlySalary) {
        this.hourlySalary = hourlySalary;
        this.supplements = new ArrayList<>();
        this.workDays = new ArrayList<>();
    }

    public void setSupplements(ArrayList<Supplement> supplements) {
        this.supplements = supplements;
    }
}
