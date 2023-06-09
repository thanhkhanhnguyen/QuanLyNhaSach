package Model;

public class Book {
	private String id;
	private String name;
	private int authorId;
	private int publisherId;
	private int number;
	private int limitDay;
	private int price;
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
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getLimitDay() {
		return limitDay;
	}
	public void setLimitDay(int limitDay) {
		this.limitDay = limitDay;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Book() {
		
	}
	
	public Book(String id,String name,int authorid,int pubid,int num,int limitday,int price) {
		this.id=id;
		this.name=name;
		this.authorId=authorid;
		this.publisherId=pubid;
		this.number=num;
		this.limitDay=limitday;
		this.price=price;
	}
}
