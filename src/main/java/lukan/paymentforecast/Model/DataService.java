package lukan.paymentforecast.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lukan.paymentforecast.Domain.*;

/*
 * This class is an implementation of the DataService interface.
 * It contains methods for reading and writing data to a file.
 * 
 * Directory format:
 * - Data/
 *      - users/
 *      - workdays/
 *      - timeintervals/
 *      - userSupplements/
 *      - workdaySupplements/
 *
 * Data filenames:
 * - workday files: "{userName}.csv"
 * - timeInterval files: "{userName}{date}.csv"
 * 
 * Data formats:
 * - user files: username,hourly salary (double)
 * - workday files: date(long),workday type(WorkDayType),bonus
 * - timeInterval files: start time in seconds(int), end time in seconds(int), is a break(boolean)
 * - userSupplement files: supplemnt salary(double), start time in seconds(int), end time in seconds(int), supplement type(Supplement Type)
 * - workdaySupplements files: supplement type(SupplementType)
 */ 
public class DataService implements DataServiceInterface {
    
    public List<User> getUsers() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./Data/users/userList.csv"));
        List<User> users = new ArrayList<>();
        String line = "";

        while(true) {
            line = reader.readLine();
            if (line == null) {
                // When reaching EOF, readLine returns null
                break;
            }
            String[] data = line.trim().split(",");
            User user = new User(data[0], Double.parseDouble(data[1]));
            users.add(user);
        }
        reader.close();
        return users;
    }

    public List<WorkDay> getWorkDays(User user) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./Data/workdays/" + user.name + ".csv"));
        List<WorkDay> workDays = new ArrayList<>();
        String line = "";

        while(true) {
            line = reader.readLine();
            // When reaching EOF, readLine returns null
            if (line == null)
                break;
            String[] data = line.trim().split(",");
            List<TimeInterval> timeIntervals = getTimeIntervals(user.name + data[0]);   
            List<SupplementType> supplementTypes = getSupplementTypes();  
            WorkDay workDay = new WorkDay(new Date(Long.parseLong(data[0])), timeIntervals, selectWorkDayType(data[1]), user, supplementTypes, Double.parseDouble(data[2]));
            workDays.add(workDay);
        }
        reader.close();
        return workDays;
    }

    public static List<TimeInterval> getTimeIntervals(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./Data/timeintervals/"+fileName+".csv"));
        List<TimeInterval> timeIntervals = new ArrayList<>();
        String line = "";

        while(true) {
            line = reader.readLine();
            // When reaching EOF, readLine returns null
            if (line == null)
                break;
            String[] data = line.trim().split(",");
            TimeInterval timeInterval = new TimeInterval(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Boolean.parseBoolean(data[2]));
            timeIntervals.add(timeInterval);
        }
        reader.close();
        return timeIntervals;
    }

    private static WorkDayType selectWorkDayType(String workdayType) {
        return WorkDayType.valueOf(workdayType);
    }

    public static List<SupplementType> getSupplementTypes() {
        List<SupplementType> supplementTypes = new ArrayList<>();
        // Implement function that reads from the workDaysupplements file
        return supplementTypes;
    }

    public List<Supplement> getSupplements(User user) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./data/supplements/" + user.name + ".csv"));
        List<Supplement> supplements = new ArrayList<>();
        String line = "";

        while (true) {
            line = reader.readLine();
            // When reaching EOF, readLine returns null
            if (line == null)
                break;
            String[] data = line.trim().split(";");
            Supplement supplement = new Supplement(Double.parseDouble(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), selectSupplementType(data[3]));
            supplements.add(supplement);
        }
        reader.close();
        return supplements;
    }

    public static SupplementType selectSupplementType(String supplementType) {
        return SupplementType.valueOf(supplementType);
    }

    private void appendToFile(String filePath, String line) throws IOException {
        File file = new File(filePath);
        // Creates a new file, if and only if, the files does not already exists
        file.createNewFile();
        /* The boolean parameter specifies if the writer should append to the file,
         * instead of overwriting it. */
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(line);
        writer.close();
    }

    private void removeFromFile(String filePath, String linetoRemove) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        File tmpFile = new File(".Data/tmpFile.csv");
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile));
        String line = "";

        // Write every line to new temporary file, except the desired line
        while(true) {
            line = reader.readLine();
            if (line == null)
                break;
            if (line.trim().equals(linetoRemove))
                continue;
            /* Write the line to the temporary file, 
             * with a system specific newline character */
            writer.write(line + System.getProperty("line.seperator"));
        }
        reader.close();
        writer.close();
        // Delete original file
        file.delete();
        // Rename temporary file to the same name as the original file
        if (!tmpFile.renameTo(file))
            throw new IOException("Temporary file could not be renamed");
    }

    public void addUser(User user) throws IOException {
        appendToFile("./Data/users/userList.csv", user.name + "," + user.hourlySalary);
    }

    public void removeUser(User user) throws FileNotFoundException, IOException {
        removeFromFile("./Date/users/userLists.csv", user.name + "," + user.hourlySalary);
    }

    public void addWorkDay(WorkDay workDay) throws IOException {
        appendToFile("./Data/workdays/" + workDay.getUser().name + ".csv", workDay.getDate() + "," + workDay.getType());
    }
    
    public void removeWorkDay(WorkDay workDay) throws FileNotFoundException, IOException {
        removeFromFile("./Data/workdays/" + workDay.getUser().name + ".csv", workDay.getDate() + "," + workDay.getType());
    }

    public void addTimeInterval(WorkDay workDay, TimeInterval timeInterval) throws IOException {
        appendToFile("./Data/timeintervals/" + workDay.getUser().name + workDay.getDate() + ".csv", timeInterval.startTimeInSeconds + "," + timeInterval.endTimeInSeconds + "," + timeInterval.isBreak);
    }

    public void removeTimeInterval(WorkDay workDay, TimeInterval timeInterval) throws FileNotFoundException, IOException {
        removeFromFile("./Data/timeintervals/" + workDay.getUser().name + workDay.getDate() + ".csv", timeInterval.startTimeInSeconds + "," + timeInterval.endTimeInSeconds + "," + timeInterval.isBreak);
    }

    public void addSupplement(User user, Supplement supplement) throws IOException {
        appendToFile("./Data/supplements/" + user.name + ".csv", supplement.supplementSalary + "," + supplement.startTimeInSeconds + "," + supplement.endTimeInSeconds + "," + supplement.supplementType.toString());
    }

    public void removeSupplement(User user,Supplement supplement) throws IOException {
        removeFromFile("./Data/supplements/" + user.name + ".csv", supplement.supplementSalary + "," + supplement.startTimeInSeconds + "," + supplement.endTimeInSeconds + "," + supplement.supplementType.toString());
    }
}
