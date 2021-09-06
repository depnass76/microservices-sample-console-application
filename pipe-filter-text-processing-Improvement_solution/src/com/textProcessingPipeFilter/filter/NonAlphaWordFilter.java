package com.textProcessingPipeFilter.filter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import com.textProcessingPipeFilter.timer.ITimer;

public class NonAlphaWordFilter implements ITimer {

	/*
	 * https://www.mkyong.com/java8/java-8-streams-filter-examples/?utm_source=
	 * mkyong.com&utm_medium=Referral&utm_campaign=afterpost-related&utm_content=
	 * link3
	 *
	 * converts all words to lower case
	 *
	 * Throws away any strings that are not letters
	 * 
	 * evaluate the response times of NonAlphaWord filter in ns.
	 */

	public Stream<String> removeNonAlphaFilter(String filename) throws IOException {

		return Files.lines(Paths.get(filename)).parallel().flatMap(line -> Arrays.stream(line.trim().split(" ")))
				.map(word -> word.replaceAll("[^a-zA-Z&&[\\S]]", "").toLowerCase().trim())
				.filter(word -> word.length() > 0);

	}

	@Override
	public long getTime(String filename) throws IOException {
		filename = "alice30.txt";
		final long startTime = System.nanoTime();
		removeNonAlphaFilter(filename);
		final long endTime = System.nanoTime();
		return (endTime - startTime);
	}

	@Override
	public Long avgDuration(String filename1) throws IOException {

		filename1 = "alice30.txt";
		long total = 0;
		long start = System.nanoTime();
		removeNonAlphaFilter(filename1);
		long end = System.nanoTime();
		cycleDurations.add(end - start);

		for (Long avg : cycleDurations) {
			total += avg;

		}
		long avgValue = (total / cycleDurations.size());
		return avgValue;
	}

}
