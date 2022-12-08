import java.sql.*;

public class ExecuteQuery01 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Step:Registration to the Driver

        Class.forName("org.postgresql.Driver");

        //2. Step: Create connection with database

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "Ulviyeage7");

        // 3. Step: Create a Statement
        Statement st = con.createStatement();


        //1.Example:Select the country names whose region ids are 1
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";

        boolean result1 = st.execute(sql1);
        System.out.println("result1 = " + result1);


        //To see the records outcome we have another method which is "executeQuery()"

        ResultSet resultSet1 = st.executeQuery(sql1);

        while (resultSet1.next()) {

            System.out.println(resultSet1.getString("country_name"));

        }

        //3.Example: Select all columns whose number_of_employees is the lowest from companies table

        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3 = st.executeQuery(sql3);

        while (resultSet3.next()) {

            System.out.println(resultSet3.getInt(1) + "-> " + resultSet3.getString(2) + "-> " + resultSet3.getInt(3));

        }

        con.close();
        st.close();

    }
}
