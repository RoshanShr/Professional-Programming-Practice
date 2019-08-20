import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; //Fixed enum name order

	private BorrowBookControl control;
	private Scanner input;
	private UiState state;	//Changed the variable names to correct order

	
	public BorrowBookUI(BorrowBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UiState.INITIALISED;
		control.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UiState state) {	//Change method name to setState
		this.state = state;	//Fixed the names
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memberDetails = input("Swipe member card (press <enter> to cancel): ");	//Changed variable name to right order(memberDetails)
				if (memberDetails.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memberDetails).intValue();	//Changed variable name to right order(memberId)
					control.swiped(memberId);	//Fixed the method name to right order(swiped)
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel();
				break;
			
				
			case SCANNING:
				String bookDetails = input("Scan Book (<enter> completes): ");	//Changed variable name to right order(bookDetails)
				if (bookDetails.length() == 0) {
					control.complete();	//Fixed the method name to right order(complete)
					break;
				}
				try {
					int bookId = Integer.valueOf(bookDetails).intValue();	//Changed variable name to right order(bookId)
					control.scanned(bookId);	//Fixed the method name to right order(scanned)
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String confirmation = input("Commit loans? (Y/N): ");	//Changed variable name to right order(confirmation)
				if (confirmation.toUpperCase().equals("N")) {
					control.cancel();
					
				} else {
					control.commitLoans();	//Changed method name to right order(commitLoans)
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);			
			}
		}		
	}


	public void display(Object object) {	//Changed method name to display
		output(object);		
	}


}
