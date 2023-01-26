import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Singleton class for managing database operations
public class DB {
    private static String dbUrl = "jdbc:mysql://localhost:3306/g4l";
    private static String dbUser = "root";
    private static String dbPass = "";
    private static Connection dbConnection;
    private static DB _instance;
    

    public DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            //System.out.println("Connection successful...");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassError: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLError: " + e.getMessage());
        }
    }
        // Static method
        // Static method to create instance of Singleton class
    public static DB instance() {
        if (_instance != null) {
            return _instance;
        } else {
            _instance = new DB();
            return _instance;
        }
    }

    public static boolean isValidConnection() {
        return dbConnection != null;
    }

    public Boolean saveEmployee(int eId, String eName, Double eRate, String eType) {
        if (!isValidConnection()) {
            System.out.println("Invalid connection...");
            return false;
        }

        String query = "INSERT INTO `employees`(`employee_id`, `employee_name`, `employee_rate`, `employee_type`, `isManager`, `password`) VALUES (?, ?, ?, ?, false, NULL)";

        try (PreparedStatement st = dbConnection.prepareStatement(query)) {
            st.setInt(1, eId);
            st.setString(2, eName);
            st.setDouble(3, eRate);
            st.setString(4, eType);
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean login(String uname, String pword) {
        try {
            Statement st = dbConnection.createStatement();
            String query = "SELECT * FROM `employees` WHERE `employee_id` = " + uname + " AND `password` = '" + pword
                    + "' AND `isManager` = true";
            ResultSet manager = st.executeQuery(query);

            if (manager.next())
                return true;
            else
                throw new SQLException();
        } catch (SQLException e) {
            System.out.println("Invalid username or password");
        }
        return false;
    }
 
    public void viewNonManagerialEmployees() {
        if (!isValidConnection()) {
            System.out.println("Invalid connection");
            return;
        }

        try (Statement st = dbConnection.createStatement()) {
            ResultSet emp = st.executeQuery("SELECT * FROM `employees` WHERE `isManager` = false");

            while (emp.next()) {
                System.out.println(emp.getInt("employee_id") +"\t"+ emp.getString("employee_name") + "\tR" + emp.getDouble("employee_rate") +"\t"+ emp.getString("employee_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getEmployeeById(int empId) {
        if (!isValidConnection()) {
            System.out.println("Invalid connection");
            return null;
        }

        try (Statement st = dbConnection.createStatement()) {
            String query = "SELECT `employee_id`, `employee_name`, `employee_rate`, `employee_type` FROM `employees` WHERE `employees`.`employee_id` = "
                    + empId + "";
            return st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void generateTimesheet(int empId) {
        int totalWorkedHours = 0;

        if (!isValidConnection()) {
            System.out.println("Invalid connection");
            return;
        }


       System.out.println("NAME \t"+ "ID\t" + "RATE\t" + "TYPE\t" + "HRS\t");

        try (Statement st = dbConnection.createStatement()) {
            // Retrieving Employee Information
            String empQuery = "SELECT `employee_id`, `employee_name`, `employee_rate`, `employee_type` FROM `employees` WHERE `employees`.`employee_id` = "
                    + empId + "";
            ResultSet employee = st.executeQuery(empQuery);

            // yyavhvjhvjhvahv
            // Viewing the retrieved employee timesheet
            if (employee.next()) {
                int employeeId = employee.getInt("employee_id");
                String employeeName = employee.getString("employee_name");
                Double employeeRate = employee.getDouble("employee_rate");
                String employeeType = employee.getString("employee_type");

                String sheetQuery = "SELECT `work_date`, `work_start`, `work_end`, `lunch_start`, `lunch_end` FROM `timesheet` WHERE `employee_id` = "
                        + employee.getInt("employee_id") + "";
                ResultSet timesheet = st.executeQuery(sheetQuery);
                
                while (timesheet.next()) {
                    totalWorkedHours += this.employeeWorkHours(timesheet.getTime("work_start").toString(),
                            timesheet.getTime("work_end").toString(), timesheet.getTime("lunch_start").toString(),
                            timesheet.getTime("lunch_end").toString());
                }
                
                System.out.println(employeeName +"\t"+ employeeId + "\tR" + employeeRate+"\t"+ employeeType +"\t"+ totalWorkedHours);
            }

            System.out.println();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Helper method for calculating hours worked
    public int employeeWorkHours(String startTime, String endTime, String startLunch, String endLunch) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        try {
            long totalWorkTime = Math
                    .abs((format.parse(startTime).getTime() - format.parse(endTime).getTime()) / 3600000);

            if (startLunch != null && endLunch != null) {
                long totalLunchTime = Math
                        .abs((format.parse(startLunch).getTime() - format.parse(endLunch).getTime()) / 3600000);

                totalWorkTime -= totalLunchTime;
            }

            // Can be made dynamic...
            if ((int) totalWorkTime > 8)
                return 8;
            else
                return (int) totalWorkTime;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}
