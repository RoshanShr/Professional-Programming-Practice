public class PayFineControl {
	
	private PayFineUI ui; // changes the instance of PayFineUI to ui
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; //Changed the enusm to ControlState
	private ControlState state; //Changed variable to state
	
	private Library library; //Changed instance to library of Library class
	private Member member; //Changed instance to member of Member class


	public PayFineControl() {
		this.library = library.getInstance(); //Changed method to getInstance
		state = ControlState.INITIALISED;
	}
	
	
	public void setUI(PayFineUI ui) { //Changed method to setUI
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(PayFineUI.UIState.READY); //Changed method to setState
		state = ControlState.READY;		
	}


	public void cardSwiped(int memberId) { //Changed method to cardSwiped
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.Member(memberId);
		
		if (member == null) {
			ui.display("Invalid Member Id");
			return;
		}
		ui.display(member.toString());
		ui.setState(PayFineUI.UIState.PAYING);
		state = ControlState.PAYING; // Change variable to state
	}
	
	
	public void cancel() { // changed method to cancel
		ui.setState(PayFineUI.UIState.CANCELLED);
		state = ControlState.CANCELLED;
	}


	public double payFine(double amount) { // changed method to payFine
		if (!state.equals(ControlState.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount); // change varaible to change
		if (change > 0) {
			ui.display(String.format("Change: $%.2f", ChAnGe));
		}
		ui.display(member.toString());
		ui.setState(PayFineUI.UIState.COMPLETED);
		state = ControlState.COMPLETED;
		return change;
	}
	


}
