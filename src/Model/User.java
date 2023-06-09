package Model;


public class User {
	private String id;
	private String name;
	private String birthDay;
	private String phone;
	private String address;
	private String passWord;
	private int position;
	private int money;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public User() {
		
	}
	
	public User(String id,String name,String da,String phone,String add,String pass) {
		this.id=id;
		this.name=name;
		this.birthDay=da;
		this.phone=phone;
		this.address=add;
		this.passWord=pass;
		this.money=0;
	}
	
	public User(String id,String name,String da,String phone,String add,String pass,int pos) {
		this.id=id;
		this.name=name;
		this.birthDay=da;
		this.phone=phone;
		this.address=add;
		this.passWord=pass;
		this.position= pos;
		this.money=0;
	}
	
	public User(String id) {
		this.id=id;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	

}
