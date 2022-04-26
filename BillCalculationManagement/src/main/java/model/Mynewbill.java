package model;

import java.sql.*; 

public class Mynewbill {
	
	//A common method to connect to the DB
	
		public Connection connect(){
		    	
		        //database connection details
		        String dbDriver = "com.mysql.jdbc.Driver";
		        String dbURL = "jdbc:mysql://localhost:3306/";
		        String dbName = "ElectroGrid";
		        String dbUsername = "root";
		        String dbPassword = "";
		        
		        Connection conn = null;
		        
		        try {
		        	//connecting the database
		        	Class.forName(dbDriver);
		        	conn = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPassword);
		        	
		        	//if successfully connected this will be printed in the terminal
		        	System.out.print("Database connected successfully");
		        } catch(Exception e) {
		        	e.printStackTrace();
		        }
	        
	        return conn;
	    }
		public String insertMynewbill(String billid, String consumerid, String expiredate, String issuingdate, String unitnumber, String billamount)
		{
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for inserting."; }
				 
				 // create a prepared statement
				 
				 String query = " insert into bill (`billid`,`consumerid`,`expiredate`,`issuingdate`,`unitnumber`,`billamount`)" + " values (?, ?, ?, ?, ?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 
				 preparedStmt.setString(1, billid);
				 preparedStmt.setString(3, consumerid);
				 preparedStmt.setString(4, expiredate);
				 preparedStmt.setString(5, issuingdate);
				 preparedStmt.setString(6, unitnumber);
				 preparedStmt.setString(7, billamount);
				
				 
				 // execute the statement
				 
				 preparedStmt.execute();
				 con.close();
				 output = "Inserted successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Inserted successfully";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}
			public String readMynewbill()
			 {
				String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for reading."; }
					 
					 // Prepare the html table to be displayed
					 
					 output = "<table border='1'><tr><th>USER ID</th><th>PRE BILL NO</th>" +"<th>AMOUNT</th>" +
							 "<th>PAYED AMOUNT</th>" +"<th>BALANCE</th>" +"<th>PAYED NEW BILL NO</th>" +
							 "<th>Update</th>"+"<th>Remove</th></tr>";
				
					 String query = "select * from bill";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 // iterate through the rows in the result set
					 
					 while (rs.next())
					 {
						 String billid = Integer.toString(rs.getInt("billid"));
						 String consumerid = rs.getString("consumerid");
						 String expiredate = rs.getString("expiredate");
						 String issuingdate = rs.getString("issuingdate");
						 String unitnumber = rs.getString("unitnumber");
						 String billamount = rs.getString("billamount");
						
			
						 
						 // Add into the html table
						 
						 output += "<td>" + billid + "</td>";
						 output += "<td>" + consumerid + "</td>";
						 output += "<td>" + expiredate + "</td>";
						 output += "<td>" + issuingdate + "</td>";
						 output += "<td>" + unitnumber + "</td>";
						 output += "<td>" + billamount + "</td>";
						
						 
						 
						 // buttons
						 
						 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						 + "<td><form method='post' action='feed.jsp'>"
						 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						 + "<input name='id' type='hidden' value='" + billid + "'>" + "</form></td></tr>";
					 }
					 con.close();
					 
					 // Complete the html table
					 
					 output += "</table>";
				 }
				 catch (Exception e)
				 {
					 output = "Error while reading the .";
					 System.err.println(e.getMessage());
				 }
				 return output;
			 }
			
			
			public String updateMynewbill(String billid, String consumerid, String expiredate, String issuingdate, String unitnumber, String billamount)
			 {
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for updating."; }
					 
					 // create a prepared statement
					 
					 String query = "UPDATE bill SET consumerid=?,expiredate=?,issuingdate=?,unitnumber=?,billamount=? WHERE billid=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 
					 
					 preparedStmt.setString(1, consumerid);
					 preparedStmt.setString(2, expiredate);
					 preparedStmt.setString(3, issuingdate);
					 preparedStmt.setString(4, unitnumber);
					 preparedStmt.setString(5, billamount);
					 preparedStmt.setInt(6, Integer.parseInt(billid));
				
					 
					 // execute the statement
					 
					 preparedStmt.execute();
					 con.close();
					 output = "Updated successfully";
				 }
				 catch (Exception e)
				 {
					 output = "Error while updating the payment.";
					 System.err.println(e.getMessage());
				 }
				 return output;
			}
			
			public String deleteBill(String billid)
			{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for deleting."; }
					 // create a prepared statement
					 String query = "delete from bill where billid=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(billid));
					 // execute the statement
					 preparedStmt.execute();
					 
					 con.close();
					 output = "Deleted successfully";
				 }
				 catch (Exception e)
				 {
					 output = "Error while deleting the item.";
					 System.err.println(e.getMessage());
				 }
				 return output;
			 }

}