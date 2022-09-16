package ru.test.testproj.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ActionParserTest {
	private final ActionParser parser = new ActionParser();

	@Test
	public void parseShouldReturnOneActionWhenInputHasAvailableAction() {
		String result = parser.parse("1+1");
		assertEquals("+", result);
	}

	@Test
	public void parseShouldRizeExceptionWhenMoerOneAction() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.parse("1-1+");
		});

		assertEquals("Calculation possible with only one action", exception.getMessage());
	}

	@Test
	public void parseShouldRizeExceptionWhenActionNotSet() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.parse("11");
		});
		
		assertEquals("No available action set", exception.getMessage());
	}
	
	@Test
	public void parseShouldRizeExceptionWhenSetNotAvailableAction() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.parse("1^1");
		});
		
		assertEquals("No available action set", exception.getMessage());
	}
	
	@Test
	public void parseShouldRizeExceptionWhenInputIsNull() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.parse(null);
		});
		
		assertEquals("Input data is null. Parsing is not possible", exception.getMessage());
	}
}
