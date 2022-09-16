package ru.test.testproj.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DigitParserTest {
	private final DigitParser parser = new DigitParser();
	
	@Test
	public void parseShouldReturnTwoDigitWhenInputHasTwoArabDigitAndOneAction() {
		String[] result = parser.parse("1+2");
		assertEquals(2, result.length);
		assertEquals("1", result[0]);
		assertEquals("2", result[1]);
	}
	
	@Test
	public void parseShouldReturnTwoDigitWhenInputHasTwoRomeDigitAndOneAction() {
		String[] result = parser.parse("I+IV");
		assertEquals(2, result.length);
		assertEquals("I", result[0]);
		assertEquals("IV", result[1]);
	}
	
	@Test
	public void parseShouldRizeExceptionWhenInputIsNull() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.parse(null);
		});
		
		assertEquals("Input data is null. Parsing is not possible", exception.getMessage());
	}
	
	@Test
	public void parseShouldRizeExceptionWhenMoreOneDigit() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.parse("1-1+1");
		});

		assertEquals("Calculation possible with only two elements", exception.getMessage());
	}
	
	@Test
	public void parseShouldRizeExceptionWhenInputHasRomeAndArabDigits() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.parse("1-V");
		});

		assertEquals("Only allowed to work with Roman or Arabic numerals", exception.getMessage());
	}
	
	@Test
	public void parseShouldRizeExceptionWhenInputHasNotProhibitedDigits() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.parse("8-22");
		});

		assertEquals("This [22] number is prohibited", exception.getMessage());
	}
}
