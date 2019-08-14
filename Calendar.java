import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar SeLf;
	private static java.util.Calendar calendar; //Changed the instance name
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance(); //Changed the instance name
	}
	
	public static Calendar INSTANCE() {
		if (SeLf == null) {
			SeLf = new Calendar();
		}
		return SeLf;
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days); //Changed the instance name		
	}
	
	public synchronized void setDate(Date date) { //Changing the method name to meaningful verb phrase
		try {
			calendar.setTime(date); //Changed the instance name
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  //Changed the instance name
	        calendar.set(java.util.Calendar.MINUTE, 0);  //Changed the instance name
	        calendar.set(java.util.Calendar.SECOND, 0);  //Changed the instance name
	        calendar.set(java.util.Calendar.MILLISECOND, 0); //Changed the instance name
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  //Changed the instance name
	        calendar.set(java.util.Calendar.MINUTE, 0);  //Changed the instance name
	        calendar.set(java.util.Calendar.SECOND, 0);  //Changed the instance name
	        calendar.set(java.util.Calendar.MILLISECOND, 0); //Changed the instance name
			return calendar.getTime(); //Changed the instance name
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date Due_Date(int loanPeriod) {
		Date NoW = Date();
		 calendar.add(java.util.Calendar.DATE, loanPeriod); //Changed the instance name
		Date DuEdAtE =  calendar.getTime(); //Changed the instance name
		 calendar.setTime(NoW); //Changed the instance name
		return DuEdAtE;
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long Diff_Millis = Date().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
