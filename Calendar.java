import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; // local variable Self changed to self by Arjun @3:17PM
	private static java.util.Calendar calendar; //Changed the instance name from CaLeNdAr to calender
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance(); //Changed the instance name from CaLeNdAr to calender
	}
	
	public static Calendar INSTANCE() {
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
	        calendar.set(java.util.Calendar.HourOfDay, 0);  //Changed the instance name from CaLeNdAr to calender and class name from HOUR_OF_DAY to HourOfDay
	        calendar.set(java.util.Calendar.Minute, 0);  //Changed the instance name from CaLeNdAr to calender and class name from MINUTE to Minute
	        calendar.set(java.util.Calendar.Second, 0);  //Changed the instance name from CaLeNdAr to calender and class name from SECOND to Second
	        calendar.set(java.util.Calendar.MilliSecond, 0); //Changed the instance name from CaLeNdAr to calender and class name from MILLISECOND to MilliSecond
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date date() { //Changing the method name to meaningful verb phrase
		try {
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  ////Changed the instance name from CaLeNdAr to calender
	        calendar.set(java.util.Calendar.MINUTE, 0);  //Changed the instance name from CaLeNdAr to calender
	        calendar.set(java.util.Calendar.SECOND, 0);  //Changed the instance name from CaLeNdAr to calender
	        calendar.set(java.util.Calendar.MILLISECOND, 0); //Changed the instance name from CaLeNdAr to calender
			return calendar.getTime(); ///Changed the instance name from CaLeNdAr to Calender
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date dueDate(int loanPeriod) { //Changing the method name to meaningful verb phrase
		Date NoW = Date();
		 calendar.add(java.util.Calendar.DATE, loanPeriod); //Changed the instance name from CaLeNdAr to calender
		Date DuEdAtE =  calendar.getTime(); //Changed the instance name from CaLeNdAr to calender
		 calendar.setTime(NoW); //Changed the instance name from CaLeNdAr to calender
		return DuEdAtE;
	}
	
	public synchronized long getDaysDifference(Date targetDate) { //Changing the method name to meaningful verb phrase
		
		long Diff_Millis = Date().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
