package puzzle;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, PrinterException {
		String first = args[0];
		String second = args[1];
		char middle = first.charAt(0);
		List<Character> letters = new ArrayList<Character>();
		for (int i = 0; i < second.length(); i++) {
			letters.add(second.charAt(i));
		}
		letters.add(middle);
		Scanner scanner = getWords();
		Scanner otherScanner = getMoreWords();
		List<String> words = getValidWords(middle, letters, scanner);
		List<String> moreWords = getValidWords(middle, letters, otherScanner);
		Set<String> unioned = new TreeSet<String>();
		unioned.addAll(words);
		unioned.addAll(moreWords);
		for (String word: unioned) {
			System.out.println(word);
		}
		System.out.println(unioned.size());
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new Printer(unioned));
		job.print();
	}

	/*
	 * Returns if a WORD is valid, meaning has MIDDLE in it, contains only
	 * LETTERS and has more than 5 chars.
	*/
	public static boolean validWord(char middle, List<Character> letters, String word) {
		int length = word.length();
		if (length < 5) {
			return false;
		}
		if (word.indexOf(middle) == -1) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			char letter = word.charAt(i);
			if (!letters.contains(letter)) {
				return false;
			}
		}
		return true;
	}
	
	/** Returns scanner from words.txt file */
	public static Scanner getWords() throws FileNotFoundException {
		File words = new File("/usr/share/dict/words");
		return new Scanner(words);
	}

	/** Returns scanner from wordsEn.txt */
	public static Scanner getMoreWords() throws FileNotFoundException {
		File words = new File("/Users/jay/wordsEn.txt");
		return new Scanner(words);
	}
	
	/** Returns a list of valid words in SCAN, containing MIDDLE, with LETTER. */
	public static List<String> getValidWords(char middle, List<Character> letters, Scanner scan) {
		List<String> words = new LinkedList<String>();
		while (scan.hasNextLine()) {
			String word = scan.nextLine();
			word = word.toLowerCase();
			if (validWord(middle, letters, word)) {
				words.add(word);
			}
		}
		return words;
	}
}
