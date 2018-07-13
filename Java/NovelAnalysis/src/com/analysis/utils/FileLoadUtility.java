package com.analysis.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * A singleton class that does word counting functions
 * @author Harsh Lal
 *
 */
public class FileLoadUtility {
	
	private static FileLoadUtility fileLoadUtility = null;
	private HashMap<String, Integer> wordDict = null;
	private Integer uniqueWords = null;
	private Integer totalWords = null;
	
	private FileLoadUtility()
	{
		wordDict = new HashMap<>();
	}
	
	public static FileLoadUtility getFileLoadUtilityObj()
	{
		if(null == fileLoadUtility)
		{
			fileLoadUtility = new FileLoadUtility();
		}
		return fileLoadUtility;
	}
	
	private void populateHashMap(String line, HashMap<String, Integer> wordDict)
	{
		if(null == line)
			return;
		String regex = " ";
		for(String wrd : line.split(regex))
		{
			Integer count = wordDict.get(wrd);
			if(null == count)
				count = 0;
			count++;
			wordDict.put(wrd, count);
		}
	}
	
	/**
	 * This function processes file and creates the word dictionary for further usage
	 * @param filePath
	 */
	private void processFile(String filePath)
	{
		String line = null;
		File file = new File(filePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine())!= null) 
			{
				line = br.readLine();
				populateHashMap(line, wordDict);
			}
			br.close();
		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		totalWords = wordDict.values().stream().mapToInt(Integer::intValue).sum();
		uniqueWords = wordDict.keySet().size();
	}
	
	/**
	 * This function returns the total number of words in a given novel
	 * @param filePath
	 * @return
	 */
	public int getTotalNumberOfWords(String filePath)
	{
		if(null == totalWords)
			processFile(filePath);
		return totalWords;
	}
	
	/**
	 * This function returns the total number of unique words in a given novel 
	 * @param filePath
	 * @return
	 */
	public int getTotalUniqueWords(String filePath)
	{
		if(null == uniqueWords)
			processFile(filePath);
		return uniqueWords;
	}
	
	public void get20MostFrequentWords()
	{
		
	}
}
