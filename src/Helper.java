import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class Helper extends Employee {
    private Scanner input;

    public Helper() {
        super();
        this.input = new Scanner(System.in);
    }

    public int showMenu() {
        System.out.println("1. Add Employee");
        System.out.println("2. View Employees");
        System.out.println("3. View Timesheet");
        System.out.println("99. Logout");

        return this.input.nextInt();
    }

    public void addEmployee() {
        try {
            System.out.println("=CREATE NEW EMPLOYEE");
            System.out.println();

            System.out.print("Name: ");
            setEmployeeName(this.input.next());

            System.out.print("ID Number: ");
            setEmployeeId(this.input.nextInt());

            System.out.print("Rate: R");
            setEmployeeRate(this.input.nextDouble());

            System.out.print("Employee Type: \n1. Full-time\n2. Part-Time\n ");
            setEmployeeType(this.input.nextInt() == 1 ? EmployeeType.full : EmployeeType.part);

            // Invoke the save method!!
            save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
