package lukan.paymentforecast.Commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lukan.paymentforecast.Domain.User;
import lukan.paymentforecast.Model.DataService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "deleteuser", description = "Deletes the specified user, if such a user exists.")
public class ListUsers implements Runnable {

    @Parameters(paramLabel = "<name>", description = "Type the name of the user you wish to delete", arity = "1")
    String name;

    @Override
    public void run() {
        DataService data = new DataService();
        
        List<User> users = new ArrayList<>();

        try {
            users = data.getUsers();

            if (users.size() > 0) {
                System.out.println("Users:");
                for (User user : users) {
                    System.out.println(user.name + " - " + user.hourlySalary);
                }
            } else {
                System.out.println("There are no users.");
            }
        } catch (FileNotFoundException FNFe) {

        } catch (IOException IOe) {

        }
    }
}
