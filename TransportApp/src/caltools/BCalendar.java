package caltools;

import java.util.Date;

public interface BCalendar {
	enum CalendarType {POPUP,SINGLE_TIMESHEET, MULTI_TIMESHEET};
	enum CalendarMode {DAY, WEEK, MONTH};
	int  standard_width=400, standard_height=400;
	
	
	void setType(CalendarType ctype);
	void setMode(CalendarMode cmode);
	void initializeCalendar();
	boolean isInitialized();
	boolean isLeapYear(Date d);
	
}
