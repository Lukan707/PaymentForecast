package lukan.paymentforecast.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import lukan.paymentforecast.Domain.User;

public class TestSetCurrentUser {

    @Test
    public void TestSetCurrentUserOverwritesFileContent() {
        // Arrange
        User testUser = new User("Charlie", 110.0);
        File file = new File("./Data/users/currentUser.csv");

        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            writer.write("Alice, 120.0" + System.getProperty("line.seperator"));
            writer.write("Bob, 130.0" + System.getProperty("line.seperator"));
            writer.close();
        } catch (IOException e) {

        } finally {

        }

        // Act
        User respone = null;
        DataService data = new DataService();
        try {
            data.setCurrentUser(testUser);
            respone = data.getCurrentUser();
        } catch (Exception e) {
            System.out.println("Error: could not set or retrieve current user; " + e.getMessage());
        }

        // assertThrows(IOException.class, () -> data.setCurrentUser(testUser));
        // Assert
        assertEquals(testUser.name, respone.name);
        assertEquals(testUser.hourlySalary, respone.hourlySalary);
    }

}