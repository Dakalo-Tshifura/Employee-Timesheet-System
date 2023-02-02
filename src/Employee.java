import java.util.Scanner;

public abstract class Employee {
    private int empId;
    private String empName;
    private Double empRate;
    private EmployeeType empType;
    private Scanner input;
    private double hoursWorked;
    private double hourlyWage;

    public Employee(int empId, String empName, double empRate,EmployeeType empType, double hoursWorked, double hourlyWage){

        this.empId= empId;
        this.empName=empName;
        this.empRate=empRate;
        this.empType=empType;
        this.hoursWorked=hoursWorked;
        this.hourlyWage=hourlyWage;
    }

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
    public void setHoursWorked(double hoursWorked){
        this.hoursWorked = hoursWorked;
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
    public double getHoursWorked(){
        return this.hoursWorked;
    }
    public double getHourlyWage(){
        return this.hourlyWage;
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

    public  double calculatePay(){
        return this.hourlyWage *this.hoursWorked;
    }
}
