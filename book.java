import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {	//Fixed the order of class name
	
	//Changed the following variable names to right order
	private String title;
	private String athor;
	private String callNo;
	private int id;
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };	//Change the enum variable to right format(State)
	private State state;
	
	
	public Book(String author, String title, String callNo, int id) {	//Fixed the order of constructor name
		
		//Changed the following variable names to right order
		this.author = author;
		this.title = title;
		this.callNo = callNo;
		this.id = id;
		this.state = state.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(ID).append("\n")
		  .append("  Title:  ").append(title).append("\n")
		  .append("  Author: ").append(author).append("\n")
		  .append("  CallNo: ").append(callNo).append("\n")
		  .append("  State:  ").append(state);
		
		return sb.toString();
	}

	public Integer ID() {
		return ID;
	}

	public String TITLE() {
		return TITLE;
	}


	
	public boolean AVAILABLE() {
		return State == STATE.AVAILABLE;
	}

	
	public boolean On_loan() {
		return State == STATE.ON_LOAN;
	}

	
	public boolean IS_Damaged() {
		return State == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (State.equals(STATE.AVAILABLE)) {
			State = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
	}


	public void Return(boolean DAMAGED) {
		if (State.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				State = STATE.DAMAGED;
			}
			else {
				State = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	
	public void Repair() {
		if (State.equals(STATE.DAMAGED)) {
			State = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
	}


}
