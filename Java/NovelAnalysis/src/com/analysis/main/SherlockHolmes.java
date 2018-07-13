package com.analysis.main;

import java.io.IOException;

import com.analysis.utils.FileLoadUtility;

public class SherlockHolmes {
	
	public static void main(String[] args) throws IOException {
		String pathname = "data/1661.txt";
		FileLoadUtility fileLoadUtility = FileLoadUtility.getFileLoadUtilityObj();
		System.out.println(fileLoadUtility.getTotalNumberOfWords(pathname));
		System.out.println(fileLoadUtility.getTotalUniqueWords(pathname));
	}

}
