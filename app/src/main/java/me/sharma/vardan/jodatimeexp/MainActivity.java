package me.sharma.vardan.jodatimeexp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

import static java.lang.String.valueOf;
import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.getInstance;

public class MainActivity extends AppCompatActivity {

	//use something like simple fate
	final String[] months =
			{"Jan", "Feb", "Mar", "Apr", "Jun", "May", "Jul", "Aug", "Sep", "oct", "nov", "dec"};

	final String[] days =
			{"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"};
	final String[] weeks =
			{"Week 1", "Week 2", "Week 3", "Week 4", "Week 5", "Week 6"};
	final String[] hours =
			{"12:00 am", "6:00 am", "12:00 pm", "6:00pm"};


	enum Type {
		YEARLY, MONTHLY, WEEKLY, DAILY
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		PointConvert.getPoints();
//		getWeeksInLast30Days();
//		getLast12Months();
//		getNumberOfWeeksInAMonth();
//		getNumberOfWeekInLast30Days();
//		getLast7Days();
//		getTimeslotInLast24Hours();
//		getWeekDatesInLast30Days();
//		getWeekDatesInLast30Days();
		test2();
	}


	// gets me last 12 months
	private void getLast12Months() {
		Calendar calendar = getInstance();
		for (int i = 0; i < 12; i++) {
			calendar.add(MONTH, -1);
			Timber.d(months[calendar.get(MONTH)]);
		}
	}

	//target is to find date of first day of current week
	private void getDateoFFirstDayOfWeek() {
		Calendar calendar = getInstance();
//		calendar.add(Calendar.DAY_OF_MONTH, 10);
		calendar.set(DAY_OF_WEEK, MONDAY);
//		Timber.d(calendar.toString());
		Timber.d(valueOf(calendar.get(DATE)) + valueOf(calendar.get(MONTH)));
	}

