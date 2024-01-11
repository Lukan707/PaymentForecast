package lukan.paymentforecast.Model;

import java.util.List;
import lukan.paymentforecast.DomainLogic.*;

/*
 * This class is an implementation of the DataService interface.
 * It contains methods for reading and writing data to a file.
 */ 
public class DataService implements DataServiceInterface {
    
    public DataService() {
        
    }
    
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
