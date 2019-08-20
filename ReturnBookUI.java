import java.util.Scanner;


public class ReturnBookUI {

	public static enum UIState { INITIALISED, READY, INSPECTING, COMPLETED }; //Changed the enum to UIState

	private ReturnBookControl control; // Changed variable to control
	private Scanner input;
	private UIState state;

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UIState.INITIALISED;
		control.setUI(this); // Changed method to setUI
	}


	public void run() {		// Changed method to run
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { //Changed to state
			
			case INITIALISED:
				break;
				
			case READY:
				String book = input("Scan Book (<enter> completes): ");
				if (book.length() == 0) {
					control.scanningComplete(); // Changed method to scanningComplete
				}
				else {
					try {
						int bookId = Integer.valueOf(book).intValue(); 
						control.bookScanned(bookId); // Changed method to bookScanned
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				control.dischargeLoan(isDamaged); // changed variable to isDamaged
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
			}
		}
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void setState(UIState state) { // changed method to setState
		this.state = state;
	}

	
}
