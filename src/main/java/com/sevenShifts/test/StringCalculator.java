package com.sevenShifts.test;

import java.util.ArrayList;
import java.util.List;


public class StringCalculator {
//	public static void main(String[] args) throws Exception {
//		String strNumber = "//$\n1$2$3";
////		System.out.println(AddWithPatternMatcher(strNumber));
//		// System.out.println(Add_3("//$\n1$2$3"));
//		System.out.println(Add_3("//$,@\n1$2@3"));
//		System.out.println(Add_3("//$\n1$2$3"));
//		System.out.println(Add_3("//$$$\n1$$$2$$$3"));
//		System.out.println(Add_3("1,2,3"));
//
//	}

	public int Add(String numbers) throws Exception {
		int sum = 0;
		if (numbers.isEmpty() || numbers == null) { // b. Empty strings should return 0.
			return 0;
		}
		
		if(numbers.length() == 1) {
			return Integer.parseInt(numbers);
		}

		String newDelimiter = null;

		newDelimiter = extractDelimiter(numbers);

		if (newDelimiter.equals(",")) {	// default delimiter 
			sum = calculateSumByDelimiter(numbers, ",");
		} else {
			sum = calculateSumByDelimiter(numbers, newDelimiter);
		}
		return sum;
	}

	private String extractDelimiter(String numbers) {
		int delCount = 0;

		if(!numbers.startsWith("//")) {
			return ",";
		}
		
		if (numbers.startsWith("//") & numbers.contains(",")) {	// check for custom delimiter

			for (int j = 0; j < numbers.length(); j++) {	
				if (numbers.charAt(j) == ',')
					delCount++;
			}
			if (delCount != 1) {			
				return ",";
			} else {
				String[] multiDel = numbers.split("\n");
				return multiDel[0];
			}
		} else {
			String[] del = numbers.split("\n");
			return del[0];
		}
	}

	private int calculateSumByDelimiter(String numbers, String delimiter) throws Exception {
		List<String> lstNagativeNumbers = new ArrayList<String>();
		int sum = 0;
		int currentNumber;
		int index = 1;
		String[] numArray = {};
		if(delimiter.equals(",")) {
			numArray = numbers.split(",");
			index = 0;
		}else if (delimiter.length() > 1) {
			numbers = numbers.replaceAll("//", "");
			
			String[] mulDel = {};
			if(delimiter.contains(",")) {
				index = 0;
				mulDel = numbers.split("\n")[0].split(",");
				numArray = numbers.split("\n")[1].split("[\\" + mulDel[0] +"|" + mulDel[1] + "]+"); // The numbers in the string are separated by a
			}else {
				numbers = numbers.replaceAll("\n", "");
				numArray = numbers.split("[\\" + delimiter + "]+"); // The numbers in the string are separated by a	
			}
		} else {
			numArray = numbers.split("\\" + delimiter); // The numbers in the string are separated by a delimiter.
		}
		
		for (int i = index; i < numArray.length; i++) {
			currentNumber = Integer.parseInt(numArray[i]);
			if (currentNumber < 1) {
				lstNagativeNumbers.add(numArray[i]);
			} else if (currentNumber >= 1000) {
				continue;
			} else {
				sum = sum + Integer.parseInt(numArray[i]);
			}

		}
		isNegativeFound(lstNagativeNumbers);

		return sum;
	}

	private void isNegativeFound(List<String> lstNagativeNumbers) throws Exception {
		if (!lstNagativeNumbers.isEmpty()) {
			System.out.println("Negative Numbers: " + lstNagativeNumbers);
			throw new Exception("Negatives not allowed.");
		}
	}

}
