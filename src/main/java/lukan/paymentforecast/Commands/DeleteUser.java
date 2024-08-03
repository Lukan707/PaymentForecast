package lukan.paymentforecast.Commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lukan.paymentforecast.Domain.User;
import lukan.paymentforecast.Domain.Exceptions.NoCurrentUser;
import lukan.paymentforecast.Model.DataService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "deleteuser", description = "Deletes the specified user, if such a user exists.")
public class DeleteUser implements Runnable {

    @Parameters(paramLabel = "<name>", description = "Type the name of the user you wish to delete", arity = "1")
    String name;

    @Override
    public void run() {
        DataService data = new DataService();
        
        User user = null;
        List<User> users = new ArrayList<User>();

        try {
            users = data.getUsers();
        } catch (FileNotFoundException FNFe) {

        } catch (IOException IOe) {

        }

        boolean hasBeenFound = false;
        for (User u : users) {
              if (u.name.equals(name)) {
                hasBeenFound = true;
                user = u;
            }
        }
        
        if (hasBeenFound == false) {
            System.out.println("The given user does not exist. Check the typing of the users name, if correct, no further action is needed.");
        } else {
            try {
                data.removeUser(user);
                data.deleteAllWorkDays(user);
            } catch (FileNotFoundException FNFe) {
                System.out.println("error 1");
            } catch (IOException IOe) {
                System.out.println("error 2");
            }
            try {
                User currentUser = data.getCurrentUser();
                if (currentUser.name == name)
                    data.removeCurrentUser();
            } catch (FileNotFoundException FNFe) {

            } catch (IOException IOe) {

            } catch (NoCurrentUser NCUe) {

            }
        }
    }
}
