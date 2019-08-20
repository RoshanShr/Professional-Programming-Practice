import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner in; //Changed the instance IN to in
	private static library lib; //Changed the instance LIB to lib
	private static String menu; //Changed the instance MENU to menu
	private static Calendar cal; //Changed the instance CAL to cal
	private static SimpleDateFormat sdf; //Changed the instance SDF to sdf
	
	
	private static String getMenu() { //changed the method Get-menu to getMenu
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
			lib = library.getInstance(); //Method INSTANCE changed to getInstance
			cal = Calendar.getInstance();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
	
			for (member m : lib.getMembers()) { //Method name changed from MEMBERS to getMembers
				output(m);
			}
			output(" ");
			for (book b : lib.getBooks()) { //Method name changed from BOOKS to getBooks
				output(b);
			}
						
			menu = getMenu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + sdf.format(cal.Date()));
				String c = input(menu);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember(); //Method name must be in lowercase and camelBack
					break;
					
				case "LM": 
					getMembers(); //Method name must be in lowercase and camelBack
					break;
					
				case "B": 
					addBook(); //Method name must be in lowercase and camelBack
					break;
					
				case "LB": 
					getBooks(); //Method name must be in lowercase and camelBack
					break;
					
				case "FB": 
					fixBooks(); //Method name must be in lowercase and camelBack
					break;
					
				case "L": 
					borrowBook(); //Method name must be in lowercase and camelBack
					break;
					
				case "R": 
					returnBook(); //Method name must be in lowercase and camelBack
					break;
					
				case "LL": 
					currentLoans(); //Method name must be in lowercase and camelBack
					break;
					
				case "P": 
					getFines(); //Method name must be in lowercase and camelBack
					break;
					
				case "T": 
					incrementDate(); //Method name must be in lowercase and camelBack
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.getSave(); //Method SAVE changed to getSave
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void fines() { //method name must start with lower case
		new PayFineUI(new payFineControl()).getRun();		//method name must start with lowercase and be in camelBack
	}


	private static void currentLoans() { //method name must start with lowercase and be in camelBack
		output("");
		for (loan loan : lib.currentLoans()) { //method name must start with lowercase and be in camelBack
			output(loan + "\n");
		}		
	}



	private static void getBbooks() { //method name must start with lowercase and camelBack
		output("");
		for (book book : lib.getBooks()) {
			output(book + "\n");
		}		
	}



	private static void getMmembers() { //method name must start with lowercase and be in camelBack
		output("");
		for (member member : lib.getMembers()) { //Method name changed from MEMBERS to getMembers
			output(member + "\n");
		}		
	}



	private static void borrowBook() { //method name must start with lowercase and be in camelBack
		new BorrowBookUI(new BorrowBookControl()).getRun();		
	}


	private static void returnBook() { //method name must start with lowercase and be in camelBack
		new ReturnBookUI(new ReturnBookControl()).getRun();		
	}


	private static void fixBooks() { //method name must start with lowercase and be in camelBack
		new FixBookUI(new FixBookControl()).getRun();		
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
		book B = lib.addBook(A, T, C); //method name must start with lowercase and be in camelBack
		output("\n" + B + "\n");
		
	}

	
	private static void addMember() { //method name must start with lowercase and be in camelBack
		try {
			String lastName = input("Enter last name: "); //Variable name must be in lowercase and in camelBack
			String firstName  = input("Enter first name: "); //Variable name must be in lowercase and in camelBack
			String eMail = input("Enter email: "); //Variable name must be in lowercase and in camelBack
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue(); //Variable name must be in lowercase and in camelBack
			member M = lib.Add_mem(lastName, firstName, eMail, phoneNumber); //Variable name must be in lowercase and in camelBack
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
