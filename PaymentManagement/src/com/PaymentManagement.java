package com;
import model.PaymentModel;







import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/Payment")
public class PaymentManagement {
	PaymentModel payment = new PaymentModel();
	
	//add service
	

		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String addPayment(
				                 @FormParam("CardType") String CardType,
				                 @FormParam("CardNumber") String CardNumber,
				                 @FormParam("CardHolderName") String CardHolderName,
				                 @FormParam("CVC") String CVC,
				                 @FormParam("CardExpireDate") String CardExpireDate,
				                 @FormParam("PaymentDate") String PaymentDate,
				                 @FormParam("BillID") int BillID)
		{
			
			if(CardType.isEmpty()||CardNumber.isEmpty()||CardHolderName.isEmpty()||CVC.isEmpty()||CardExpireDate.isEmpty()||PaymentDate.isEmpty())
			{
				 return "Fields must be filled out";
			}
			else if(CardNumber.length()!=16) {
				 return "Card Number length must be 16 characters long";
			 }
			else if(CVC.length()!=3)
			{
				 return "CVC length must be 3 characters long";
			}
			else if(!CardType.matches("^[a-z A-Z]*$"))
			{
				return "Can't use numbers on Card Type feild";
			}
			else if(!CardNumber.matches("[0-9]+"))
			{
				return "Can't use Characters on Card Number feild.."
						+ "Must be Integer";
			}
			else if(!CVC.matches("[0-9]+"))
			{
				return "Can't use Characters on CVC feild.."
						+ "Must be Integer";
			}
			else if(!CardExpireDate.matches("^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$"))
			{
				return "CardExpireDate did not match correct date format";
			}
			else if(!PaymentDate.matches("^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$"))
			{
				return "PaymentDate did not match correct date format";
			}
			
			
			
			String output = payment.addPayment(CardType, CardNumber, CardHolderName, CVC, CardExpireDate, PaymentDate, BillID);
			return output;
		}
		
		//view service
		
		
		@GET
		@Path("/get")
	    @Produces(MediaType.TEXT_HTML)
	    public String getAllPatmentEntry(){
	        return this.payment.getAllPayment();
	    }
		
		@GET
		@Path("/getById/{UserID}")
		@Produces(MediaType.TEXT_HTML)
		public String getPaymentById(@PathParam("UserID") int UserID) {
			return this.payment.getPaymentByUser(UserID);
		}
		
		
		//update service
		@PUT
		@Path("/update/payment/{PaymentID}")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePaymentById(@PathParam("PaymentID") int PaymentID ,
				 @FormParam("CardType") String CardType,
				 @FormParam("CardNumber") String CardNumber,
				 @FormParam("CardHolderName") String CardHolderName,
				 @FormParam("CVC") String CVC,
				 @FormParam("CardExpireDate") String CardExpireDate,
				 @FormParam("PaymentDate") String PaymentDate,
				 @FormParam("BillID") int BillID ) {
			
			
			
			if(CardType.isEmpty()||CardNumber.isEmpty()||CardHolderName.isEmpty()||CVC.isEmpty()||CardExpireDate.isEmpty()||PaymentDate.isEmpty())
			{
				 return "Fields must be filled out";
			}
			else if(CardNumber.length()!=16) {
				 return "Card Number length must be 16 characters long";
			 }
			else if(CVC.length()!=3)
			{
				 return "CVC length must be 3 characters long";
			}
			else if(!CardType.matches("^[a-z A-Z]*$"))
			{
				return "Can't use numbers on Card Type feild";
			}
			else if(!CardNumber.matches("[0-9]+"))
			{
				return "Can't use Characters on Card Number feild.."
						+ "Must be Integer";
			}
			else if(!CVC.matches("[0-9]+"))
			{
				return "Can't use Characters on CVC feild.."
						+ "Must be Integer";
			}
			else if(!CardExpireDate.matches("^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$"))
			{
				return "CardExpireDate did not match correct date format";
			}
			else if(!PaymentDate.matches("^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$"))
			{
				return "PaymentDate did not match correct date format";
			}
			
		return this.payment.updatePayment(PaymentID, CardType, CardNumber, CardHolderName, CVC, CardExpireDate, PaymentDate, BillID);
		
		}
		
		
		//delete service
		@DELETE
		@Path("/delete/payment/{PaymentID}")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String DeleteOrder(
		@PathParam ("PaymentID") int PaymentID )
		{

		//Read the value from the element <AppID>
		String output = payment.DeletePayment(PaymentID);
		        return output;



		}
		

}
