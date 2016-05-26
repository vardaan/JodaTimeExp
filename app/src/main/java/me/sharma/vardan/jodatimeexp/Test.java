package me.sharma.vardan.jodatimeexp;

/**
 * Created by vardaan sharma on 26/5/16.
 */
public class Test {
	private final Range range;
	private double accumaltor;
	private int frequency;
	private String label;
	private double avg;

	public Test(long start, long end, String label) {
		this.label = label;
		this.range = new Range(start, end);
	}

	public boolean isInRange(long value) {
		return value <= range.end && value >= range.start;
	}

	public void addValue(double value) {
		accumaltor += value;
		frequency++;
		avg = getAvg();
	}

	public double getAvg() {
		if (accumaltor == 0 || frequency == 0) {
			return 0;
		}
		return accumaltor / frequency;
	}

	@Override
	public String toString() {
		return "Test{" +
				"range=" + range +
				", accumaltor=" + accumaltor +
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
