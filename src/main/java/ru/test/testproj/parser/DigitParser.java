package ru.test.testproj.parser;

import java.util.Arrays;

import ru.test.testproj.utils.Roman2Arabic;

public class DigitParser implements Parser<String[], String> {
	
	@Override
	public String[] parse(String data) {
		if(data == null) {
			throw new IllegalArgumentException("Input data is null. Parsing is not possible");
		}
		
		if(data.split(AVAILABLE_ACTIONS_PATTERN).length != ELEMENT_COUNT) {
			throw new IllegalArgumentException("Calculation possible with only two elements");
		}

		return getDigits(data);
	}
	
	private String[] getDigits(String data) {
		String[] digits = new String[1];
		Boolean isDigit = null;
		
		for(String symbol : splitLine(data)) {
			if(isDigit == null) {
				isDigit = Character.isDigit(symbol.trim().charAt(0));
			}

			if(digits[digits.length - 1] != null) {
				digits = Arrays.copyOf(digits, digits.length + 1);
			}
			
			digits[digits.length - 1] = сheckDigit(isDigit, symbol.trim());
		}
		
		return digits;
	}
	
	private String сheckDigit(Boolean isDigit, String digit) {
		boolean isExists = false;
		int digitInt;
		
		if(isDigit != Character.isDigit(digit.charAt(0))) {
			throw new IllegalArgumentException("Only allowed to work with Roman or Arabic numerals");
		}
		
		if(isDigit) {
			digitInt = Integer.valueOf(digit.split(" ")[0]);
		} else {
			digitInt = Roman2Arabic.toArabic(digit);
		}
		
		for(int d : AVAILABLE_DIGITS) {
			if(digitInt == d) {
				isExists = true;
				break;
			}
		}
		
		if(isExists  == false) {
			throw new IllegalArgumentException(String.format("This [%s] number is prohibited", digit));
		}
		
		return digit;
	}
	
	private String[] splitLine(String line) {
		return line.split(AVAILABLE_ACTIONS_PATTERN);
	}
}
