package in.ols.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.data.util.Pair;

public class DateUtil {

	public static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	public static String epochFormat = "YYYY-MM-DDTHH:mm:ss.SSS";

	public static String getDateTimeString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		return simpleDateFormat.format(date);
	}

	public static Object getDateTimeAsPerClient(Date date) {
		return date;
	}
	
	public static Date getDate(String date) throws ParseException {
		Date formattedDate = new SimpleDateFormat(DateUtil.dateFormat).parse(date);
		return formattedDate;
	}
	
	public static Date getEpochDate(String date) throws ParseException {
		Date formattedDate = new SimpleDateFormat(DateUtil.epochFormat).parse(date);
		return formattedDate;
	}

	public static Pair<Date, Date> getDateRange(Date date) {
		Date begining, end;

		{
			Calendar calendar = getCalendarForNow(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			setTimeToBeginningOfDay(calendar);
			begining = calendar.getTime();
		}

		{
			Calendar calendar = getCalendarForNow(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			setTimeToEndofDay(calendar);
			end = calendar.getTime();
		}

		return Pair.of(begining, end);
	}

	public static Calendar getCalendarForNow() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		return calendar;
	}

	public static Calendar getCalendarForNow(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static Calendar getCalendarForMonth(int month) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		return calendar;
	}

	public static void setTimeToBeginningOfDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	public static void setTimeToEndofDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
	}
	
	public static long getADayAdded(Date date) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		return c.getTimeInMillis();
	}
	
	public static Date getLongToDate(long longVal) {
		if(longVal!=0) {
	        Date date=new Date(longVal);
	        return date;
		}
		return null;
	}
}
