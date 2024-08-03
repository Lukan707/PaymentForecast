package lukan.paymentforecast.Commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lukan.paymentforecast.Domain.User;
import lukan.paymentforecast.Model.DataService;
import picocli.CommandLine.Command;

@Command(name = "listusers", description = "Lists all users.")
public class ListUsers implements Runnable {

    @Override
    public void run() {
        DataService data = new DataService();
        
        List<User> users = new ArrayList<>();

        try {
            users = data.getUsers();

            if (users.size() > 0) {
                System.out.println("Users:");
                for (User user : users) {
                    System.out.println("User name: " + user.name + ",  Hourly salary:" + user.hourlySalary);
                }
            } else {
                System.out.println("There are no users.");
            }
        } catch (FileNotFoundException FNFe) {
            System.out.println("Error: The needed file is not found");
        } catch (IOException IOe) {
            System.out.println("Error: The program is unable to access the file");
        }
    }
}
