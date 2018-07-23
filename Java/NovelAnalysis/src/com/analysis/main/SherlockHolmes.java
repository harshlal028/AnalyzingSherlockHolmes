package com.analysis.main;

import java.io.IOException;

import com.analysis.utils.ChapterAnalysisUtility;
import com.analysis.utils.FileLoadUtility;

public class SherlockHolmes {
	
	public static void main(String[] args) throws IOException {
		String pathname = "data/1661.txt";
		String commonWords = "data/1-1000.txt";
		
		FileLoadUtility fileLoadUtility = FileLoadUtility.getFileLoadUtilityObj();
		System.out.println("Total no. of words = "+fileLoadUtility.getTotalNumberOfWords(pathname));
		System.out.println("Total no. of unique words = "+fileLoadUtility.getTotalUniqueWords(pathname));
		
		String[][] res;
		System.out.println("-- The 20 most frequent words --");
		res = fileLoadUtility.get20MostFrequentWords(pathname);
		for(String[] r : res)
			System.out.println(r[0]+"--"+r[1]);
		System.out.println("=======================================");
		System.out.println("-- The 20 most Interesting words --");
		res = fileLoadUtility.get20MostInterestingFrequentWords(commonWords, pathname);
		for(String[] r : res)
			System.out.println(r[0]+"--"+r[1]);
		System.out.println("=======================================");
		System.out.println("-- The 20 least frequent words --");
		res = fileLoadUtility.get20LeastFrequentWords(pathname);
		for(String[] r : res)
			System.out.println(r[0]+"--"+r[1]);
		
		ChapterAnalysisUtility obj = new ChapterAnalysisUtility(pathname);
		System.out.println("-- The chapterwise distribution --");
		System.out.println("\nHolmes");
		for(int val: obj.getFrequencyOfWord("Holmes"))
			System.out.print(val+" - ");
		System.out.println("\nSherlock");
		for(int val: obj.getFrequencyOfWord("Sherlock"))
			System.out.print(val+" - ");
		System.out.println("\nWatson");
		for(int val: obj.getFrequencyOfWord("Watson"))
			System.out.print(val+" - ");
		System.out.println("\nIrene");
		for(int val: obj.getFrequencyOfWord("Irene"))
			System.out.print(val+" - ");
		System.out.println("\nmystery");
		for(int val: obj.getFrequencyOfWord("mystery"))
			System.out.print(val+" - ");
		System.out.println("\nmurder");
		for(int val: obj.getFrequencyOfWord("murder"))
			System.out.print(val+" - ");
		System.out.println("\nfate");
		for(int val: obj.getFrequencyOfWord("fate"))
			System.out.print(val+" - ");
		System.out.println();
		
		System.out.println("-- Quote Search --");
		String quote = "My whole examination served to turn my conjecture into a certainty";
		System.out.println(" Quote = "+quote);
		System.out.println("Chapter found = "+obj.getChapterQuoteAppears(quote));
		System.out.println("Generated Sentence = "+obj.generateSentence(100));
	}

}
