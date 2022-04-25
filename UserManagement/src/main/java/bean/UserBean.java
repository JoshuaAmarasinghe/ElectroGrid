package bean;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserBean {

	private Integer userId;
	private String accountNo;
	private String name;
	private String address;
	private String NIC;
	private String email;
	private String phone;
	private String password;
	private String user_role;

	public UserBean(Integer userId, String accountNo, String name, String address, String NIC, String email, String phone, String password, String user_role) {
	}

	public UserBean() {
		super();
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_role() {
		return user_role;
	}
	
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	
	public void convertStringToJSONInsert(String userData) {
        //convert string to JSON object and assign to variables in the class
        JsonObject UserObj = new JsonParser().parse(userData).getAsJsonObject();
        setAccountNo(UserObj.get("accountNo").getAsString());
        setName(UserObj.get("name").getAsString());
        setAddress(UserObj.get("address").getAsString());
        setNIC(UserObj.get("NIC").getAsString());
        setEmail(UserObj.get("email").getAsString());
        setPhone(UserObj.get("phone").getAsString());
        setPassword(UserObj.get("password").getAsString());
        setUser_role(UserObj.get("user_role").getAsString());
    }
	
	public void convertStringToJSONUpdateUser (String userData) {
		//convert string to JSON object and assign to variables in the class
        JsonObject UserObj  = new JsonParser().parse(userData).getAsJsonObject();
        setUserId(UserObj.get("userId").getAsInt());
        setAccountNo(UserObj.get("accountNo").getAsString());
        setName(UserObj.get("name").getAsString());
        setAddress(UserObj.get("address").getAsString());
        setNIC(UserObj.get("NIC").getAsString());
        setEmail(UserObj.get("email").getAsString());
        setPhone(UserObj.get("phone").getAsString());
	}

	public void convertStringToJSONlogin (String userData) {
		//convert string to JSON object and assign to variables in the class
        JsonObject UserObj  = new JsonParser().parse(userData).getAsJsonObject();
        setEmail(UserObj.get("email").getAsString());
        setPassword(UserObj.get("password").getAsString());
	}
}
