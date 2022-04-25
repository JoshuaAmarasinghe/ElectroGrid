package util;

public class UserValidation {
	
	public boolean registrationValidation(String accountNo, String name, String address, String NIC, String email, String phone, String password) {
		
		if(accountNo.isEmpty()||name.isEmpty()||address.isEmpty()||NIC.isEmpty()||email.isEmpty()||phone.isEmpty()||password.isEmpty()) {
			 return false;
		 }
		 else if(accountNo.length()!=10) {
			 return false;
		 }
		 else if(!NIC.matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")){
			 return false;
		 }
		 else if(!phone.matches("^(?:7|0|(?:\\+94))[0-9]{9,10}$")) {
			 return false;
		 }
		 else if(!password.matches("(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$)")) {
			 return false;
		 }
		 else if(!email.matches ("(^[A-Za-z0-9+_.-]+@(.+)$)")) {
			 return false;
	     }
		 else {
			 return true;
		 }
	}
	
public boolean userUpdateValidation(String accountNo, String name, String address, String NIC, String email, String phone) {
		
		if(accountNo.isEmpty()||name.isEmpty()||address.isEmpty()||NIC.isEmpty()||email.isEmpty()||phone.isEmpty()) {
			 return false;
		 }
		 else if(accountNo.length()!=10) {
			 return false;
		 }
		 else if(!NIC.matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")){
			 return false;
		 }
		 else if(!phone.matches("^(?:7|0|(?:\\+94))[0-9]{9,10}$")) {
			 return false;
		 }
		 else if(!email.matches ("(^[A-Za-z0-9+_.-]+@(.+)$)")) {
			 return false;
	     }
		 else {
			 return true;
		 }
	}
}