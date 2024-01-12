package lukan.paymentforecast.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;
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
            if (line == null) {
                // When reaching EOF, readLine returns null
                break;
            }
            String[] data = line.split(",");
            User user = new User(data[0], Double.parseDouble(data[1]));
            users.add(user);
        }
        reader.close();
        return users;
    }

    public List<WorkDay> getWorkDays(User user) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./Data/workdays/" + user.name + ".csv"));
        List<WorkDay> workDays = new ArrayList<>();

        while(true) {
            String line = reader.readLine();
            // When reaching EOF, readLine returns null
            if (line == null)
                break;
            String[] data = line.split(",");
            List<TimeInterval> timeIntervals = getTimeIntervals(user.name + data[0]);           
            WorkDay workDay = new WorkDay(new Date(Long.parseLong(data[0])), timeIntervals, selectSupplementType(data[3]), user);
            workDays.add(workDay);
        }
        reader.close();
        return workDays;
    }

    public static List<TimeInterval> getTimeIntervals(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./Data/timeIntervals/"+fileName+".csv"));
        List<TimeInterval> timeIntervals = new ArrayList<>();

        while(true) {
            String line = reader.readLine();
            // When reaching EOF, readLine returns null
            if (line == null)
                break;
            String[] data = line.split(",");
            TimeInterval timeInterval = new TimeInterval(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Boolean.parseBoolean(data[2]));
            timeIntervals.add(timeInterval);
        }
        reader.close();
        return timeIntervals;
    }

    public static SupplementType selectSupplementType(String supplement) {
        return SupplementType.valueOf(supplement);
    }

    public List<Supplement> getSupplements(User user) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./data/supplements/" + user.name + ".csv"));
        List<Supplement> supplements = new ArrayList<>();

        while (true) {
            String line = reader.readLine();
            // When reaching EOF, readLine returns null
            if (line == null)
                break;
            String[] data = line.split(";");
            Supplement supplement = new Supplement(Double.parseDouble(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), selectSupplementType(data[3]));
            supplements.add(supplement);
        }
        reader.close();
        return supplements;
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
