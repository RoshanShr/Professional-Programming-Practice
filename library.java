
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


	public member Add_mem(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, NextMID());
		MEMBERS.put(member.GeT_ID(), member);		
		return member;
	}

	
	public book Add_book(String a, String t, String c) {		
		book b = new book(a, t, c, NextBID());
		CATALOG.put(b.ID(), b);		
		return b;
	}

	
	public member MEMBER(int memberId) {
		if (MEMBERS.containsKey(memberId)) 
			return MEMBERS.get(memberId);
		return null;
	}

	
	public book Book(int bookId) {
		if (CATALOG.containsKey(bookId)) 
			return CATALOG.get(bookId);		
		return null;
	}

	
	public int LOAN_LIMIT() {
		return loanLimit;
	}

	
	public boolean MEMBER_CAN_BORROW(member member) {		
		if (member.Number_Of_Current_Loans() == loanLimit ) 
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.GeT_LoAnS()) 
			if (loan.OVer_Due()) 
				return false;
			
		return true;
	}

	
	public int Loans_Remaining_For_Member(member member) {		
		return loanLimit - member.Number_Of_Current_Loans();
	}

	
	public loan ISSUE_LAON(book book, member member) {
		Date dueDate = Calendar.getInstance().Due_Date(loanPeriod);
		loan loan = new loan(NextLID(), book, member, dueDate);
		member.Take_Out_Loan(loan);
		book.Borrow();
		LOANS.put(loan.ID(), loan);
		CURRENT_LOANS.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan LOAN_BY_BOOK_ID(int bookId) {
		if (CURRENT_LOANS.containsKey(bookId)) {
			return CURRENT_LOANS.get(bookId);
		}
		return null;
	}

	
	public double CalculateOverDueFine(loan loan) {
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.getInstance().Get_Days_Difference(loan.Get_Due_Date());
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void Discharge_loan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.Add_Fine(overDueFine);	
		
		member.dIsChArGeLoAn(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.Add_Fine(damageFee);
			DAMAGED_BOOKS.put(book.ID(), book);
		}
		currentLoan.DiScHaRgE();
		CURRENT_LOANS.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : CURRENT_LOANS.values()) {
			loan.checkOverDue();
		}		
	}


	public void Repair_BOOK(book currentBook) {
		if (DAMAGED_BOOKS.containsKey(currentBook.ID())) {
			currentBook.Repair();
			DAMAGED_BOOKS.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
