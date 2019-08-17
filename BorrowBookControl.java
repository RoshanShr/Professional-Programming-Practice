import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl { // Changes class name to BorrowBookControl
	
	private BorrowbookUI UI;
	
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
	

	public void setUI(BorrowbookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.UI = ui;
		ui.Set_state(BorrowbookUI.UI_state.READY);
		state = ControlState.READY;		
	}

		
	public void Swiped(int MEMMER_ID) {
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.MEMBER(MEMMER_ID);
		if (member == null) {
			UI.Display("Invalid memberId");
			return;
		}
		if (library.MEMBER_CAN_BORROW(member)) {
			PENDING = new ArrayList<>();
			UI.Set_state(BorrowbookUI.UI_state.SCANNING);
			state = ControlState.SCANNING; }
		else 
		{
			UI.Display("Member cannot borrow at this time");
			UI.Set_state(BorrowbookUI.UI_state.RESTRICTED); }}
	
	
	public void Scanned(int bookId) {
		book = null;
		if (!state.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.book(bookId);
		if (book == null) {
			UI.Display("Invalid bookId");
			return;
		}
		if (!book.AVAILABLE()) {
			UI.Display("book cannot be borrowed");
			return;
		}
		PENDING.add(book);
		for (book B : PENDING) {
			UI.Display(B.toString());
		}
		if (library.Loans_Remaining_For_Member(member) - PENDING.size() == 0) {
			UI.Display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			UI.Display("\nFinal Borrowing List");
			for (book B : PENDING) {
				UI.Display(B.toString());
			}
			COMPLETED = new ArrayList<loan>();
			UI.Set_state(BorrowbookUI.UI_state.FINALISING);
			state = ControlState.FINALISING;
		}
	}


	public void Commit_LOans() {
		if (!state.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowbookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : PENDING) {
			loan LOAN = library.ISSUE_LAON(B, member);
			COMPLETED.add(LOAN);			
		}
		UI.Display("Completed Loan Slip");
		for (loan LOAN : COMPLETED) {
			UI.Display(LOAN.toString());
		}
		UI.Set_state(BorrowbookUI.UI_state.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		UI.Set_state(BorrowbookUI.UI_state.CANCELLED);
		state = ControlState.CANCELLED;
	}
	
	
}
