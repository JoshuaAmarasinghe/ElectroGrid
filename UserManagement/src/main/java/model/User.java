package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.UserBean;
import util.DBConnection;

public class User {

	Connection con = null;

	public User() {
		con = DBConnection.connect();
	}

	//Insert
	public String insertUser(UserBean userBean) {

		String output = "";

		try {
			Connection con = DBConnection.connect();
			
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = "insert into user(`accountNo`,`name`,`address`,`NIC`,`email`,`phone`,`password`,`user_role`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);

			//binding values
			preparedStmt.setString(1, userBean.getAccountNo());
			preparedStmt.setString(2, userBean.getName());
			preparedStmt.setString(3, userBean.getAddress());
			preparedStmt.setString(4, userBean.getNIC());
			preparedStmt.setString(5, userBean.getEmail()); 
			preparedStmt.setString(6, userBean.getPhone());
			preparedStmt.setString(7, userBean.getPassword()); 
			preparedStmt.setString(8, "Customer");
			
			//Insert Validation
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";

		} catch (SQLException e) {
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//Read
	public String readUserDetails() {
		
		String output = "";

		try{
			Connection con = DBConnection.connect();
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Account Number</th>"
					+"<th>Name</th><th>Address</th><th>NIC</th>"
					+ "<th>Email</th>"
					+ "<th>Phone</th>"
					+ "<th>Password</th>"
					+ "<th>User Role</th>";
			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()){
				String accountNo = rs.getString("accountNo");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String NIC = rs.getString("NIC");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String password = rs.getString("password");
				String user_role = rs.getString("user_role");

				// Add a row into the html table
				output += "<tr><td>" + accountNo + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + email + "</td>"; 
				output += "<td>" + phone + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + user_role + "</td>";
			}

			con.close();
			// Complete the html table
			output += "</table>";
		}

		catch (Exception e){
			output = "Error while reading the user details"
					+ ".";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//Fetch User Details
	public String getUserDetails(String userID){
		String output = "";

		try{
			Connection con = DBConnection.connect();
			if (con == null){
				return "Error while connecting to the database for reading";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Account No</th>"
					+"<th>Name</th><th>NIC</th>"
					+ "<th>Email</th>"
					+ "<th>Phone</th>"
					+ "<th>Password</th>"
					+ "<th>Role</th>";
			String query = "select * from user where userId='"+userID+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()){ 
				String userId = Integer.toString(rs.getInt("userId"));
				String accountNo = rs.getString("accountNo");
				String name = rs.getString("name");
				String NIC = rs.getString("NIC");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String password = rs.getString("password");
				String user_role = rs.getString("user_role");
				// Add a row into the html table
				output += "<tr><td>" + accountNo + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + email + "</td>"; 
				output += "<td>" + phone + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + user_role + "</td>";
				// buttons
				output += "<input name='itemID' type='hidden' "
						+ " value='" + userId + "'>"
						+ "</form></td></tr>";
			}

			con.close();
			// Complete the html table
			output += "</table>";

		}

		catch (Exception e){
			output = "Error while reading the user details";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//Update Customer
	public String updateUser(UserBean userBean){
	    String output = "";

	    try{
			Connection con = DBConnection.connect();
		   if (con == null){
			   return "Error while connecting to the database for updating"; 
		   }
		   // create a prepared statement
		   String query = "UPDATE user SET accountNo=?,name=?,address=?,NIC=?,email=?,phone=? WHERE userId=? and user_role='Customer'";
		   PreparedStatement preparedStmt = con.prepareStatement(query);
		   
		   // binding values
		   	preparedStmt.setString(1, userBean.getAccountNo());
			preparedStmt.setString(2, userBean.getName());
			preparedStmt.setString(3, userBean.getAddress());
			preparedStmt.setString(4, userBean.getNIC());
			preparedStmt.setString(5, userBean.getEmail()); 
			preparedStmt.setString(6, userBean.getPhone());
			preparedStmt.setInt(7, userBean.getUserId());

		    // execute the statement		   
		    int st = preparedStmt.executeUpdate();
			 
			if (st>0) {
				output = "User Details Updated Sucessfully";
			}else {
				output = "User not found corresponding to the specified User ID";
			}

		   con.close();
		   
		}
	    catch (Exception e){
		   output = "Error while updating the user";
		   System.err.println(e.getMessage());
		}

	    return output;
	}
		
	//Update Admin
		public String updateAdmin(UserBean userBean){
		    String output = "";

		    try{
				Connection con = DBConnection.connect();
			   if (con == null){
				   return "Error while connecting to the database for updating"; 
			   }
			   // create a prepared statement
			   String query = "UPDATE user SET accountNo=?,name=?,address=?,NIC=?,email=?,phone=? WHERE userId=? and user_role='Admin'";
			   PreparedStatement preparedStmt = con.prepareStatement(query);
			   
			   // binding values
			   	preparedStmt.setString(1, userBean.getAccountNo());
				preparedStmt.setString(2, userBean.getName());
				preparedStmt.setString(3, userBean.getAddress());
				preparedStmt.setString(4, userBean.getNIC());
				preparedStmt.setString(5, userBean.getEmail()); 
				preparedStmt.setString(6, userBean.getPhone());
				preparedStmt.setInt(7, userBean.getUserId());

			    // execute the statement		   
			    int st = preparedStmt.executeUpdate();
				 
				if (st>0) {
					output = "User Details Updated Sucessfully";
				}else {
					output = "User not found corresponding to the specified User ID";
				}

			   con.close();
			   
			}
		    catch (Exception e){
			   output = "Error while updating the user";
			   System.err.println(e.getMessage());
			}

		    return output;
		}
		
	//Delete 
	public String deleteUser(UserBean userBean){
		String output = "";

		try {
			Connection con = DBConnection.connect();
			if (con == null){
				 return "Error while connecting to the database for deleting."; 
			}
			 
			// create a prepared statement
			String query = "delete from user where userId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			// binding values
			preparedStmt.setInt(1, userBean.getUserId());
			
			// execute the statement
			int st = preparedStmt.executeUpdate();
			
			if (st>0) {
				output = "User Deleted Sucessfully";
			}else {
				output = "User not found corresponding to the specified User ID";
			}
			
			con.close();
			 
		}

		 catch (Exception e){
			 output = "Error while deleting the user";
			 System.err.println(e.getMessage());
		}
		
		 return output;
	}

	//login
	public String login(UserBean userBean) {
		String output = " ";
		
		try {
			Connection con = DBConnection.connect();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "SELECT * FROM user WHERE email=? and password=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, userBean.getEmail());
			preparedStmt.setString(2, userBean.getPassword());
			ResultSet results = preparedStmt.executeQuery();
			
			if (!results.isBeforeFirst() ) {        
			    output = "Invalid User Credentials !!!!";
			}

			// iterate through the rows in the result set
			while (results.next()) {
				output = "Valid User, Welcome "+results.getString("name")+" , User Role: "+results.getString("user_role");				   
			}

		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}