import java.util.Scanner;

public abstract class Employee {
    private int empId;
    private String empName;
    private Double empRate;
    private EmployeeType empType;
    private Scanner input;

    public Employee() {
        this.input = new Scanner(System.in);
    }

    public void setEmployeeId(int id) {
        this.empId = id;
    }

    public void setEmployeeName(String name) {
        this.empName = name;
    }

    public void setEmployeeRate(Double rate) {
        this.empRate = rate;
    }

    public void setEmployeeType(EmployeeType type) {
        this.empType = type;
    }

    public int getEmployeeId() {
        return this.empId;
    }

    public String getEmployeeName() {
        return this.empName;
    }

    public Double getEmployeeRate() {
        return this.empRate;
    }

    public String getEmployeeType() {
        return this.empType.toString();
    }

    public void save() {
        if (DB.instance().saveEmployee(getEmployeeId(), getEmployeeName(), getEmployeeRate(), getEmployeeType())) {
            System.out.println("Employee saved successfully...");
        } else {
            System.out.println("Failed to save employee...");
        }
    }

    public void viewEmployees() {
        DB.instance().viewNonManagerialEmployees();
    }

    public void viewTimesheet(){
        System.out.print("Enter Employee ID: ");
        DB.instance().generateTimesheet(this.input.nextInt());
    }

    public abstract double calculatePay();
}
