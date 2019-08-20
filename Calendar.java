import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; // local variable Self changed to self by Arjun @3:17PM
	private static java.util.Calendar calendar; //Changed the instance name from CaLeNdAr to calender
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance(); //Changed the instance name from CaLeNdAr to calender
	}
	
	public static Calendar getInstance() { //Changed the method name
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.Date, days); //Changed the instance name from CaLeNdAr to calender and the class DATE to Date	
	}
	
	public synchronized void setDate(Date date) { //Changing the method name to meaningful verb phrase
		try {
			calendar.setTime(date); //Changed the instance name
			calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
			calendar.set(java.util.Calendar.MINUTE, 0); 
			calendar.set(java.util.Calendar.SECOND, 0);  
			calendar.set(java.util.Calendar.MILLISECOND, 0); 
			return calendar.getTime(); 
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date date() { //Changing the method name to meaningful verb phrase
		try {
			calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
			calendar.set(java.util.Calendar.SECOND, 0);  
			calendar.set(java.util.Calendar.MILLISECOND, 0); 
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date dueDate(int loanPeriod) { //Changing the method name to meaningful verb phrase
		Date now = Date(); //Changed the variable NoW to now
		calendar.add(java.util.Calendar.DATE, loanPeriod); 
		Date duedate =  calendar.getTime(); 
		calendar.setTime(now); //Changed the instance name from CaLeNdAr to calender and the varibale NoW to now
		return dueDate; //Changed the variable DuEdAtE to dueDate
	}
	
	public synchronized long getDaysDifference(Date targetDate) { //Changing the method name to meaningful verb phrase
		
		long diffMillis = Date().getTime() - targetDate.getTime(); //Changed the variable name
		long diffDays = timeUnit.days.convert(diffMills, timeUnit.MILLISECONDS); //Changed the variable name and method name
		return diffDays; //Changed the variable name
	}

}
