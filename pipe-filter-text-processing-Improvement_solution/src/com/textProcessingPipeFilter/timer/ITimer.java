package com.textProcessingPipeFilter.timer;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Using this interface to evaluate response time of filters
 */
public interface ITimer {
	public ArrayList<Long> cycleDurations = new ArrayList<>();

	public long getTime(String str) throws IOException;

	public Long avgDuration(String str) throws IOException;

}
