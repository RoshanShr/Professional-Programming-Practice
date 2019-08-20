public class ReturnBookControl {

	private ReturnBookUI userInterface; //Changed instance variable name
	private enum ControlState { INITIALISED, READY, INSPECTING }; //enum name should start with uppercase and be in CamelBack
	private ControlState state; //Changed Class and instsnce variable
	
	private Library library; //Class name should start with uppercase and variable name should start with lowercase and be in camelBack
	private Loan currentLoan; //Class name should start with uppercase and variable name should start with lowercase and be in camelBack
	

	public ReturnBookControl() {
		this.library = library.getInstance(); //Variable name should start with lowercase and be in camelBack and Changed method name to verb starting with lowercase and in camelBack
		state = ControlState.getInitialized; //Variable name should start with lowercase and be in camelBack and Changed method name to verb starting with lowercase and in camelBack
	}
	
	
	public void setUserInterface(ReturnBookUI userInterface) { //Changed method name to verb starting with lowercase and in camelBack
		if (!state.equals(ControlState.getInitialized)) { //Changed instance sTaTe to state
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.userInterface = userInterface;
		userInterface.setState(ReturnBookUI.UI_STATE.READY); //Changed method name to verb starting with lowercase and in camelBack
		state = ControlState.READY;		
	}


	public void getBookScanned(int bookId) { //Changed method name to verb starting with lowercase and in camelBack
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		Book curBook = library.Book(bookId); //Changed instance lIbRaRy to library and changed variable name BOOK_ID to bookId and class name book to Book
		
		if (curBook == null) { //Changed instance CUR_book to curBook
			userInterface.display("Invalid Book Id");
			return;
		}
		if (!curBook.onLoan()) { //Changed method name to verb starting with lowercase and in camelBack
			userInterface.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	//Changed method name to verb starting with lowercase and in camelBack
		double overDueFine = 0.0; //Changed variable name to a meaningful word
		if (currentLoan.isOverDue()) { //Changed method name to verb starting with lowercase and in camelBack
			overDueFine = library.calculateOverDueFine(currentLoan); //Changed method name to verb starting with lowercase and in camelBack
		}
		userInterface.display("Inspecting");
		userInterface.display(CUR_book.toString());
		userInterface.display(currentLoan.toString());
		
		if (currentLoan.overDue()) { //Changed method name to verb starting with lowercase and in camelBack
			userInterface.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		userInterface.setState(ReturnBookUI.UI_STATE.INSPECTING);
		state = ControlState.INSPECTING;		
	}


	public void ccanningComplete() { //Changed method name to verb starting with lowercase and in camelBack
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		userInterface.setState(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) {//Changed method name to verb starting with lowercase and in camelBack
		if (!state.equals(ControlState.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged); //Changed method name to verb starting with lowercase and in camelBack
		currentLoan = null; //Changed variable name to a meaningful word
		userInterface.setState(ReturnBookUI.UI_STATE.READY);
		state = ControlState.READY;				
	}


}
