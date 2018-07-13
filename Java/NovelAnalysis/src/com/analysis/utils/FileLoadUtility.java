package com.analysis.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A singleton class that does word counting functions
 * @author Harsh Lal
 *
 */
public class FileLoadUtility {
	
	public static final String REGEX = "[ ,\",.,(,),1-9,#,!]";
	private static FileLoadUtility fileLoadUtility = null;
	private Map<String, Integer> wordDict = null;
	private Map<String, Integer> revWordDict = null;
	private Map<String, Integer> commonWordDict = null;
	private Map<String, Integer> filteredWordDict = null;
	
	private Integer uniqueWords = null;
	private Integer totalWords = null;
	private boolean sorted = false;
	
	private FileLoadUtility()
	{
		wordDict = new HashMap<>();
		commonWordDict = new LinkedHashMap<>();
		filteredWordDict = new HashMap<>();
	}
	
	public static FileLoadUtility getFileLoadUtilityObj()
	{
		if(null == fileLoadUtility)
		{
			fileLoadUtility = new FileLoadUtility();
		}
		return fileLoadUtility;
	}
	
	private void populateHashMap(String line, Map<String, Integer> wordDict)
	{
		if(null == line)
			return;
		for(String wrd : line.split(REGEX))
		{
			if(wrd.isEmpty())
				continue;
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
	private void processFile(String filePath, Map<String, Integer> wordDict)
	{
		String line = null;
		File file = new File(filePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine())!= null) 
			{
				populateHashMap(line, wordDict);
			}
			br.close();
		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	private void processFileFiltered(String filePath, Map<String, Integer> wordDict, Map<String, Integer> filteredWordDict)
	{
		String line = null;
		File file = new File(filePath);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine())!= null) 
			{
				if(null != line)
				for(String wrd : line.split(REGEX))
				{
					if(wrd.isEmpty())
						continue;
					Integer c = wordDict.get(wrd);
					if(null == c)
					{
						c = filteredWordDict.get(wrd);
						if(null == c)
							c = 0;
						c++;
						filteredWordDict.put(wrd, c);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * This function returns the total number of words in a given novel
	 * @param filePath
	 * @return
	 */
	public int getTotalNumberOfWords(String filePath)
	{
		if(null == totalWords)
		{
			if(wordDict.size() == 0)
			{
				processFile(filePath, wordDict);
			}
			totalWords = wordDict.values().stream().mapToInt(Integer::intValue).sum();
		}
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
		{
			if(wordDict.size() == 0)
			{
				processFile(filePath, wordDict);
			}
			uniqueWords = wordDict.keySet().size();
		}
		return uniqueWords;
	}
	
	public String[][] get20MostFrequentWords(String filePath)
	{
		if(wordDict.size() == 0)
		{
			processFile(filePath, wordDict);
		}
		String[][] freqWords = new String[20][2];
		if(!sorted)
		{
			Map<String, Integer> result = new LinkedHashMap<>();
			wordDict.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
			wordDict = result;
			sorted = true;
		}
		int counter = 0;
		for(String word: wordDict.keySet())
		{
			freqWords[counter][0] = word;
			freqWords[counter][1] = wordDict.get(word).toString();
			if(++counter == 20)
				break;
		}
		return freqWords;
	}
	
	public String[][] get20MostInterestingFrequentWords(String commonWordsFile, String filePath)
	{
		String[][] freqWords = new String[20][2];
		int counter = 0;
		
		if(commonWordDict.size() == 0)
		{	
			String line = null;
			File file = new File(commonWordsFile);
			int count = 300;
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				while((line = br.readLine())!= null && count > 0) 
				{
					commonWordDict.put(line, 0);
					count--;
				}
				br.close();
			} catch (IOException e) {
				//e.printStackTrace();
				System.err.println(e.getMessage());
			}
			
			processFileFiltered(filePath, commonWordDict, filteredWordDict);
			Map<String, Integer> result = new LinkedHashMap<>();
			filteredWordDict.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
			filteredWordDict = result;
		}
		
		for(String word: filteredWordDict.keySet())
		{
			freqWords[counter][0] = word;
			freqWords[counter][1] = filteredWordDict.get(word).toString();
			if(++counter == 20)
				break;
		}
		return freqWords;
	}
	
	public String[][] get20LeastFrequentWords(String filePath)
	{
		String[][] freqWords = new String[20][2];
		int counter = 0;
		if(wordDict.size() == 0)
		{
			processFile(filePath, wordDict);
		}
		if(null == revWordDict)
		{
			revWordDict = new LinkedHashMap<>();
			wordDict.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue())
                .forEachOrdered(x -> revWordDict.put(x.getKey(), x.getValue()));
		}
		
		for(String word: revWordDict.keySet())
		{
			freqWords[counter][0] = word;
			freqWords[counter][1] = revWordDict.get(word).toString();
			if(++counter == 20)
				break;
		}
		return freqWords;
	}
}
