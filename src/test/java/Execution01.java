import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execution01 {

    public static void main(String[] args)  throws ClassNotFoundException, SQLException {

        //1. Step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        //2. Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres","postgres","Ulviyeage7");

        //3. Step: Create statement
        Statement st = con.createStatement();
        System.out.println("Connection Success");

        //4 Step:Execute query

        String sql = "CREATE TABLE workers(worker_id VARCHAR(10), worker_name VARCHAR(50), worker_salary INT)";
        boolean sqlResult = st.execute("sql");
        System.out.println(sqlResult);

        /*
         execute() method can be used in DDL(Table creation, drop table, alter table) and DQL(Select)
        1)If you use execute() method in DDL you will get false everytime.
        2)If you use execute() method in DQL you will get false or true
            When you use execute() method in DQL, if you get ResultSet Object as return you will get true
            otherwise you will get false.
         */


        //--2.Example: Alter the table by adding worker_address column into the workers table

       String sql1 = " ALTER TABLE workers ADD worker_address VARCHAR(80)";
      // st.execute(sql1);

       String sql2 ="DROP TABLE workers";
       st.execute(sql2);



        //5. Step: Close the connection and statement
        con.close();
        st.close();

    }
}

/*
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/Emina3/JDBC-.git
git push -u origin main

 */
