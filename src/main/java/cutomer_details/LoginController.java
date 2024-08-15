package cutomer_details;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
//import java.sql.*;

@SuppressWarnings("unused")
public  class LoginController {

	private static String url = "jdbc:postgresql://localhost:5432/tms";
	private static String user = "postgres";
	private static String pass = "1176";
	private static String createTable = "create table tmsuser2(id varchar(50), name varchar(50), pass varchar(50))";
	private static String addCustomer = "Insert into tmsuser2 values(?,?,?)";
	private static Connection con;
	
	public LoginController() {
		con = getConnection();
	}
	static Connection getConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,user,pass);
			if (con!=null) 
			{
				System.out.println("connected");
						
			}
			
			/*
			 * stat.execute(createTable); System.out.println("Table created Successfully");
			 */
		}catch(Exception e) {
			System.out.println("not connected "+e);
		}
		return con;
	}
	
	public void createTable() {
		try {
			Statement stat = con.createStatement();
			stat.executeUpdate(createTable);
			System.out.println("Table created Successfully");
			
		}catch(Exception e) {
			System.out.println("Table failed "+e);
		}
	}
	
	public  void addCustomer(Customer cus) {
		try {
			PreparedStatement p1 = con.prepareStatement(addCustomer);
			p1.setInt(1, cus.getCustomerId());
			p1.setString(2, cus.getCustomerName());
			p1.setString(3, cus.getCustomerPassword());
			p1.execute();
			System.out.println(cus+ "is added");
		} catch (Exception e) {
			
		}
	}

	
	/*
	 * public static void main(String[] args) { LoginController l =new
	 * LoginController(); l.createTable(); Customer c =new Customer(001, "vipin",
	 * "12345"); l.addCustomer(c); }
	 */	
	
}
