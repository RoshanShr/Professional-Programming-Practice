import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl { // Changes class name to BorrowBookControl
	
	private BorrowBookUI borrowbookui; //Created instance of BorrowBookUI Class
	
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
	

	public void setUI(BorrowBookUI borrowbookui) {
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.BorrowBookUI = borrowbookui;
		borrowbookui.Set_state(BorrowBookUI.UI_state.READY);
		state = ControlState.READY;		
	}

		
	public void Swiped(int MEMMER_ID) {
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.MEMBER(MEMMER_ID);
		if (member == null) {
			borrowbookui.Display("Invalid memberId");
			return;
		}
		if (library.MEMBER_CAN_BORROW(member)) {
			PENDING = new ArrayList<>();
			borrowbookui.Set_state(BorrowBookUI.UI_state.SCANNING);
			state = ControlState.SCANNING; }
		else 
		{
			borrowbookui.Display("Member cannot borrow at this time");
			borrowbookui.Set_state(BorrowBookUI.UI_state.RESTRICTED); }}
	
	
	public void Scanned(int bookId) {
		book = null;
		if (!state.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.book(bookId);
		if (book == null) {
			borrowbookui.Display("Invalid bookId");
			return;
		}
		if (!book.AVAILABLE()) {
			borrowbookui.Display("book cannot be borrowed");
			return;
		}
		PENDING.add(book);
		for (book B : PENDING) {
			borrowbookui.Display(B.toString());
		}
		if (library.Loans_Remaining_For_Member(member) - PENDING.size() == 0) {
			borrowbookui.Display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			borrowbookui.Display("\nFinal Borrowing List");
			for (book B : PENDING) {
				borrowbookui.Display(B.toString());
			}
			COMPLETED = new ArrayList<loan>();
			borrowbookui.Set_state(BorrowBookUI.UI_state.FINALISING);
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
		borrowbookui.Display("Completed Loan Slip");
		for (loan LOAN : COMPLETED) {
			borrowbookui.Display(LOAN.toString());
		}
		borrowbookui.Set_state(BorrowBookUI.UI_state.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		borrowbookui.Set_state(BorrowBookUI.UI_state.CANCELLED);
		state = ControlState.CANCELLED;
	}
	
	
}
