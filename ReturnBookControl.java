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


	public void Book_scanned(int Book_ID) {
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book CUR_book = lIbRaRy.Book(Book_ID);
		
		if (CUR_book == null) {
			Ui.display("Invalid Book Id");
			return;
		}
		if (!CUR_book.On_loan()) {
			Ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = lIbRaRy.LOAN_BY_BOOK_ID(Book_ID);	
		double Over_Due_Fine = 0.0;
		if (currentLoan.OVer_Due()) {
			Over_Due_Fine = lIbRaRy.CalculateOverDueFine(currentLoan);
		}
		Ui.display("Inspecting");
		Ui.display(CUR_book.toString());
		Ui.display(currentLoan.toString());
		
		if (currentLoan.OVer_Due()) {
			Ui.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		}
		Ui.Set_State(ReturnBookUI.UI_STATE.INSPECTING);
		sTaTe = CONTROL_STATE.INSPECTING;		
	}


	public void Scanning_Complete() {
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		Ui.Set_State(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void Discharge_loan(boolean isDamaged) {
		if (!sTaTe.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		lIbRaRy.Discharge_loan(currentLoan, isDamaged);
		currentLoan = null;
		Ui.Set_State(ReturnBookUI.UI_STATE.READY);
		sTaTe = CONTROL_STATE.READY;				
	}


}
