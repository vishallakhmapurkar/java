/**
 * 
 */
package biz.neustar.netnumber.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author rrsharma
 * 
 * @version $Revision: 1.1 $
 */
public class DateUtils {

	public static final Logger LOGGER = Logger.getLogger(DateUtils.class);

	// create a thread local for date format as it is not thread safe
	private static final ThreadLocal<DateFormat> threadLocalFormatter = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmm");
		}
	};

	/**
	 * Method getDate.
	 * 
	 * @param dateStr
	 *            String
	 * 
	 * 
	 * @return Date * @throws ParseException * @throws ParseException
	 */
	public static Date getDate(final String dateStr) throws ParseException {
		DateFormat dateFormat = threadLocalFormatter.get();
		Date date = null;

		if (dateStr != null && !"".equals(dateStr.trim())) {
			date = dateFormat.parse(dateStr);
		}

		return date;
	}

	/**
	 * Method getCurrentDateAsString.
	 * 
	 * @return String
	 */
	public static String getCurrentDateAsString() {
		DateFormat dateFormat = threadLocalFormatter.get();
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	/**
	 * Method getCurrentDate.
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
}
