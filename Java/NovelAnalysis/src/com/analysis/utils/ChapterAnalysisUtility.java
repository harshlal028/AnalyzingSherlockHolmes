package com.analysis.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChapterAnalysisUtility {
	
	List<String> chapters = null;
	
	public ChapterAnalysisUtility(String filePath) {
		chapters = new ArrayList<>();
		loadChapters(filePath);
	}
	
	private void loadChapters(String filePath) {
		List<String> chapterList = new ArrayList<>(Arrays.asList("I. A Scandal in Bohemia",
																"II. The Red-headed League",
																"III. A Case of Identity",
																"IV. The Boscombe Valley Mystery",
																"V. The Five Orange Pips",
																"VI. The Man with the Twisted Lip",
																"VII. The Adventure of the Blue Carbuncle",
																"VIII. The Adventure of the Speckled Band",
																"IX. The Adventure of the Engineer's Thumb",
																"X. The Adventure of the Noble Bachelor",
																"XI. The Adventure of the Beryl Coronet",
																"XII. The Adventure of the Copper Beeches",
																"END OF THIS PROJECT GUTENBERG EBOOK THE ADVENTURES OF SHERLOCK HOLMES"));
		
		int currentChapter = 0;
		int nextChapter = currentChapter+1;
		String line = null;
		File file = new File(filePath);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			int lineCount = 0;
			StringBuffer tempStr = new StringBuffer();
			while((line = br.readLine())!= null) 
			{
				lineCount++;
				if(lineCount < 60) //The line after which chapter begins
				{
					continue;
				}
				
				if(nextChapter == chapterList.size())
					break;
				tempStr.append(line);
				if(line.toLowerCase().contains(chapterList.get(nextChapter).toLowerCase()))
				{
					chapters.add(currentChapter, tempStr.toString());
					currentChapter = nextChapter;
					nextChapter = currentChapter+1;
					tempStr = new StringBuffer();
				}
			}
			br.close();
		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		System.out.println("File Split to Chapters Completed!");		
	}
	
	private int getFrequency(String chapter, String word)
	{
		int count = 0;
		for(String ch : chapter.split(FileLoadUtility.REGEX))
		{
			if(ch.equals(word))
				count++;
		}
		return count;
	}
	
	public int[] getFrequencyOfWord(String word)
	{
		int[] res = new int[chapters.size()];
		for(int i=0; i<res.length; i++)
		{
			res[i] = getFrequency(chapters.get(i), word);
		}
		return res;
	}

}
