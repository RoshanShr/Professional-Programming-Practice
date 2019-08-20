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
		StAtE = ControlState.READY;		
	}


	public void Card_Swiped(int memberId) {
		if (!StAtE.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.Member(memberId);
		
		if (member == null) {
			ui.DiSplAY("Invalid Member Id");
			return;
		}
		ui.DiSplAY(member.toString());
		ui.Set_State(PayFineUI.UI_STATE.PAYING);
		StAtE = CONTROL_STATE.PAYING;
	}
	
	
	public void CaNcEl() {
		ui.Set_State(PayFineUI.UI_STATE.CANCELLED);
		StAtE = CONTROL_STATE.CANCELLED;
	}


	public double PaY_FiNe(double AmOuNt) {
		if (!StAtE.equals(CONTROL_STATE.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double ChAnGe = member.Pay_Fine(AmOuNt);
		if (ChAnGe > 0) {
			ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));
		}
		ui.DiSplAY(member.toString());
		ui.Set_State(PayFineUI.UI_STATE.COMPLETED);
		StAtE = CONTROL_STATE.COMPLETED;
		return ChAnGe;
	}
	


}
