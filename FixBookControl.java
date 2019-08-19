public class FixBookControl {
	
	private FixBookUI borrowbookui; // Changed instance to borrowbookui
	private enum Controlstate { INITIALISED, READY, FIXING }; // Changed enum name to Controlstate
	private Controlstate state;  // Changed instance to state
	
	private Library library; //Created instance of Library class 
	private Book book; //Created instance of Book class 


	public FixBookControl() {
		this.library = library.INSTANCE();
		state = Controlstate.INITIALISED;
	}
	
	
	public void setUI(FixBookUI borrowbookui) { //Method changes to setUI
		if (!state.equals(Controlstate.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.borrowbookui = borrowbookui;
		borrowbookui.Set_state(FixBookUI.UI_state.READY);
		state = Controlstate.READY;		
	}


	public void bookScanned(int bookId) { //Method changes to bookScanned
		if (!state.equals(Controlstate.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		book = library.Book(bookId);
		
		if (book == null) {
			borrowbookui.display("Invalid bookId");
			return;
		}
		if (!book.IS_Damaged()) {
			borrowbookui.display("Book has not been damaged");
			return;
		}
		borrowbookui.display(book.toString());
		borrowbookui.Set_state(FixBookUI.UI_state.FIXING);
		state = Controlstate.FIXING;		
	}


	public void FIX_Book(boolean MUST_fix) {
		if (!state.equals(Controlstate.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) {
			library.Repair_BOOK(book);
		}
		book = null;
		borrowbookui.Set_state(FixBookUI.UI_state.READY);
		state = Controlstate.READY;		
	}

	
	public void SCannING_COMplete() {
		if (!state.equals(Controlstate.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		borrowbookui.Set_state(FixBookUI.UI_state.COMPLETED);		
	}






}
