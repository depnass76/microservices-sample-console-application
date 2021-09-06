package com.textProcessingPipeFilter.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.textProcessingPipeFilter.ReadTextFile.ComputeWordsFrequency;
import com.textProcessingPipeFilter.Stemmer.Stemmer;
import com.textProcessingPipeFilter.filter.NonAlphaWordFilter;
import com.textProcessingPipeFilter.filter.StopWords;
import com.textProcessingPipeFilter.timer.Stopwatch;

/*
 * [Filter], [readFile], [Stmmer]
 * [Source]  <word string> 
 * {Remove non-alphas}  <word string> -> {Make Lower Case} -<word string> 
 * {Remove Stop Words}  <word string > -> {Stem to root terms} -<word string > 
 * {Compute term frequency}  <string, int hashmap >
 * {Sort by Frequency}  <word string > -> {take top 10 words} -<word string > 
 * {output 10 most commonly occurring terms}
 * {output and evaluate the response times of StopWord filter, NonAlphaWord filter, and Stmmer filter in ns.
 */

public class Main {

	public static void main(String args[]) throws Exception {

		NonAlphaWordFilter _noalphawords = new NonAlphaWordFilter();
		StopWords _stopwords = new StopWords();
		Stemmer stemmer = new Stemmer();
		List<String> stopWords = _stopwords.stopWords("stopwords.txt");
		Stream<String> words_usdeclar = _noalphawords.removeNonAlphaFilter("usdeclar.txt");
		Stream<String> words_alice30 = _noalphawords.removeNonAlphaFilter("alice30.txt");
		Stream<String> words_kjbible = _noalphawords.removeNonAlphaFilter("kjbible.txt");

		try {

			Map<String, Integer> wordCount = new HashMap<>();

			/*
			 * https://www.mkyong.com/java8/java-8-streams-filter-examples/?utm_source=
			 * mkyong.com&utm_medium=Referral&utm_campaign=afterpost-related&utm_content=
			 * 
			 * link3 Stermming filter that converts word into just their roots based on the
			 * Porter stemming algorithm
			 *
			 * https://javarevisited.blogspot.com/2018/05/java-8-filter-map-collect-stream-
			 * example.html
			 */
			words_usdeclar.filter((word) -> !stopWords.contains(word)).map(stemmer::stemWord).forEach((word) -> {
				if (wordCount.containsKey(word)) {
					int incCount = wordCount.get(word) + 1;
					wordCount.put(word, incCount);
				} else {
					wordCount.put(word, 1);
				}
			});

			List<Map.Entry<String, Integer>> list2 = new ArrayList<>(wordCount.entrySet());
			list2.sort(new ComputeWordsFrequency());
			System.out.printf("%-40s%-20s%s\n",
					("Process top 10 most frequent terms of usdeclar.txt :" + list2.subList(0, 10) + ":"),
					("Runtime: " + _noalphawords.getTime("alice30.txt") + "ns"),
					("Average response time: " + _noalphawords.avgDuration("alice30.txt") + "ns"));

			/*
			 * https://www.mkyong.com/java8/java-8-streams-filter-examples/?utm_source=
			 * mkyong.com&utm_medium=Referral&utm_campaign=afterpost-related&utm_content=
			 * 
			 * link3 Stermming filter that converts word into just their roots based on the
			 * Porter stemming algorithm
			 *
			 * https://javarevisited.blogspot.com/2018/05/java-8-filter-map-collect-stream-
			 * example.html
			 */
			words_alice30.filter((word) -> !stopWords.contains(word)).map(stemmer::stemWord).forEach((word) -> {
				if (wordCount.containsKey(word)) {
					int incCount = wordCount.get(word) + 1;
					wordCount.put(word, incCount);
				} else {
					wordCount.put(word, 1);
				}
			});

			List<Map.Entry<String, Integer>> list1 = new ArrayList<>(wordCount.entrySet());
			list1.sort(new ComputeWordsFrequency());
			System.out.printf("%-40s%-20s%s\n",
					("Process top 10 most frequent terms of alice30.txt :" + list1.subList(0, 10) + ":"),
					("Runtime: " + _noalphawords.getTime("alice30.txt") + "ns"),
					("Average response time: " + _noalphawords.avgDuration("alice30.txt") + "ns"));
			/*
			 * https://www.mkyong.com/java8/java-8-streams-filter-examples/?utm_source=
			 * mkyong.com&utm_medium=Referral&utm_campaign=afterpost-related&utm_content=
			 * 
			 * link3 Stermming filter that converts word into just their roots based on the
			 * Porter stemming algorithm
			 * 
			 * https://javarevisited.blogspot.com/2018/05/java-8-filter-map-collect-stream-
			 * example.html
			 */
			words_kjbible.filter((word) -> !stopWords.contains(word)).map(stemmer::stemWord).forEach((word) -> {
				if (wordCount.containsKey(word)) {
					int incCount = wordCount.get(word) + 1;
					wordCount.put(word, incCount);
				} else {
					wordCount.put(word, 1);
				}
			});

			List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());
			list.sort(new ComputeWordsFrequency());
			System.out.printf("%-40s%-20s%s\n",
					("Process top 10 most frequent terms of kjbible.txt :" + list.subList(0, 10) + ":"),
					("Runtime: " + _noalphawords.getTime("alice30.txt") + "ns"),
					("Average response time: " + _noalphawords.avgDuration("alice30.txt") + "ns"));
			System.out.println();

			System.out.println("NonAlphaWordsFilter runtime:" + _noalphawords.getTime("alice30.txt") + " ns " + " and "
					+ "Average response time: " + _noalphawords.avgDuration("alice30.txt") + " ns");

			System.out.println("StopWords runtime:" + _stopwords.getTime("alice30.txt") + " ns " + " and "
					+ "Average response time: " + _stopwords.avgDuration("alice30.txt") + " ns");

			System.out.println("Stemmer runtime:" + stemmer.getTime("alice30.txt") + " ns " + " and "
					+ "Average response time: " + stemmer.avgDuration("alice30.txt") + " ns");
			System.out.println();

			System.out.println("Press Enter to countinue...");
			System.in.read();

			textProcessImprovedRunningTime("alice30.txt");
			// textProcessImprovedRunningTime("kjbible.txt");
			// textProcessImprovedRunningTime("usdeclar.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void textProcessImprovedRunningTime(String filename) throws IOException {
		NonAlphaWordFilter _noalphawords = new NonAlphaWordFilter();
		StopWords _stopwords = new StopWords();
		Stemmer stemmer = new Stemmer();
		Stopwatch s = new Stopwatch();

		s.start();
		_noalphawords.removeNonAlphaFilter(filename);
		s.stop();

		System.out.println("Improved running time....\n");
		System.out.println("NonAlphaWordsFilter runtime in text file alice30.txt : " + s.getElapsedTime() + " ns"
				+ " and Average response time: " + _noalphawords.avgDuration("alice30.txt") + " ns");

		s.start();
		_stopwords.stopWords(filename);
		s.stop();
		System.out.println("StopWords runtime:in text file alice30.txt : " + s.getElapsedTime() + " ns"
				+ " and Average response time: " + _stopwords.avgDuration("alice30.txt") + " ns");

		s.start();
		stemmer.stemWord(filename);
		s.stop();
		System.out.println("Stemmer runtime in text file alice30.txt : " + s.getElapsedTime() + " ns"
				+ " and Average response time: " + stemmer.avgDuration("alice30.txt") + " ns");

	}

}
