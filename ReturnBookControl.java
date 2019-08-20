public class ReturnBookControl {

	private ReturnBookUI ui;
	private enum ControlState { INITIALISED, READY, INSPECTING }; //Changed the enusm to ControlState
	private ControlState state;
	
	private Library library; // Changed variable to library
	private Loan currentLoan; // Changed variable to currentLoan
	

	public ReturnBookControl() {
		this.library = library.getInstance(); // Change method to getInstance
		state = ControlState.INITIALISED;
	}
	
	
	public void setUI(ReturnBookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(ReturnBookUI.UIState.READY); //Change method to setState
		
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) { // updated method to bookScanned
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		Book book = library.Book(bookId);
		
		if (book == null) {
			ui.display("Invalid Book Id");
			return;
		}
		if (!book.onLoan()) { // updated to onLoan
			ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.loanByBookId(bookId);	// Changed method to loanByBookId
		double overDueFine = 0.0;
		if (currentLoan.overDue()) { // Change method to overDue
			overDueFine = library.CalculateOverDueFine(currentLoan);
		}
		ui.display("Inspecting");
		ui.display(book.toString());
		ui.display(currentLoan.toString());
		
		if (currentLoan.overDue()) {
			ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		ui.setState(ReturnBookUI.UIState.INSPECTING);
		state = ControlState.INSPECTING;		
	}


	public void scanningComplete() { // Change method to scanningComplete
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(ReturnBookUI.UIState.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) { // Change method to dischargeLoan
		if (!state.equals(ControlState.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.setState(ReturnBookUI.UIState.READY);
		state = ControlState.READY;				
	}


}
