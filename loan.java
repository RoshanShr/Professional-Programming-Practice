import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // Changed class name to uppercase
	
	public static enum LoanState { CURRENT, OVERDUE, DISCHARGED }; // Changed enum name to LoanState
	
	private int loanId; // Changed variable name to loanId
	private Book book; // Change class and instance of Book class
	private Member member; // Change class and instance of Member class
	private Date date; // Changed the instance of Date
	private LoanState state;

	
	public Loan(int loanId, Book book, member member, Date dueDate) { // Changed constructor name to Uppercase
		this.loanId = loanId;
		this.book = book;
		this.member = member;
		this.date = dueDate;
		this.state = LoanState.CURRENT;
	}

	
	public void checkOverDue() {
	    if (state == LoanState.CURRENT &&
			Calendar.getInstance().getDate().after(date)) { // Changed method to getInstance and getDate
			this.state = LoanState.overDue;			
		}
	}

	
	public boolean overDue() {
		return state == LoanState.overDue;
	}

	
	public integer getLoanId() { // Changed method name to getLoanId 
		return loanId;
	}


	public Date getDueDate() {
		return date;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:").append(ID).append("\n")
		  .append("Borrower").append(member.getId()).append(" : ")
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n")
		  .append("Book").append(book.getLoanId()).append(" : " )
		  .append(book.getTitle()).append("\n")
		  .append("DueDate: ").append(sdf.format(date)).append("\n")
		  .append("State: ").append(state);		
		return sb.toString();
	}


	public Member getMember() { // Get getMember from the Member Class
		return member;
	}


	public Book getBook() { // Get getBook from the Book Class
		return book;
	}


	public void discharge() { //Changed the function to discharge
		state = LoanState.DISCHARGED;		
	}

}
