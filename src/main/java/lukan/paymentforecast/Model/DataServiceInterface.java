package lukan.paymentforecast.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import lukan.paymentforecast.DomainLogic.*;

public interface DataServiceInterface {
    public List<User> getUsers() throws FileNotFoundException, IOException;
    public List<WorkDay> getWorkDays(User user) throws FileNotFoundException;
    public List<TimeInterval> getTimeIntervals(WorkDay workDay);
    public List<Supplement> getSupplements(User user);
    public void addUser(User user);
    public void removeUser(User user);
    public void addWorkDay(User user);
    public void removeWorkDay(User user);
    public void addTimeInterval(WorkDay workDay);
    public void removeTimeInterval(WorkDay workDay);
    public void addSupplement(User user);
    public void removeSupplement(User user);
}