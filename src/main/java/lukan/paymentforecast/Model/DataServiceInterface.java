package lukan.paymentforecast.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import lukan.paymentforecast.Domain.*;
import lukan.paymentforecast.Domain.Exceptions.NoCurrentUser;

public interface DataServiceInterface {
    public User getCurrentUser() throws FileNotFoundException, IOException, NoCurrentUser;
    public List<User> getUsers() throws FileNotFoundException, IOException;
    public List<WorkDay> getWorkDays(User user) throws FileNotFoundException, IOException;
    public List<Supplement> getSupplements(User user) throws FileNotFoundException, IOException;
    public void setCurrentUser(User user) throws IOException;
    public void addUser(User user) throws IOException;
    public void removeUser(User user) throws FileNotFoundException, IOException;
    public void addWorkDay(WorkDay workday) throws IOException;
    public void removeWorkDay(WorkDay workday) throws FileNotFoundException, IOException;
    public void addTimeInterval(WorkDay workday, TimeInterval timeInterval) throws IOException;
    public void removeTimeInterval(WorkDay workday, TimeInterval timeInterval) throws FileNotFoundException, IOException;
    public void addSupplement(User user, Supplement supplement) throws IOException;
    public void removeSupplement(User user, Supplement supplement) throws FileNotFoundException, IOException;
    public void addSupplementType(WorkDay workDay, SupplementType supplementType) throws IOException;
    public void removeSupplementType(WorkDay workDay, SupplementType supplementType) throws FileNotFoundException, IOException;
}