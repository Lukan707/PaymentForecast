package lukan.paymentforecast.Data;

import java.util.List;
import lukan.paymentforecast.DomainLogic.*;

public class DataService implements DataServiceInterface {
    public List<User> getUsers() {
       throw new UnsupportedOperationException(); 
    }

    public List<WorkDay> getWorkDays(User user) {
        throw new UnsupportedOperationException();
    }

    public List<TimeInterval> getTimeIntervals(WorkDay workDay) {
        throw new UnsupportedOperationException();
    }

    public void AddUser(User user) {
        throw new UnsupportedOperationException();
    }

    public void RemoveUser(User user) {
        throw new UnsupportedOperationException();
    }

    public void AddWorkDay(User user) {
        throw new UnsupportedOperationException();
    }

    public void RemoveWorkDay(User user) {
        throw new UnsupportedOperationException();
    }

    public void AddTimeInterval(WorkDay workday) {
        throw new UnsupportedOperationException();
    }

    public void RemoveTimeInterval(WorkDay workDay) {
        throw new UnsupportedOperationException();
    }
}
