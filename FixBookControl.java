public class FixBookControl {
	
	private FixBookUI UI;
	private enum Controlstate { INITIALISED, READY, FIXING }; // Changed enum name to Controlstate
	private Controlstate state;  // Changed instance to state
	
	private Library library; //Created instance of Library class 
	private book Cur_Book;


	public FixBookControl() {
		this.library = library.INSTANCE();
		state = Controlstate.INITIALISED;
	}
	
	
	public void setUI(FixBookUI ui) { //Method changes to setUI
		if (!state.equals(Controlstate.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.UI = ui;
		ui.Set_state(FixBookUI.UI_state.READY);
		state = Controlstate.READY;		
	}


	public void Book_scanned(int bookId) {
		if (!state.equals(Controlstate.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		Cur_Book = library.Book(bookId);
		
		if (Cur_Book == null) {
			UI.display("Invalid bookId");
			return;
		}
		if (!Cur_Book.IS_Damaged()) {
			UI.display("Book has not been damaged");
			return;
		}
		UI.display(Cur_Book.toString());
		UI.Set_state(FixBookUI.UI_state.FIXING);
		state = Controlstate.FIXING;		
	}


	public void FIX_Book(boolean MUST_fix) {
		if (!state.equals(Controlstate.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) {
			library.Repair_BOOK(Cur_Book);
		}
		Cur_Book = null;
		UI.Set_state(FixBookUI.UI_state.READY);
		state = Controlstate.READY;		
	}

	
	public void SCannING_COMplete() {
		if (!state.equals(Controlstate.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		UI.Set_state(FixBookUI.UI_state.COMPLETED);		
	}






}
