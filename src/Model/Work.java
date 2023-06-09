package Model;

public class Work {
	
	private String user_id;
	private String book_id;
	private String start_date;
	private String end_date;
	private String status;
	private int total;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public Work() {
		
	}
	
	public Work(String user_id, String book_id, String start_date, String end_date, String status, int total) {
		super();
		this.user_id = user_id;
		this.book_id = book_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.total = total;
	}
	
	
}
