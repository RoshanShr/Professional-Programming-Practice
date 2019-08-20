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

	public Integer id() {	//Change the method name to correct order(id)
		return id;	//Fixed the variable name(id)
	}

	public String title() {
		return title;	//Fixed the variable name(title)
	}


	
	public boolean available() {	//Change the method name to correct order(available)
		return state == state.AVAILABLE;
	}

	
	public boolean onLoan() {	//Change the method name to correct order(onLoan)
		return state == state.ON_LOAN;
	}

	
	public boolean isDamaged() {	//Change the method name to correct order(isDamaged)
		return state == state.DAMAGED;
	}

	
	public void borrow() {	//Change the method name to correct order(borrow)
		if (state.equals(state.AVAILABLE)) {
			state = state.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void return(boolean DAMAGED) {	//Change the method name to correct order(return)
		if (state.equals(state.ON_LOAN)) {
			if (DAMAGED) {
				state = state.DAMAGED;
			}
			else {
				state = state.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void repair() {	//Change the method name to correct order(repair)
		if (state.equals(state.DAMAGED)) {
			state = state.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
