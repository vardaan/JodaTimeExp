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
		List<Test> tests = new ArrayList<>(12);
		Calendar calendar = getInstance();
		for (int i = 0; i < 12; i++) {
			long end = calendar.getTimeInMillis();
			calendar.add(MONTH, -1);
			long start = calendar.getTimeInMillis();
			Test test = new Test(start, end, months[calendar.get(MONTH)]);
			tests.add(test);
		}
		List<Point> points = PointConvert.getFakePoints();
		for (Point point : points) {
			for (Test test : tests) {
				if (test.isInRange(point.getTimestamp() * 1000)) {
					test.addValue(point.getValue());
				}
			}
		}
		for (Test test : tests) {
			Timber.d(test.toString());
		}
	}

	public void test1() {
		List<Test> tests = new ArrayList<>(12);
		Calendar calendar = getInstance();
		Timber.d(calendar.toString());
		for (int i = 0; i < 4; i++) {
			long end = calendar.getTimeInMillis();
			calendar.add(Calendar.WEEK_OF_YEAR, -1);
			calendar.set(DAY_OF_WEEK, MONDAY);
			long start = calendar.getTimeInMillis();
			final Test test = new Test(start, end, calendar.get(DATE) + " " + months[calendar.get(MONTH)]);
			tests.add(test);
		}
		List<Point> points = PointConvert.getFakePoints();
		for (Point point : points) {
			for (Test test : tests) {
				if (test.isInRange(point.getTimestamp() * 1000)) {
					test.addValue(point.getValue());
				}
			}
		}
		for (Test test : tests) {
			Timber.d(test.toString());
		}
	}

	public void test2() {
		List<Test> tests = new ArrayList<>(7);
		Calendar calendar = getInstance();
		Timber.d(months[calendar.get(MONTH)]);
//		Timber.d(calendar.toString());
		for (int i = 0; i < 7; i++) {
			long end = calendar.getTimeInMillis();
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			long start = calendar.getTimeInMillis();
			final Test test = new Test(start, end, calendar.get(DATE) + " " + months[calendar.get(MONTH)]);
			tests.add(test);
		}
		List<Point> points = PointConvert.getFakePoints();
		for (Point point : points) {
			for (Test test : tests) {
				if (test.isInRange(point.getTimestamp() * 1000)) {
					test.addValue(point.getValue());
				}
			}
		}
		for (Test test : tests) {
			Timber.d(test.toString());
		}
	}


}