import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl { // Changes class name to BorrowBookControl
	
	private BorrowBookUI ui; //Created instance of BorrowBookUI Class
	
	private Library library; //Created instance of Library Class
	private Member member; //Created instance of Member Class
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; // Changed enum name to ControlState
	private ControlState state; // Created instance of ControlState
	
	private List<book> PENDING;
	private List<loan> COMPLETED;
	private Book book; //Created instance of Book Class
	
	
	public BorrowBookControl() {
		this.library = library.INSTANCE();
		state = ControlState.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.BorrowBookUI = ui;
		ui.setState(BorrowBookUI.UIState.READY); //Set method to setState
		state = ControlState.READY;		
	}

		
	public void swiped(int memberId) { //Modified function to swiped
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.Member(memberId);
		if (member == null) {
			ui.display("Invalid memberId");
			return;
		}
		if (library.memberCanBorrow(member)) { // Changed method to memberCanBorrow
			PENDING = new ArrayList<>();
			ui.setState(BorrowBookUI.UIState.SCANNING);
			state = ControlState.SCANNING; }
		else 
		{
			ui.display("Member cannot borrow at this time");
			ui.setState(BorrowBookUI.UIState.RESTRICTED); }}
	
	
	public void scanned(int bookId) { //Modified function to scanned
		book = null;
		if (!state.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.book(bookId);
		if (book == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!book.available()) { //Modified function to available()
			ui.display("book cannot be borrowed");
			return;
		}
		PENDING.add(book);
		for (pendingBook : PENDING) { //Changed variable to pendingBook
			ui.display(book.toString());
		}
		if (library.loansRemainingForMember(member) - PENDING.size() == 0) { // Changed method to loansRemainingForMember
			ui.display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void complete() { //Modified function to complete
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			ui.display("\nFinal Borrowing List");
			for (Book book : PENDING) {
				ui.display(book.toString());
			}
			COMPLETED = new ArrayList<loan>();
			ui.setState(BorrowBookUI.UIState.FINALISING);
			state = ControlState.FINALISING;
		}
	}


	public void commitLoans() { //Modified function to commitLoans
		if (!state.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowbookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (Book book : PENDING) {
			Loan loan = library.issueLoan(book, member); //Method to issueLoan
			COMPLETED.add(LOAN);			
		}
		ui.display("Completed Loan Slip");
		for (Loan loan : COMPLETED) {
			ui.display(loan.toString());
		}
		ui.setState(BorrowBookUI.UIState.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UIState.CANCELLED);
		state = ControlState.CANCELLED;
	}
	
	
}
