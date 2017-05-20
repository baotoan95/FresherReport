package com.fresher.report.entities;

public class ClassStatistic {
	private String name;
	private long total;
	private long release;
	private long running;

	public ClassStatistic() {
		this.name = "";
		this.total = 0;
		this.release = 0;
		this.running = 0;
	}

	public ClassStatistic(String name, long total, long release, long running) {
		super();
		this.name = name;
		this.total = total;
		this.release = release;
		this.running = running;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getRelease() {
		return release;
	}

	public void setRelease(long release) {
		this.release = release;
	}

	public long getRunning() {
		return running;
	}

	public void setRunning(long running) {
		this.running = running;
	}

}
