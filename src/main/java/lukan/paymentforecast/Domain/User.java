package lukan.paymentforecast.Domain;

import java.util.ArrayList;
import java.util.List;

/*
 * This class represent a user.
 * It contains the users current hourly salary,
 * a list of a users supplement and a list of the days the user worked .
 */
public class User {
    
    public String name;
    public Double hourlySalary;
    public Double senioritySupplement;
    public List<Supplement> supplements;
    public List<WorkDay> workdays;

    public User(String name, Double hourlySalary) {
        this(name, hourlySalary, new ArrayList<WorkDay>(), new ArrayList<Supplement>(), 0.0);
    }

    public User(String name, Double hourlySalary, List<WorkDay> workdays, List<Supplement> supplements, Double senioritySupplement) {
        this.name = name;
        this.hourlySalary = hourlySalary;
        this.supplements = supplements;
        this.workdays = workdays;
        this.senioritySupplement = senioritySupplement;
    }
}
