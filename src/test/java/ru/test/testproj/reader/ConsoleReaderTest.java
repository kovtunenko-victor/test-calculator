package ru.test.testproj.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

import ru.test.testproj.error.CalcAppError;

public class ConsoleReaderTest {
	ConsoleReader reader;
	
	@Test
	public void readShouldReturnInputString() {
		String data = "1 + 1";
		reader = new ConsoleReader(new ByteArrayInputStream(data.getBytes()));
		String result = reader.read();
		
		assertEquals("1 + 1", result);
	}
	
	@Test
	public void readShouldRizeExceptionWhenInputStreamIsNull() {
		Throwable exception = assertThrows(CalcAppError.class, () -> {
			reader = new ConsoleReader(null);
			reader.read();
		});
		
		assertEquals("Input stream is null", exception.getMessage());
	}

}
