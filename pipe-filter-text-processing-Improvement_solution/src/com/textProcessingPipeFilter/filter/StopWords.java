package com.textProcessingPipeFilter.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.textProcessingPipeFilter.timer.ITimer;

public class StopWords implements ITimer {

	/*
	 * takes a file with a list of words. throws away any word that matches a word
	 * on the list.
	 * 
	 * evaluate the response times of StopWord filter in ns.
	 */

	public List<String> stopWords(String filename) throws IOException {
		List<String> stopWords = new LinkedList<>();

		FileReader fr = new FileReader(filename);
		BufferedReader bufr = new BufferedReader(fr);

		String line = bufr.readLine();

		while (line != null) {
			stopWords.add(line);
			line = bufr.readLine();
		}

		bufr.close();

		return stopWords;
	}

	@Override
	public long getTime(String filename) throws IOException {
		filename = "alice30.txt";
		final long startTime = System.nanoTime();
		stopWords(filename);
		final long endTime = System.nanoTime();
		return (endTime - startTime);
	}

	@Override
	public Long avgDuration(String filename3) throws IOException {

		filename3 = "alice30.txt";
		long total = 0;
		long start = System.nanoTime();
		stopWords(filename3);
		long end = System.nanoTime();
		cycleDurations.add(end - start);

		for (Long avg : cycleDurations) {
			total += avg;

		}
		long avgValue = (total / cycleDurations.size());
		return avgValue;
	}

}
