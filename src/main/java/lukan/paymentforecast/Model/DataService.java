package lukan.paymentforecast.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lukan.paymentforecast.DomainLogic.*;

/*
 * This class is an implementation of the DataService interface.
 * It contains methods for reading and writing data to a file.
 */ 
public class DataService implements DataServiceInterface {
    
    public List<User> getUsers() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./Data/users/userList.csv"));

        List<User> users = new ArrayList<>();

        while(true) {
            String line = reader.readLine();
            if (line != null) {
                break;
            }
        }

        reader.close();

        return users;
    }

    public List<WorkDay> getWorkDays(User user) {
        throw new UnsupportedOperationException();
    }

    public List<TimeInterval> getTimeIntervals(WorkDay workDay) {
        throw new UnsupportedOperationException();
    }

    public List<Supplement> getSupplements(User user) {
        throw new UnsupportedOperationException();
    }

    public void addUser(User user) {
        throw new UnsupportedOperationException();
    }

    public void removeUser(User user) {
        throw new UnsupportedOperationException();
    }

    public void addWorkDay(User user) {
        throw new UnsupportedOperationException();
    }

    public void removeWorkDay(User user) {
        throw new UnsupportedOperationException();
    }

    public void addTimeInterval(WorkDay workday) {
        throw new UnsupportedOperationException();
    }

    public void removeTimeInterval(WorkDay workDay) {
        throw new UnsupportedOperationException();
    }

    public void addSupplement(User user) {
        throw new UnsupportedOperationException();
    }

    public void removeSupplement(User user) {
        throw new UnsupportedOperationException();
    }
}
