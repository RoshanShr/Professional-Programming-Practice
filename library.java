
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable { //Class name must start with capital letter
	
	private static final String LIBRARY_FILE = "library.obj"; //Constants must be all upper case and underscore separated
	private static final int LOAN_LIMIT = 2; //Constants must be all upper case and underscore separated
	private static final int LOAN_PERIOD = 2; //Constants must be all upper case and underscore separated
	private static final double FINE_PER_DAY = 1.0; //Constants must be all upper case and underscore separated
	private static final double MAX_FINES_OWED = 1.0; //Constants must be all upper case and underscore separated
	private static final double DAMAGE_FREE = 2.0; //Constants must be all upper case and underscore separated
	
	private static Library self; //Changed the class library to Library and instance SeLf to self
	private int bookId; //Variable name must start with lower case and be in camelBack.
	private int memberId; //Variable name must start with lower case and be in camelBack.
	private int loanId; //Variable name must start with lower case and be in camelBack.
	private Date loanDate; //Variable name must start with lower case and be in camelBack.
	
	private Map<Integer, book> CATALOG;
	private Map<Integer, member> MEMBERS;
	private Map<Integer, loan> LOANS;
	private Map<Integer, loan> CURRENT_LOANS;
	private Map<Integer, book> DAMAGED_BOOKS;
	

	private getLibrary() { //Method name should be a verb and start with lowercase and be in camelback
		CATALOG = new HashMap<>();
		MEMBERS = new HashMap<>();
		LOANS = new HashMap<>();
		CURRENT_LOANS = new HashMap<>();
		DAMAGED_BOOKS = new HashMap<>();
		bookId = 1; //Variable name must be in lowercase and camelBack
		memberId = 1;		 //Variable name must be in lowercase and camelBack
		loanId = 1;		//Variable name must be in lowercase and camelBack
	}

	
	public static synchronized Library getInstance() {	//Method name should be a verb and start with lowercase and be in camelback	
		if (self == null) {
			Path PATH = Paths.get(libraryFile);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream lif = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					self = (Library) lif.readObject(); //Changed object LiF to lif
					Calendar.getInstance().setDate(self.loanDate); //Method name should be a verb and start with lowercase and be in camelback
					lif.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();
		}
		return self;
	}

	
	public static synchronized void isSave() { //Method name should be a verb and start with lowercase and be in camelback
		if (self != null) {
			self.loanDate = Calendar.getInstance().Date(); //Variable name LOAN_DATE changed to loanDate
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				LoF.writeObject(self);
				LoF.flush();
				LoF.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int bookId() { //Method name changed to lowercase and camelBack.
		return bookId; //Variable name must be in lowercase and camelBack 
	}
	
	
	public int memberId() { //Method name changed to lowercase and camelBack.
		return memberId; //Variable name must be in lowercase and camelBack
	}
	
	
	private int nextBid() { //Method name changed to lowercase and camelBack.
		return bookId++; //Variable name must be in lowercase and camelBack
	}

	
	private int nextMid() { //Method name changed to lowercase and camelBack.
		return memberId++; //Variable name must be in lowercase and camelBack
	}

	
	private int nextLid() { //Method name changed to lowercase and camelBack.
		return loanId++; //Variable name must be in lowercase and camelBack
	}

	
	public List<member> MEMBERS() {		
		return new ArrayList<member>(MEMBERS.values()); 
	}


	public List<book> BOOKS() {		
		return new ArrayList<book>(CATALOG.values()); 
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(CURRENT_LOANS.values());
	}


	public Member Add_mem(String lastName, String firstName, String email, int phoneNo) { //Class name member changed to Member
		Member member = new member(lastName, firstName, email, phoneNo, nextMid());
		MEMBERS.put(member.getID(), member);	//method GET_ID changed to getID	
		return member;
	}

	
	public book addBook(String a, String t, String c) {	//Method name Add_book changed to aadBook	
		book b = new book(a, t, c, nextBid());
		CATALOG.put(b.getId(), b); //Method name must start with lowercase and be in camelBack		
		return b;
	}

	
	public Member MEMBER(int memberId) {
		if (MEMBERS.containsKey(memberId)) 
			return MEMBERS.get(memberId);
		return null;
	}

	
	public book Book(int bookId) {
		if (CATALOG.containsKey(bookId)) 
			return CATALOG.get(bookId);		
		return null;
	}

	
	public int loanLimit() { //Method name must start with lowercase and be in camelBack	
		return loanLimit;
	}

	
	public boolean MEMBER_CAN_BORROW(Member member) {		
		if (member.Number_Of_Current_Loans() == loanLimit ) 
			return false;
				
		if (member.finesOwed() >= maxFinesOwed)  //Method name must start with lowercase and be in camelBack	
			return false;
				
		for (Loan loan : Member.getLoans()) //Method name must start with lowercase and be in camelBack	
			if (loan.overDue()) //Method name must start with lowercase and be in camelBack	
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(Member member) { //Method name must start with lowercase and be in camelBack			
		return loanLimit - member.numberOfCurrentLoans(); //Method name must start with lowercase and be in camelBack	
	}

	
	public loan issueLoan(Book book, Member member) { //Method name must start with lowercase and be in camelBack	
		Date dueDate = Calendar.getInstance().dueDate(loanPeriod); //Method name must start with lowercase and be in camelBack	
		loan loan = new loan(nextLid(), book, member, dueDate);
		member.takeOutLoan(loan); //Method name must start with lowercase and be in camelBack	
		book.Borrow();
		LOANS.put(loan.getId(), loan); //Method name must start with lowercase and be in camelBack	
		CURRENT_LOANS.put(book.getId(), loan); //Method name must start with lowercase and be in camelBack	
		return loan;
	}
	
	
	public loan LOAN_BY_BOOK_ID(int bookId) {
		if (CURRENT_LOANS.containsKey(bookId)) {
			return CURRENT_LOANS.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) { //Method name must start with lowercase and be in camelBack	
		if (loan.overDue()) { //Method name must start with lowercase and be in camelBack	
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate()); //Method name must start with lowercase and be in camelBack	
			long daysOverDue = Calendar.getInstance().getDifference(loan.getDueDate()); //Method name must start with lowercase and be in camelBack	
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) { //Method name must start with lowercase and be in camelBack	
		Member member = currentLoan.member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.Add_Fine(overDueFine);	
		
		member.disChargeLoan(currentLoan); //Method name must start with lowercase and be in camelBack	
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(damageFee);
			damagedBooks.put(book.ID(), book); //Method name must start with lowercase and be in camelBack	
		}
		currentLoan.getDischarge(); //Method name must start with lowercase and be in camelBack	
		CURRENT_LOANS.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : CURRENT_LOANS.values()) {
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {
		if (DAMAGED_BOOKS.containsKey(currentBook.ID())) {
			currentBook.Repair();
			DAMAGED_BOOKS.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
