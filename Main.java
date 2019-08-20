import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner in; //Changed the instance IN to in
	private static library lib; //Changed the instance LIB to lib
	private static String menu; //Changed the instance MENU to menu
	private static Calendar cal; //Changed the instance CAL to cal
	private static SimpleDateFormat sdf; //Changed the instance SDF to sdf
	
	
	private static String Get_menu() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			in = new Scanner(System.in);
			lib = library.INSTANCE();
			cal = Calendar.INSTANCE();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
	
			for (member m : lib.MEMBERS()) {
				output(m);
			}
			output(" ");
			for (book b : lib.BOOKS()) {
				output(b);
			}
						
			menu = Get_menu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.Date()));
				String c = input(menu);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					ADD_MEMBER();
					break;
					
				case "LM": 
					MEMBERS();
					break;
					
				case "B": 
					ADD_BOOK();
					break;
					
				case "LB": 
					BOOKS();
					break;
					
				case "FB": 
					FIX_BOOKS();
					break;
					
				case "L": 
					BORROW_BOOK();
					break;
					
				case "R": 
					RETURN_BOOK();
					break;
					
				case "LL": 
					CURRENT_LOANS();
					break;
					
				case "P": 
					FINES();
					break;
					
				case "T": 
					INCREMENT_DATE();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void fines() { //method name must start with lower case
		new PayFineUI(new PayFineControl()).RuN();		
	}


	private static void currentLoans() { //method name must start with lowercase and be in camelBack
		output("");
		for (loan loan : lib.CurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void books() { //method name must start with lower case
		output("");
		for (book book : lib.books()) {
			output(book + "\n");
		}		
	}



	private static void members() { //method name must start with lower case
		output("");
		for (member member : lib.MEMBERS()) {
			output(member + "\n");
		}		
	}



	private static void borrowBook() { //method name must start with lowercase and be in camelBack
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() { //method name must start with lowercase and be in camelBack
		new ReturnBookUI(new ReturnBookControl()).RuN();		
	}


	private static void fixBooks() { //method name must start with lowercase and be in camelBack
		new FixBookUI(new FixBookControl()).RuN();		
	}


	private static void incrementDate() { //method name must start with lowercase and be in camelBack
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			cal.incrementDate(days);
			lib.checkCurrentLoans();
			output(sdf.format(cal.Date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() { //method name must start with lowercase and be in camelBack
		
		String A = input("Enter author: ");
		String T  = input("Enter title: ");
		String C = input("Enter call number: ");
		book B = lib.Add_book(A, T, C);
		output("\n" + B + "\n");
		
	}

	
	private static void addMember() { //method name must start with lowercase and be in camelBack
		try {
			String LN = input("Enter last name: ");
			String FN  = input("Enter first name: ");
			String EM = input("Enter email: ");
			int PN = Integer.valueOf(input("Enter phone number: ")).intValue();
			member M = lib.Add_mem(LN, FN, EM, PN);
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return in.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
