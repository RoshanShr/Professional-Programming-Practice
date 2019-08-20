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
					control.SCannING_COMplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookDetails).intValue();	//Changed the variable name to right order(bookId)
						control.Book_scanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String AnS = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				if (AnS.toUpperCase().equals("Y")) {
					FiX = true;
				}
				control.FIX_Book(FiX);
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
