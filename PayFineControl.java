public class PayFineControl {
	
	private PayFineUI ui; // changes the instance of PayFineUI to ui
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; //Changed the enusm to ControlState
	private ControlState state; //Changed variable to state
	
	private library LiBrArY;
	private member MeMbEr;


	public PayFineControl() {
		this.LiBrArY = LiBrArY.INSTANCE();
		StAtE = CONTROL_STATE.INITIALISED;
	}
	
	
	public void Set_UI(PayFineUI ui) {
		if (!StAtE.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.Set_State(PayFineUI.UI_STATE.READY);
		StAtE = CONTROL_STATE.READY;		
	}


	public void Card_Swiped(int memberId) {
		if (!StAtE.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		MeMbEr = LiBrArY.MEMBER(memberId);
		
		if (MeMbEr == null) {
			ui.DiSplAY("Invalid Member Id");
			return;
		}
		ui.DiSplAY(MeMbEr.toString());
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
		double ChAnGe = MeMbEr.Pay_Fine(AmOuNt);
		if (ChAnGe > 0) {
			ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));
		}
		ui.DiSplAY(MeMbEr.toString());
		ui.Set_State(PayFineUI.UI_STATE.COMPLETED);
		StAtE = CONTROL_STATE.COMPLETED;
		return ChAnGe;
	}
	


}
