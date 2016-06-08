package me.sharma.vardan.jodatimeexp;

/**
 * Created by vardaan sharma on 26/5/16.
 */
public class IntervalPoint {
	private final Range range;
	private double accumulator;
	private int frequency;
	private String label;
	private double avg;

	public IntervalPoint(long start, long end, String label) {
		this.label = label;
		this.range = new Range(start, end);
	}

	public boolean isInRange(long value) {
		return value <= range.end && value >= range.start;
	}

	public void addValue(double value) {
		accumulator += value;
		frequency++;
		avg = getAvg();
	}

	public double getAvg() {
		if (accumulator == 0 || frequency == 0) {
			return 0;
		}
		return accumulator / frequency;
	}

	@Override
	public String toString() {
		return "Test{" +
				"range=" + range +
				", accumulator=" + accumulator +
				", frequency=" + frequency +
				", label='" + label + '\'' +
				", avg=" + avg +
				'}';
	}

	class Range {
		final long start;
		final long end;

		Range(long start, long end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Range{" +
					"start=" + start +
					", end=" + end +
					'}';
		}
	}
}
