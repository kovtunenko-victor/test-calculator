package ru.test.testproj.parser;

import ru.test.testproj.error.CalcAppError;
import ru.test.testproj.utils.DigitUtils;

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
			throw new CalcAppError("Input data is null");
		}
		
		if(digitParser == null || actionParser == null) {
			throw new CalcAppError("Actions or Digits parsers is null");
		}

		String action = actionParser.parse(data);
		String[] digits = digitParser.parse(data);
		
		if(action == null || digits == null) {
			throw new CalcAppError("Actions or Digits elements is null");
		}
		
		
		if (digits.length == 2) {
			Boolean isDigit = Character.isDigit(digits[0].charAt(0));
			
			if (action.equals("+")) {
				result = sum(isDigit, digits[0], digits[1]);
			}
			if (action.equals("-")) {
				result = diff(isDigit, digits[0], digits[1]);
			}
			if (action.equals("/")) {
				result = dev(isDigit, digits[0], digits[1]);
			}
			if (action.equals("*")) {
				result = mult(isDigit, digits[0], digits[1]);
			}
		} else {
			throw new CalcAppError("Calculation possible with only two elements");
		}

		return result;
	}
	
	private String sum(Boolean isDigit, String firstDigit, String secondDigit) {
		if(isDigit) {
			Integer result = Integer.valueOf(firstDigit) + Integer.valueOf(secondDigit);
			return result.toString();
		} else {
			Integer result = DigitUtils.toArabic(firstDigit) + DigitUtils.toArabic(secondDigit);
			return DigitUtils.toRoman(result);
		}
	}

	private String dev(Boolean isDigit, String firstDigit, String secondDigit) {
		if(isDigit) {
			if(Integer.valueOf(secondDigit) == 0) {
				throw new CalcAppError("Divide by zero is not possible");
			}
			
			Integer result = Integer.valueOf(firstDigit) / Integer.valueOf(secondDigit);
			return result.toString();
		} else {
			Integer result = DigitUtils.toArabic(firstDigit) / DigitUtils.toArabic(secondDigit);
			return DigitUtils.toRoman(result);
		}
	}

	private String mult(Boolean isDigit, String firstDigit, String secondDigit) {
		if(isDigit) {
			Integer result = Integer.valueOf(firstDigit) * Integer.valueOf(secondDigit);
			return result.toString();
		} else {
			Integer result = DigitUtils.toArabic(firstDigit) * DigitUtils.toArabic(secondDigit);
			return DigitUtils.toRoman(result);
		}
	}

	private String diff(Boolean isDigit, String firstDigit, String secondDigit) {
		if(isDigit) {
			Integer result = Integer.valueOf(firstDigit) - Integer.valueOf(secondDigit);
			return result.toString();
		} else {
			Integer result = DigitUtils.toArabic(firstDigit) - DigitUtils.toArabic(secondDigit);
			
			if(result < 0) {
				return "-" + DigitUtils.toRoman(Math.abs(result));
			} else {
				return DigitUtils.toRoman(result);
			}
		}
	}
}
