package lukan.paymentforecast.Data;

import java.util.List;
import lukan.paymentforecast.DomainLogic.*;

public interface DataServiceInterface {
    public List<User> getUsers();
    public List<WorkDay> getWorkDays(User user);
    public List<TimeInterval> getTimeIntervals(WorkDay workDay);
    public void AddUser(User user);
    public void RemoveUser(User user);
    public void AddWorkDay(User user);
    public void RemoveWorkDay(User user);
    public void AddTimeInterval(WorkDay workDay);
    public void RemoveTimeInterval(WorkDay workDay);
}