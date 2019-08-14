import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class member implements Serializable {

	private String LN;
	private String FN;
	private String EM;
	private int PN;
	private int ID;
	private double FINES;
	
	private Map<Integer, loan> LNS;

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.LN = lastName;
		this.FN = firstName;
		this.EM = email;
		this.PN = phoneNo;
		this.ID = id;
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(ID).append("\n")
		  .append("  Name:  ").append(LN).append(", ").append(FN).append("\n")
		  .append("  Email: ").append(EM).append("\n")
		  .append("  Phone: ").append(PN)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", FINES))
		  .append("\n");
		
		for (loan Loan : LNS.values()) {  //Changed Loan variable to correct order
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	
	public int getId() {	//Changed the method name to correct order
		return ID;
	}

	
	public List<loan> getLoans() {	//Changed the method name to correct order
		return new ArrayList<loan>(LNS.values());
	}

	
	public int numberOfCurrentLoans() {	//Changed the method name to correct order
		return LNS.size();
	}

	
	public double finesOwed() {	//Changed the method name to correct order
		return FINES;
	}

	
	public void takeOutLoan(loan loan) {	//Changed the method name to correct order
		if (!LNS.containsKey(loan.ID())) {
			LNS.put(loan.ID(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {	//Changed the method name to correct order
		return LN;
	}

	
	public String getFirstName() {	//Changed the method name to correct order
		return FN;
	}


	public void addFine(double fine) {  //Changed the method name to correct order
		FINES += fine;
	}
	
	public double Pay_Fine(double AmOuNt) {
		if (AmOuNt < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (AmOuNt > FINES) {
			change = AmOuNt - FINES;
			FINES = 0;
		}
		else {
			FINES -= AmOuNt;
		}
		return change;
	}


	public void disChargeLoan(loan Loan) {   //Changed the method name to correct order
		if (LNS.containsKey(Loan.ID())) {
			LNS.remove(Loan.ID());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
