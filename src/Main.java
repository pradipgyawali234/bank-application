import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        BankDetails C = new BankDetails();
            System.out.println("\n-----------Banking System Application----------");
            System.out.println("\n1. Open a new account \n 2. Search by Account number\n 3. Deposit the amount \n 4. Withdraw the amount \n 5.Exit");
            System.out.println("\nEnter your choice:");
             int ch = sc.nextInt();
            switch (ch) {
                case 1:
                   C.openAccount();
                    break;
                case 2:
                    C.showAccount();
                    break;
                case 3:
                    C.deposit();
                    break;
                case 4:
                    C.withdrawal();
                    break;
                case 5:
                    System.out.println("See you soon...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
}
