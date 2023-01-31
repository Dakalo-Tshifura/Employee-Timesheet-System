public class SalaryEmployee extends Employee {

    double basicSalary;

    public SalaryEmployee(int empId, String empName,double empRate,EmployeeType empType,double basicSalary){
        super();
        this.basicSalary=basicSalary;
    }

    @Override
    public double calculatePay() {
        return this.basicSalary/12;
    }
    
}
