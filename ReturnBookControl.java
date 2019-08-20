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
		if (!sTaTe.equals(ControlState.getInitialized)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.userInterface = userInterface;
		userInterface.setState(ReturnBookUI.UI_STATE.READY); //Changed method name to verb starting with lowercase and in camelBack
		sTaTe = ControlState.READY;		
	}


	public void getBookScanned(int Book_ID) { //Changed method name to verb starting with lowercase and in camelBack
		if (!sTaTe.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book CUR_book = lIbRaRy.Book(Book_ID);
		
		if (CUR_book == null) {
			userInterface.display("Invalid Book Id");
			return;
		}
		if (!CUR_book.On_loan()) {
			userInterface.display("Book has not been borrowed");
			return;
		}		
		CurrENT_loan = lIbRaRy.getLoanByBookId(Book_ID);	//Changed method name to verb starting with lowercase and in camelBack
		double Over_Due_Fine = 0.0;
		if (CurrENT_loan.isOverDue()) { //Changed method name to verb starting with lowercase and in camelBack
			Over_Due_Fine = lIbRaRy.CalculateOverDueFine(CurrENT_loan);
		}
		userInterface.display("Inspecting");
		userInterface.display(CUR_book.toString());
		userInterface.display(CurrENT_loan.toString());
		
		if (CurrENT_loan.OVer_Due()) {
			userInterface.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		}
		userInterface.setState(ReturnBookUI.UI_STATE.INSPECTING);
		sTaTe = ControlState.INSPECTING;		
	}


	public void Scanning_Complete() {
		if (!sTaTe.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		userInterface.setState(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void Discharge_loan(boolean isDamaged) {
		if (!sTaTe.equals(ControlState.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		lIbRaRy.Discharge_loan(CurrENT_loan, isDamaged);
		CurrENT_loan = null;
		userInterface.setState(ReturnBookUI.UI_STATE.READY);
		sTaTe = ControlState.READY;				
	}


}
