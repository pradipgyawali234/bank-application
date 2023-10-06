import java.sql.*;
import java.util.Scanner;

public class BankDetails {

    private Long acc_no;
    private String acc_name;
    private String acc_type;
    private long balance;
    Scanner sc = new Scanner(System.in);

    public void openAccount() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bank";
        String user = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, user, password);
        try {

            System.out.println("Enter your Account No.:");
            acc_no = sc.nextLong();

            System.out.println("Enter your Account Type:");
            acc_type = sc.next();
            System.out.println("Enter your Account Name:");
            acc_name = sc.next();
            System.out.println("Enter your Account Balance:");
            balance = sc.nextLong();
            String sql = "insert into table1(acc_no,acc_type,name,balance) values (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);


            statement.setLong(1, acc_no);
            statement.setString(2, acc_type);
            statement.setString(3, acc_name);
            statement.setLong(4, balance);
            statement.executeUpdate();
            System.out.println("Record updated.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showAccount() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement statement = connection.createStatement();

            System.out.println("Enter the account number you want to search.");
            acc_no = sc.nextLong();

            ResultSet resultSet = statement.executeQuery("select * from table1 where acc_no =" + acc_no);
            while (resultSet.next()) {

                acc_no = resultSet.getLong(1);
                System.out.println("Account Number:" + acc_no);
                acc_type = resultSet.getString(2);
                System.out.println("Account Type:" + acc_type);
                acc_name = resultSet.getString(3);
                System.out.println("Account Name:" + acc_name);
                balance = resultSet.getLong(4);
                System.out.println("Account Balance:" + balance);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void deposit() {
        long amt;

        System.out.print("Enter Account no. : ");
        acc_no = sc.nextLong();
        System.out.println("Enter the amount you want to deposit:");
        amt = sc.nextLong();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");


            String query = "select * from table1 where acc_no=" + acc_no;
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(query);
            while (resultSet.next()) {
                balance = resultSet.getLong("balance");
                long deposited = balance + amt;

                String sql = "UPDATE table1 SET balance = ? WHERE acc_no = " + acc_no;
                PreparedStatement statement = connection.prepareStatement(sql);


                statement.setLong(1, deposited);

                statement.executeUpdate();
            }


            System.out.println("Record updated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdrawal() {
        long amt;
        System.out.print("Enter Account No : ");
        acc_no = sc.nextLong();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");


            String query = "select * from table1 where acc_no=" + acc_no;
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(query);
            while (resultSet.next()) {
                balance = resultSet.getLong("balance");
                System.out.println("Enter the amount you want to withdraw:");
                amt = sc.nextLong();
                if (balance >= amt) {
                    balance = balance - amt;
                    System.out.println("After withdrawal, you have: " + balance + "on your account");


                    String sql = "UPDATE table1 SET balance = ? WHERE acc_no = " + acc_no;
                    PreparedStatement statement = connection.prepareStatement(sql);


                    statement.setLong(1, balance);

                    statement.executeUpdate();
                } else {
                    System.out.println("Your balance is less than" + amt + "\tTransaction Failed!!!");
                }
            }
            System.out.println("Record updated.");
            resultSet.close();
            statement1.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