	//Number of weeks in a current month
	private void getNumberOfWeeksInAMonth() {
		Calendar calendar = getInstance();
		Timber.d(calendar.toString());
		calendar.add(Calendar.DAY_OF_MONTH, 10);
//		Timber.d(calendar.toString());
		Timber.d(valueOf(calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)));
	}

	//Number of weeks in a last 30 days
	private void getNumberOfWeekInLast30Days() {
		Calendar calendar = getInstance();
		Timber.d(calendar.toString());
		Set<Integer> weeks = new HashSet<>(6);
		for (int i = 0; i < 30; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			Timber.d(valueOf(calendar.get(Calendar.DAY_OF_YEAR)));
			weeks.add(calendar.get(Calendar.WEEK_OF_YEAR));
		}
		Timber.d(valueOf(weeks.size()));
	}


	//prints out the last 7 days
	private void getLast7Days() {
		Calendar calendar = getInstance();
		for (int i = 0; i < 7; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			Timber.d(days[calendar.get(DAY_OF_WEEK) - 1]);
		}
	}

	private void getTimeslotInLast24Hours() {
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 24 / 6; i++) {
			calendar.add(Calendar.HOUR, 6);
			Timber.d(valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
		}
	}

	//TODO prints out weeks date  in last 30 days
	private void getWeekDatesInLast30Days() {
		Calendar calendar = getInstance();
		int y = calendar.get(Calendar.YEAR);
		Timber.d(calendar.toString());
		Set<Integer> weeks = new HashSet<>(6);
		for (int i = 0; i < 30; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
//			Timber.d(String.valueOf(calendar.get(Calendar.DAY_OF_YEAR)));
//			Timber.d(String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
			weeks.add(calendar.get(Calendar.WEEK_OF_YEAR));
		}

		for (Integer week : weeks) {
//			Timber.d(valueOf(calendar.get(Calendar.DAY_OF_WEEK)));
			calendar.clear();
			calendar.set(Calendar.WEEK_OF_YEAR, week);
			calendar.set(Calendar.YEAR, y);
			calendar.set(Calendar.DAY_OF_WEEK, MONDAY);
//			Timber.d(valueOf(calendar.get(Calendar.DAY_OF_WEEK)));
			Timber.d(valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
//		}
		}

	}

	public void test() {
//		List<Point> points = PointConvert.getFakePoints();
//		Calendar calendar = getInstance();
//		List<Integer> months = new ArrayList<>(12);
//		List<Integer> accumalor = new ArrayList<>(12);
//		for (int i = 0; i < 12; i++) {
//			calendar.add(MONTH, -1);
//			months.add(calendar.get(MONTH));
//		}
//		for (Point point : points) {
//			Calendar calendar1 = getInstance();
//			calendar1.setTimeInMillis(point.getTimestamp() * 1000);
//			for (Integer month : months) {
//				calendar.set();
//			}
//		}
		List<IntervalPoint> intervalPoints = new ArrayList<>(12);
		Calendar calendar = getInstance();
		getYearIntervalPoints(intervalPoints, calendar);
		List<Point> points = FakeDataProvider.getFakePoints();
		populatePoints(intervalPoints, points);
		printPoints(intervalPoints);
	}


	public void test1() {
		List<Point> points = FakeDataProvider.getFakePoints();
		List<IntervalPoint> intervalPoints = getMonthIntervalPoints();
		populatePoints(intervalPoints, points);
	}


	public void test2() {
		List<IntervalPoint> intervalPoints = new ArrayList<>(7);
		Calendar calendar = getInstance();
		Timber.d(months[calendar.get(MONTH)]);
//		Timber.d(calendar.toString());
		getWeekIntervalPoint(intervalPoints, calendar);
		List<Point> points = FakeDataProvider.getFakePoints();
		populatePoints(intervalPoints, points);
		printPoints(intervalPoints);
	}

	private void getWeekIntervalPoint(List<IntervalPoint> intervalPoints, Calendar calendar) {
		for (int i = 0; i < getLength(Type.WEEKLY); i++) {
			long end = calendar.getTimeInMillis();
			subtract(calendar, Type.WEEKLY);
			long start = calendar.getTimeInMillis();
			final IntervalPoint intervalPoint = new IntervalPoint(start, end, calendar.get(DATE) + " " + months[calendar.get(MONTH)]);
			intervalPoints.add(intervalPoint);
		}
	}

	private List<IntervalPoint> getIntervalPoints(Type type) {
		List<IntervalPoint> intervalPoints = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < getLength(type); i++) {
			long end = calendar.getTimeInMillis();
			subtract(calendar, type);
			long start = calendar.getTimeInMillis();
			final IntervalPoint intervalPoint = new IntervalPoint(start, end, calendar.get(DATE) + " " + months[calendar.get(MONTH)]);
			intervalPoints.add(intervalPoint);
		}
		return intervalPoints;
	}


	private void getYearIntervalPoints(List<IntervalPoint> intervalPoints, Calendar calendar) {
		for (int i = 0; i < getLength(Type.YEARLY); i++) {
			long end = calendar.getTimeInMillis();
			subtract(calendar, Type.YEARLY);
			long start = calendar.getTimeInMillis();
			IntervalPoint intervalPoint = new IntervalPoint(start, end, months[calendar.get(MONTH)]);
			intervalPoints.add(intervalPoint);
		}
	}

	private List<IntervalPoint> getMonthIntervalPoints() {
		Calendar calendar = Calendar.getInstance();
		List<IntervalPoint> intervalPoints = new ArrayList<>();
		for (int i = 0; i < getLength(Type.MONTHLY); i++) {
			long end = calendar.getTimeInMillis();
			subtract(calendar, Type.MONTHLY);
			long start = calendar.getTimeInMillis();
			final IntervalPoint intervalPoint = new IntervalPoint(start, end, calendar.get(DATE) + " " + months[calendar.get(MONTH)]);
			intervalPoints.add(intervalPoint);
		}
		return intervalPoints;
	}


	private void populatePoints(List<IntervalPoint> intervalPoints, List<Point> points) {
		for (Point point : points) {
			for (IntervalPoint intervalPoint : intervalPoints) {
				if (intervalPoint.isInRange(point.getTimestamp() * 1000)) {
					intervalPoint.addValue(point.getValue());
				}
			}
		}
	}

	private void printPoints(List<IntervalPoint> intervalPoints) {
		for (IntervalPoint intervalPoint : intervalPoints) {
			Timber.d(intervalPoint.toString());
		}
	}

	private int getLength(Type type) {
		int n = 0;
		switch (type) {
			case YEARLY:
				n = 12;
				break;
			case MONTHLY:
				n = 4;
				break;
			case WEEKLY:
				n = 7;
				break;
			case DAILY:
				throw new IllegalStateException("Not yet implement");
		}
		return n;
	}

	private void subtract(Calendar calendar, Type type) {
		switch (type) {
			case YEARLY:
				calendar.add(MONTH, -1);
				break;
			case MONTHLY:
				calendar.add(Calendar.WEEK_OF_YEAR, -1);
				calendar.set(DAY_OF_WEEK, MONDAY);
				break;
			case WEEKLY:
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				break;
			case DAILY:
				throw new IllegalStateException("Not yet implement");
		}
	}
}