public class HourlyEmployee extends Employee {
  
    private double hoursWorked;

    private double hourlyWage;

    public HourlyEmployee(int empId, String empName,double empRate,EmployeeType empType, double hoursWorked,double hourlyWage){
        //super(empId,empName,empRate, empType);
        this.hoursWorked=hoursWorked;
        this.hourlyWage =hourlyWage;
    }
    public void setHoursWorked(double hoursWorked){
        this.hoursWorked=hoursWorked;
    }
    public void setHourlyWage(double hourlyWage){
        this.hourlyWage=hourlyWage;
    }
    public double getHoursWorked(){
        return hoursWorked;
    }
    public double getHourlyWage(){
        return hourlyWage;
    }


    @Override
    public double calculatePay() {
        return this.hoursWorked * hourlyWage;
    }
    
}
