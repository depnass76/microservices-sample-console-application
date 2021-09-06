package com.textProcessingPipeFilter.ReadTextFile;

import java.util.Comparator;
import java.util.Map;

/*
 * computers the frequency of each word  and outputs a hashmap of the words and their frequencies
 */
public class ComputeWordsFrequency implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
		// sort frequency in descending order
		int it = o2.getValue().compareTo(o1.getValue());
		if (it == 0) {
			// sort equally frequent items in alphabetical order
			it = o1.getKey().compareTo(o2.getKey());
		}
		return it;
	}
}