package lukan.paymentforecast;

import java.io.FileNotFoundException;
import java.io.IOException;

import lukan.paymentforecast.Domain.User;
import lukan.paymentforecast.Model.DataService;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "paymentforecast", version = "1.0.0", 
mixinStandardHelpOptions = true,
subcommands = { CreateUser.class, CommandLine.HelpCommand.class})

public class App
{
    @Spec CommandSpec spec;

    static void setDefaultCurrentUser() {
        
    }

    public static void main( String[] args )
    {
        DataService data = new DataService();
        try {
            User currentUser = data.getCurrentUser();
        } catch (FileNotFoundException e) {
            System.out.println("");
            System.exit(0);
        }

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

@Command(
    name = "newuser",
    description = "Creates a new user. Sets the current user to this user."
)

class CreateUser implements Runnable {

    @Parameters(
        paramLabel = "<name>", 
        description = "Type your name", 
        arity = "1")
    private String name;

    @Parameters(
        paramLabel = "<hourlySalary>",
        description = "Type your hourly salary",
        arity = "1")
    private double hourlySalary;

    @Override
    public void run() {
        User newUser = new User(name, hourlySalary);
    }

}