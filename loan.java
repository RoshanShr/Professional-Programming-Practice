import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // Changed class name to uppercase
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // Changed enum name to LoanState
	
	private int loanId; // Changed variable name to loanId
	private book B;
	private member M;
	private Date date; // Changed the instance of Date
	private LoanState state;

	
	public Loan(int loanId, book book, member member, Date dueDate) { // Changed constructor name to Uppercase
		this.loanId = loanId;
		this.B = book;
		this.M = member;
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
		  .append("  Borrower ").append(M.GeT_ID()).append(" : ")
		  .append(M.Get_LastName()).append(", ").append(M.Get_FirstName()).append("\n")
		  .append("  Book ").append(B.getLoanId()).append(" : " )
		  .append(B.TITLE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member Member() {
		return M;
	}


	public book Book() {
		return B;
	}


	public void DiScHaRgE() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
