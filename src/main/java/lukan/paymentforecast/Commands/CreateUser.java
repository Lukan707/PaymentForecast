package lukan.paymentforecast.Commands;

import lukan.paymentforecast.Domain.User;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "newuser", description = "Creates a new user. Sets the current user to this user.")

public class CreateUser implements Runnable {

    @Parameters(paramLabel = "<name>", description = "Type your name", arity = "1")
    private String name;

    @Parameters(paramLabel = "<hourlySalary>", description = "Type your hourly salary", arity = "1")
    private double hourlySalary;

    @Override
    public void run() {
        User newUser = new User(name, hourlySalary);
    }

}