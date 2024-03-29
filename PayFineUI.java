import java.util.Scanner;


public class PayFineUI {


	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };	//Changed the enum variable name to correct order (UiState)

	private PayFineControl control;	//Changed the instance name to right order(control)
	private Scanner input;
	private UiState state;	//Changed the instance name to right format (state)

	
	public PayFineUI(PayFineControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UiState.INITIALISED;
		control.setUi(this);	//Changed the method name to right order(setUi)
	}
	
	
	public void setState(UiState state) {	//Changed the method name to right order(setState)
		this.state = state;
	}


	public void run() {	//Changed the method name to right format(run)
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {	//Changed the variable name to right order(state)

			case READY:
				String memberCardDetails = input("Swipe member card (press <enter> to cancel): ");	//Changed the variable name to meaningful(memberCardDetails)
				if (memberCardDetails.length() == 0) {
					control.cancel();	//Changed the method name to right order(cancel)
					break;
				}
				try {
					int memberId = Integer.valueOf(memberCardDetails).intValue();
					control.cardSwiped(memberId);	//Changed the variable name to right order(memberId) and also changed method name(cardSwiped)
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;	//Changed the variable name to right order(amount)
				String amountDetails = input("Enter amount (<Enter> cancels) : "); //Changed the variable name to make it meaningful(amountDetails)
				if (amountDetails.length() == 0) {
					control.cancel();
					break;
				}
				try {
					amount = Double.valueOf(amountDetails).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				control.payFine(amount);	//Changed the method name to right order(payFine)
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
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
			

	public void display(Object object) {	//Changed the method name to right order(display)
		output(object);
	}


}
