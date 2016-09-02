package com.epam.javalab13.util;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Util class for converting string date and time to Timestamp object
 */
public class DateConverter {
    private static Logger logger = Logger.getLogger(DateConverter.class);

    /**
     * Converting Strings of date and time into Timestamp
     * @param dateValue the string of date
     * @param timeValue the string of time
     * @return the Timestamp object
     */
    public static Timestamp getTimestamp(String dateValue, String timeValue){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");


        Date date = null;
        try {
            date = dateFormat.parse(dateValue);
        } catch (Exception e) {
        	System.out.println("can parse date");
            logger.error("Cant parse date" + dateValue  + ":",e);
        }

        Date time = null;
        try {
            time = timeFormat.parse(timeValue);
        } catch (Exception e) {
        	System.out.println("cant parse time");
            logger.error("Cant parse time" + timeValue  + ":",e);
        }

        Timestamp timestamp = new Timestamp((new Date(date.getTime()+time.getTime() + 7200000)).getTime());
        return timestamp;
    }
}
