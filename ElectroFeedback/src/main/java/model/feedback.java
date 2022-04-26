package model;

import java.sql.*; 

public class feedback {
	
	//A common method to connect to the DB
	
		private Connection connect()
		 {
			 Connection con = null;
			 try
			 {
				 Class.forName("com.mysql.jdbc.Driver");
			
				 //Provide the correct details: DBServer/DBName, username, password
				 
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/feedbackdb", "root", "root");
			 }
				 catch (Exception e)
				 {e.printStackTrace();}
				 return con;
		 }
				public String inserFeedback(String userid, String previousbillno, String amount, String payedamount, String balance, String newbillno, String newamount, String total, String comment)
		{
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for inserting."; }
				 
				 // create a prepared statement
				 
				 String query = " insert into feedback (`Nuserid`,`Npreviousbillno`,`Namount`,`Npayedamount`,`Nbalance`,`Nnewbillno`,`Nnewamount`,`Ntotal`,`Ncomment`)" + " values (?, ?, ?, ?, ?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, userid);
				 preparedStmt.setString(3, previousbillno);
				 preparedStmt.setString(4, amount);
				 preparedStmt.setString(5, payedamount);
				 preparedStmt.setString(6, balance);
				 preparedStmt.setString(7, newbillno);
				 preparedStmt.setString(8, newamount);
				 preparedStmt.setString(9, total);
				 preparedStmt.setString(10, comment);
				 
				 
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
			public String readFeedback()
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
							 "<th>NEW AMOUNT</th>" +"<th>TOTAL</th>" +"<th>COMMENT</th>" +"<th>Update</th>"+"<th>Remove</th></tr>";
				
					 String query = "select * from feedback";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 // iterate through the rows in the result set
					 
					 while (rs.next())
					 {
						 String id = Integer.toString(rs.getInt("id"));
						 String Nuserid = rs.getString("userid");
						 String Npreviousbillno = rs.getString("previousbillno");
						 String Namount = rs.getString("amount");
						 String Npayedamount = rs.getString("payedamount");
						 String Nbalance = rs.getString("balance");
						 String Nnewbillno = rs.getString("newbillno");
						 String Nnewamount = rs.getString("newamount");
						 String Ntotal = rs.getString("total");
						 String Ncomment = rs.getString("comment");
			
						 
						 // Add into the html table
						 
						 output += "<td>" + Nuserid + "</td>";
						 output += "<td>" + Npreviousbillno + "</td>";
						 output += "<td>" + Namount + "</td>";
						 output += "<td>" + Npayedamount + "</td>";
						 output += "<td>" + Nbalance + "</td>";
						 output += "<td>" + Nnewbillno + "</td>";
						 output += "<td>" + Nnewamount + "</td>";
						 output += "<td>" + Ntotal + "</td>";
						 output += "<td>" + Ncomment + "</td>";
						 
						 
						 // buttons
						 
						 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						 + "<td><form method='post' action='feedback.jsp'>"
						 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						 + "<input name='id' type='hidden' value='" + id + "'>" + "</form></td></tr>";
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
			 public String updateFeedback(String id,String userid, String previousbillno, String amount, String payedamount, String balance, String newbillno, String newamount, String total, String comment)
			 {
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for updating."; }
					 
					 // create a prepared statement
					 
					 String query = "UPDATE feedback SET Nuserid=?,Npreviousbillno=?,Namount=?,Npayedamount=?,Nbalance=?,Nnewbillno=?,Nnewamount=?,Ntotal=?,Ncomment=?WHERE id=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 
					 preparedStmt.setInt(1, Integer.parseInt(id));
					 preparedStmt.setString(2, userid);
					 preparedStmt.setString(3, previousbillno);
					 preparedStmt.setString(4, amount);
					 preparedStmt.setString(5, payedamount);
					 preparedStmt.setString(6, balance);
					 preparedStmt.setString(7, newbillno);
					 preparedStmt.setString(8, newamount);
					 preparedStmt.setString(9, total);
					 preparedStmt.setString(10, comment);
					 
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
			public String deleteFeedback(String id)
			{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for deleting."; }
					 // create a prepared statement
					 String query = "delete from feedback where id=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(id));
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
