package com.textProcessingPipeFilter.timer;

public class Stopwatch {

	private long startTime = -1;
	private long stopTime = -1;
	private boolean running = false;

	public Stopwatch start() {
		startTime = System.nanoTime();
		running = true;
		return this;
	}

	public Stopwatch stop() {
		stopTime = System.nanoTime();
		running = false;
		return this;
	}

	public long getElapsedTime() {
		if (startTime == -1) {
			return 0;
		}
		if (running) {
			return System.nanoTime() - startTime;
		} else {
			return stopTime - startTime;
		}
	}

	public Stopwatch reset() {
		startTime = -1;
		stopTime = -1;
		running = false;
		return this;
	}

}
