import java.util.Scanner;


public class FixBookUI {

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };	//Changed the enum variable name (UiState)

	private FixBookControl control;	//Changed the instance name to right order(control)
	private Scanner input;
	private UiState state;	//Changed the instance name to right order(state)

	
	public FixBookUI(FixBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UiState.INITIALISED;
		control.setUi(this);	//Changed the method name to right order(setUi)
	}


	public void setState(UiState state) {	//Changed the variable name to right order(setState)
		this.state = state;
	}

	
	public void run() {	//Changed the enum variable name (run)
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String bookDetails = input("Scan Book (<enter> completes): ");	//Changed the variable name to right order(bookDetails)
				if (bookDetails.length() == 0) {
					control.scanningComplete();	//Changed the method name to right format(scanningComplete)
				}
				else {
					try {
						int bookId = Integer.valueOf(bookDetails).intValue();	//Changed the variable name to right order(bookId)
						control.bookScanned(bookId);	//Changed the method name to right format(bookScanned)
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String confirmationMessage = input("Fix Book? (Y/N) : "); //Changed the variable name to meaningful(confirmationMessage)
				boolean fix = false; //Changed the variable name to right format(fix)
				if (confirmationMessage.toUpperCase().equals("Y")) {
					fix = true;
				}
				control.fixBook(fix); //Changed the method name to right format(fixBook)
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
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
	
	
}
