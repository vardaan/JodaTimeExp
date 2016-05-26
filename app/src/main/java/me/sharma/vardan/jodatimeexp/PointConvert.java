package me.sharma.vardan.jodatimeexp;

import com.db.chart.model.LineSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

/**
 * Created by vardaan sharma on 25/5/16.
 */
//todo better name
public class PointConvert {
	private static
	final String[] labelsX3 =
			{"Jan", "Feb", "Mar", "Apr", "Jun", "May", "Jul", "Aug", "Sep", "oct", "nov", "dec"};

	public static LineSet getPoints() {
		List<Point> points = getFakePoints();


		Timber.d("Generating points");
		for (int i = 0; i < points.size(); i++) {
			Timber.d(points.get(i).toString());
		}
		Timber.d("Points generation completed");
		Date date;


		double[] accumulator = new double[12];
		double[] number = new double[12];

		date = new Date();
		for (Point point : points) {
			date.setTime(point.getTimestamp() * 1000);
			accumulator[date.getMonth()] += point.getValue();
			number[date.getMonth()] += 1;
		}

		// compute avg from the by dividing accumlator and number of values
		final float[] finalValues = new float[12];
		for (int i = 0; i < finalValues.length; i++) {
			if (number[i] == 0 || accumulator[i] == 0)
				finalValues[i] = 0;
			else
				finalValues[i] = (float) (accumulator[i] / number[i]);
		}
		return new LineSet(labelsX3, finalValues);
	}

	public static List<Point> getFakePoints() {
		List<Point> points = new ArrayList<>(10000);
		for (int i = 0; i < 3000; i++) {

			final long timestamp = 1464176083 - (int) (Math.random() * 217728000);
			final float value = (float) (Math.random() * 100);
			points.add(new Point(timestamp, value));
		}
		return points;
	}

}
