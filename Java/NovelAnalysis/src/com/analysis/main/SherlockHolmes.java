package com.analysis.main;

import java.io.IOException;

import com.analysis.utils.ChapterAnalysisUtility;
import com.analysis.utils.FileLoadUtility;

public class SherlockHolmes {
	
	public static void main(String[] args) {
		String pathname = "data/1661.txt";
		ChapterAnalysisUtility obj = new ChapterAnalysisUtility(pathname);
		for(int val: obj.getFrequencyOfWord("Holmes"))
			System.out.print(val+" - ");
		System.out.println();
		for(int val: obj.getFrequencyOfWord("Sherlock"))
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
