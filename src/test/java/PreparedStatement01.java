import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //1. Step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        //2. Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "Ulviyeage7");

        //3. Step: Create statement
        Statement st = con.createStatement();
        System.out.println("Connection Success");



        //1.Example: Update the number of employees to 20000 if the company name is IBM by using prepared statement

        //1. Step: Create Prepared Statement Query
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?"; //Prepared Statement

        //2. Step: Create Prepared Statement Object
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3. Step: Assign the values by using 'setInt(), setString() ...' methods.
        pst1.setInt(1, 25000);
        pst1.setString(2, "GOOGLE");

        //4. Step: Execute the Query
        int numOfRecordsUpdated = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated = " + numOfRecordsUpdated);

        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet1 = st.executeQuery(sql2);

        while (resultSet1.next()) {
            System.out.println(resultSet1.getInt(1) + "->" + resultSet1.getString(2) + "->" + resultSet1.getInt(3));
        }


        //2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement
        pst1.setInt(1,8000);
        pst1.setString(2,"IBM");

        int numOfRecordsUpdated2 = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated2 = " + numOfRecordsUpdated2);

        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getInt("company_id") + "->" + resultSet2.getString("company") + "->" + resultSet2.getInt("number_of_employees"));
        }

        con.close();
        st.close();
        pst1.close();























    }
}
