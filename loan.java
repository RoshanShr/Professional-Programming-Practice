import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // Changed class name to uppercase
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // Changed enum name to LoanState
	
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
			Calendar.INSTANCE().Date().after(date)) {
			this.state = LoanState.OVER_DUE;			
		}
	}

	
	public boolean OVer_Due() {
		return state == LoanState.OVER_DUE;
	}

	
	public Integer getLoanId() { // Changed method name to getLoanId 
		return loanId;
	}


	public Date Get_Due_Date() {
		return date;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(member.GeT_ID()).append(" : ")
		  .append(member.Get_LastName()).append(", ").append(member.Get_FirstName()).append("\n")
		  .append("  Book ").append(B.getLoanId()).append(" : " )
		  .append(B.TITLE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member getMember() { // Get getMember from the Member Class
		return member;
	}


	public book getBook() { // Get getBook from the Book Class
		return book;
	}


	public void DiScHaRgE() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
