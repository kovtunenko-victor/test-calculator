package ru.test.testproj.parser;

import ru.test.testproj.utils.Roman2Arabic;

public class CalculatorProviderImpl implements CalculatorProvider<String, String> {
	private final Parser<String, String> actionParser;
	private final Parser<String[], String> digitParser;

	public CalculatorProviderImpl(Parser<String, String> actionParser, Parser<String[], String> digitParser) {
		this.actionParser = actionParser;
		this.digitParser = digitParser;
	}

	@Override
	public String calculate(String data) {
		String result = "";
		
		if(data == null) {
			throw new IllegalArgumentException("Input data is null");
		}
		
		if(digitParser == null || actionParser == null) {
			throw new IllegalArgumentException("Actions or Digits parsers is null");
		}

		String action = actionParser.parse(data);
		String[] digits = digitParser.parse(data);
		
		if(action == null || digits == null) {
			throw new IllegalArgumentException("Actions or Digits elements is null");
		}
		
		
		if (digits.length == 2) {
			Boolean isDigit = Character.isDigit(digits[0].charAt(0));
			
			if (action.equals("+")) {
				if(isDigit) {
					result = sum(Integer.valueOf(digits[0].split(" ")[0]), Integer.valueOf(digits[1].split(" ")[0])).toString();
				} else {
					result = Roman2Arabic.toRoman(sum(Roman2Arabic.toArabic(digits[0]), Roman2Arabic.toArabic(digits[1])));
				}
			}
			if (action.equals("-")) {
				if(isDigit) {
					result = diff(Integer.valueOf(digits[0].split(" ")[0]), Integer.valueOf(digits[1].split(" ")[0])).toString();
				} else {
					if(Roman2Arabic.toArabic(digits[0]) < Roman2Arabic.toArabic(digits[1])) {
						result = "-" + Roman2Arabic.toRoman(Math.abs(diff(Roman2Arabic.toArabic(digits[0]), Roman2Arabic.toArabic(digits[1]))));
					} else {
						result = Roman2Arabic.toRoman(diff(Roman2Arabic.toArabic(digits[0]), Roman2Arabic.toArabic(digits[1])));
					}
				}
			}
			if (action.equals("/")) {
				if(isDigit) {
					result = dev(Integer.valueOf(digits[0].split(" ")[0]), Integer.valueOf(digits[1].split(" ")[0])).toString();
				} else {
					result = Roman2Arabic.toRoman(dev(Roman2Arabic.toArabic(digits[0]), Roman2Arabic.toArabic(digits[1])));
				}
			}
			if (action.equals("*")) {
				if(isDigit) {
					result = mult(Integer.valueOf(digits[0].split(" ")[0]), Integer.valueOf(digits[1].split(" ")[0])).toString();
				} else {
					result = Roman2Arabic.toRoman(mult(Roman2Arabic.toArabic(digits[0]), Roman2Arabic.toArabic(digits[1])));
				}
			}
		} else {
			throw new IllegalArgumentException("Calculation possible with only two elements");
		}

		return result;
	}

	private Integer sum(int first, int second) {
		return first + second;
	}

	private Integer dev(int first, int second) {
		return first / second;
	}

	private Integer mult(int first, int second) {
		return first * second;
	}

	private Integer diff(int first, int second) {
		return first - second;
	}
}
