package lukan.paymentforecast.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import lukan.paymentforecast.Domain.User;
import lukan.paymentforecast.Domain.Exceptions.NoCurrentUser;

public class TestCurrentUser {

    @Test
    public void TestGetCurrentUser() {
        // Arrange
        User testUser = new User("Alice", 120.0);
        User response = null;
        File file = new File("./Data/users/currentUser.csv");
        DataService data = new DataService();

        try {
            file.delete();
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            writer.write("Alice, 120.0");
            writer.close();
        } catch (IOException e) {
            
        }

        // Act
        try {
            response = data.getCurrentUser();

        } catch (Exception e) {
            
        }

        // Assert
        assertEquals(testUser.name, response.name);
        assertEquals(testUser.hourlySalary, response.hourlySalary);
    }

    @Test
    public void TestGetCurrentUserThrowsNoCurrentUserException() {
        // Arrange
        File file = new File("./Data/users/currentUSer.csv");
        DataService data = new DataService();

        System.out.flush();
        System.out.println("Printing is possible");

        assertTrue(file.delete());

        try {
            // file.delete();
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            
        }

        // assertThrows(NoCurrentUser.class, () -> data.getCurrentUser());
    }

    @Test
    public void TestSetCurrentUser() {
        // Arrange
        User testUser = new User("Alice", 130.0);
        // User response = new User("Alice", 130.0);
        User response = null;
        File file = new File("./Data/users/currentUser.csv");
        DataService data = new DataService();
        
        
        // Act
        try {
            file.delete();
            file.getParentFile().mkdirs();
            file.createNewFile(); // fails here
            data.setCurrentUser(testUser);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] line = reader.readLine().trim().split(",");
            reader.close();
            response = new User(line[0], Double.parseDouble(line[1]));
        } catch (IOException e) {
            // Print out that an ioexception is raised and its correponding error message
        }

        // Assert
        assertEquals(testUser.name, response.name);
        assertEquals(testUser.hourlySalary, response.hourlySalary);
    }

    @Test
    public void TestSetCurrentUserCreatesNewFile() {
        // Arrange
        User testUser = new User("Alice", 130.0);
        User response = null;
        File file = new File("./Data/users/currentUser.csv");
        DataService data = new DataService();
        
        // Act
        
        try {
            file.delete();
            data.setCurrentUser(testUser);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] line = reader.readLine().trim().split(",");
            reader.close();
            response = new User(line[0], Double.parseDouble(line[1]));
        } catch (IOException e) {

        }

        // Assert
        assertEquals(testUser.name, response.name);
        assertEquals(testUser.hourlySalary, response.hourlySalary);
    }

    @Test
    public void TestSetCurrentUserOverwritesFileContent() {
        // Arrange
        User testUser = new User("Bob", 130.0);
        User response = null;
        File file = new File("./Data/users/currentUser.csv");
        DataService data = new DataService();
    
        try {
            file.delete();
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            writer.write("Alice, 120.0");
            writer.close();
        } catch (IOException e) {

        }

        // Act
        try {
            data.setCurrentUser(testUser);
            response = data.getCurrentUser();
        } catch (Exception e) {
            // FIX ME, does not work
            // System.out.println("Error: could not set or retrieve current user; " + e.getMessage());
        }

        // Assert
        assertEquals(testUser.name, response.name);
        assertEquals(testUser.hourlySalary, response.hourlySalary);
    }

}