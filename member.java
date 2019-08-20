import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {	//Capitalized the first letter of the class name

	private String lastName;
	private String fastName;
	private String email;
	private int phoneNo;
	private int id;
	private double fines;
	
	private Map<Integer, loan> LNS;

	
	public Member(String lastName, String firstName, String email, int phoneNo, int id) {	//Capitalized the first letter of the constructor
		
		// Changed the variable names to make it more appropriate
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.id = id;
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(id).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(email).append("\n")
		  .append("  Phone: ").append(phoneNo)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines))
		  .append("\n");
		
		for (loan Loan : LNS.values()) {  //Changed Loan variable to correct order
			sb.append(Loan).append("\n");
		}		  
		return sb.toString();
	}

	
	public int getId() {	//Changed the method name to correct order
		return id;
	}

	
	public List<loan> getLoans() {	//Changed the method name to correct order
		return new ArrayList<loan>(LNS.values());
	}

	
	public int numberOfCurrentLoans() {	//Changed the method name to correct order
		return LNS.size();
	}

	
	public double finesOwed() {	//Changed the method name to correct order
		return fines;
	}

	
	public void takeOutLoan(loan loan) {	//Changed the method name to correct order
		if (!LNS.containsKey(loan.id())) {
			LNS.put(loan.id(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {	//Changed the method name to correct order
		return lastName;
	}

	
	public String getFirstName() {	//Changed the method name to correct order
		return firstName;
	}


	public void addFine(double fine) {  //Changed the method name to correct order
		fines += fine;
	}
	
	public double payFine(double amount) {	//Changed the fines and amount variable to correct order
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > amount) {
			change = amount - fines;
			fines = 0;
		}
		else {
			fines -= amount;
		}
		return change;
	}


	public void disChargeLoan(loan Loan) {   //Changed the method name to correct order
		if (LNS.containsKey(Loan.id())) {
			LNS.remove(Loan.id());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
