import java.sql.*;


public class JDBCUtils {

    private static Connection connection;
    private static  Statement statement;

    public static Connection connectToDatabase(String hostName, String dataBaseNAme, String userName, String password){
        //1. Step:Registration to the Driver

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //2. Step: Create connection with database

        try {
            connection= DriverManager.getConnection("jdbc:postgresql://"+ hostName+":5433/"+ dataBaseNAme, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connection is successful!");
        return connection;

    }
    //3 Step:Create statement
    public static Statement createStatement(){

        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Statement created!");

        return statement;
    }
    //4.Execute the query

    public static boolean execute(String query){

        boolean isExecuted;

        try {
            isExecuted= statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Command executed successfully");
        return isExecuted;

    }
    //5.Step: Close the connection and statement
    public static void closeConnectionAndStatement() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        try {
            if (connection.isClosed() && statement.isClosed()) {
                System.out.println("Connection and statement closed");
            } else {
                System.out.println("Connection and statement not closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dropTable(String tableName){

        try {

            statement.execute("DROP TABLE " + tableName);
            System.out.println("Table " + tableName + " dropped!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void createTable(String tableName, String... columnName_DataType ){

        StringBuilder columnName_DataTypeString= new StringBuilder("");

        for(String w: columnName_DataType) {

            columnName_DataTypeString.append(w).append(",");

        }

        columnName_DataTypeString.deleteCharAt(columnName_DataTypeString.lastIndexOf(","));

        try {
            statement.execute("CREATE TABLE "+ tableName+ "(" +columnName_DataTypeString+")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Table created successfully!");

    }

    //INSERT INTO tablename  (columnName1, columnName2 ...) VALUES (value1, value2 ...)

    public static void insertDataIntoTable(String tablename, String... columnName_Values){

        StringBuilder columnName= new StringBuilder("");
        StringBuilder value= new StringBuilder("");

        for(String w: columnName_Values){

            columnName.append(w.split(" ")[0]).append(",");
            value.append(w.split(" ")[1]).append(",");
        }

        columnName.deleteCharAt(columnName.lastIndexOf(","));
        value.deleteCharAt(value.lastIndexOf(","));

        String command="INSERT INTO "+ tablename +  "("+ columnName+")"+ "VALUES ("+value+")";

        try {
            statement.execute(command);
            System.out.println("Data inserted successfully into " + tablename);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }







}
