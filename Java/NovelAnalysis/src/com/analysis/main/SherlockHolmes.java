package com.analysis.main;

import java.io.IOException;

import com.analysis.utils.ChapterAnalysisUtility;
import com.analysis.utils.FileLoadUtility;

public class SherlockHolmes {
	
	public static void main(String[] args) {
		String pathname = "data/1661.txt";
		ChapterAnalysisUtility obj = new ChapterAnalysisUtility(pathname);
		String quote = "Then I trust that you at least will honour me with your company";
		System.out.println(obj.getChapterQuoteAppears(quote));
		System.out.println(obj.generateSentence(100));
	}
	
	public static void main2(String[] args) {
		String pathname = "data/1661.txt";
		ChapterAnalysisUtility obj = new ChapterAnalysisUtility(pathname);
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
	}
	
	public static void main1(String[] args) throws IOException {
		String pathname = "data/1661.txt";
		String commonWords = "data/1-1000.txt";
		FileLoadUtility fileLoadUtility = FileLoadUtility.getFileLoadUtilityObj();
		System.out.println(fileLoadUtility.getTotalNumberOfWords(pathname));
		System.out.println(fileLoadUtility.getTotalUniqueWords(pathname));
		
		String[][] res; 
		res = fileLoadUtility.get20MostFrequentWords(pathname);
		for(String[] r : res)
			System.out.println(r[0]+"--"+r[1]);
		System.out.println("=======================================");
		res = fileLoadUtility.get20MostInterestingFrequentWords(commonWords, pathname);
		for(String[] r : res)
			System.out.println(r[0]+"--"+r[1]);
		System.out.println("=======================================");
		res = fileLoadUtility.get20LeastFrequentWords(pathname);
		for(String[] r : res)
			System.out.println(r[0]+"--"+r[1]);
	}

}
