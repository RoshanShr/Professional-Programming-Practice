public class FixBookControl {
	
	private FixBookUI ui; // Changed instance to ui
	private enum ControlState { INITIALISED, READY, FIXING }; // Changed enum name to ControlState
	private ControlState state;  // Changed instance to state
	
	private Library library; //Created instance of Library class 
	private Book book; //Created instance of Book class 


	public FixBookControl() {
		this.library = library.getInstance(); // Changed the method to getInstance
		state = ControlState.INITIALISED;
	}
	
	
	public void setUI(FixBookUI ui) { //Method changes to setUI
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.Set_state(FixBookUI.UiState.READY);
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) { //Method changes to bookScanned
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		book = library.Book(bookId);
		
		if (book == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!book.isDamaged()) { // Changed method to isDamaged
			ui.display("Book has not been damaged");
			return;
		}
		ui.display(book.toString());
		ui.setState(FixBookUI.UiState.FIXING); //Changed method to setState
		state = ControlState.FIXING;		
	}


	public void fixBook(boolean fixBookId) { // Changed method to fixBook and variable to fixBookId
		if (!state.equals(ControlState.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fixBookId) {
			library.repairBook(book); // Changed method to repairBook
		}
		book = null;
		ui.setState(FixBookUI.UiState.READY);
		state = ControlState.READY;		
	}

	
	public void scanningComplete() { // Changed method to scanningComplete
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(FixBookUI.UiState.COMPLETED); // Changed method to setState		
	}






}
