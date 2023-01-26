import java.util.Scanner;

public class App extends Helper {
    private Scanner input;

    public App() {
        super();
        this.input = new Scanner(System.in);
    }

    public void startApp() {
        System.out.print("Enter Username: ");
        String username = this.input.next();
        System.out.print("Enter Password: ");
        String password = this.input.next();

        if (DB.instance().login(username, password)) {
            System.out.println();
            System.out.println("WELCOME TO Geeks4Learning");
            System.out.println();

            while (true) {
                switch (showMenu()) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        viewEmployees();
                        break;
                    case 3:
                        viewTimesheet();
                        break;
                    case 99:
                        return;
                }
            }
        }
    }

    @Override
    public double calculatePay() {
      
        return 0;
    }
}
