import java.util.ArrayList;

import Controller.Supplement;
import Controller.User;
import Controller.WorkDay;

public class Main {

    public static void main(String[] args) {
        
        ArrayList<Supplement> supplements = new ArrayList<>() {{
            add(new Supplement(29.0, 18.0, 23.0, "n"));
            add(new Supplement(38.95, 23.0, 23.59, "n"));             
            add(new Supplement(38.95, 0.0, 6.0, "n"));
            add(new Supplement(51.0, 15.0, 24.0, "sa"));
            add(new Supplement(58.15, 0.0, 24.0, "sh"));            
        }};
        User userOne = new User(128.83, supplements);
        
        WorkDay d1 = new WorkDay("13:00", "21:00", "0:00","sh", userOne);
        d1.calculateSalary();
        System.out.println(d1.getSalary());
    }
}