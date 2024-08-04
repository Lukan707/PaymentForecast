package lukan.paymentforecast;

import lukan.paymentforecast.Commands.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import lukan.paymentforecast.Domain.User;
import lukan.paymentforecast.Domain.Exceptions.NoCurrentUser;
import lukan.paymentforecast.Model.DataService;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(
    name = "paymentforecast", 
    version = "1.0.0", 
    mixinStandardHelpOptions = true, 
    subcommands = { 
        CreateUser.class,
        DeleteUser.class,
        ListUsers.class,
        CommandLine.HelpCommand.class 
    }
)

public class App {
    User user;

    public App(User user) {
        this.user = user;
    }

    @Spec
    CommandSpec spec;

    static User setDefaultCurrentUser() {
        User user = null;
        // create file currentUser if it does not exists

        // if file of users exists and it have content, select the first user as default
        // and inform user
        // if not, inform user to run the setUser command

        return user;
    }

    public static void main(String[] args) {

        User currentUser = null;
        DataService data = new DataService();
        
        try {
            currentUser = data.getCurrentUser();
        } catch (FileNotFoundException FNFe) {
            currentUser = setDefaultCurrentUser();
        } catch (IOException IOe) {
            System.out.println("Error: There was a problem reading from the file currentuser.csv");
            System.exit(1);
        } catch (NoCurrentUser NCUe) {
            currentUser = setDefaultCurrentUser();
        }

        int exitCode = new CommandLine(new App(currentUser)).execute(args);
        System.exit(exitCode);
    }
}