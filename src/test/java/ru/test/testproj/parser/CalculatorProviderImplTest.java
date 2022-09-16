package ru.test.testproj.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorProviderImplTest {
	private CalculatorProviderImpl calculator;
	
	@Mock
	private ActionParser actionParser;
	
	@Mock
	private DigitParser digitParser;
	
	@Test
	public void calculateShouldReturnSumByTwoArabDigit() {
		String input = "1+3";
		
		when(actionParser.parse(input)).thenReturn("+");
		when(digitParser.parse(input)).thenReturn(new String[] {"1", "3"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("4", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnDiffByTwoArabDigitNegative() {
		String input = "1-3";
		
		when(actionParser.parse(input)).thenReturn("-");
		when(digitParser.parse(input)).thenReturn(new String[] {"1", "3"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("-2", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnDiffByTwoArabDigitPosetive() {
		String input = "5-3";
		
		when(actionParser.parse(input)).thenReturn("-");
		when(digitParser.parse(input)).thenReturn(new String[] {"5", "3"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("2", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnMultByTwoArabDigit() {
		String input = "2*3";
		
		when(actionParser.parse(input)).thenReturn("*");
		when(digitParser.parse(input)).thenReturn(new String[] {"2", "3"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("6", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnDevByTwoArabDigitMoreZero() {
		String input = "6/3";
		
		when(actionParser.parse(input)).thenReturn("/");
		when(digitParser.parse(input)).thenReturn(new String[] {"6", "3"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("2", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnDevByTwoArabDigitEqualsZero() {
		String input = "2/3";
		
		when(actionParser.parse(input)).thenReturn("/");
		when(digitParser.parse(input)).thenReturn(new String[] {"2", "3"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("0", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnSumByTwoRomeDigit() {
		String input = "I+III";
		
		when(actionParser.parse(input)).thenReturn("+");
		when(digitParser.parse(input)).thenReturn(new String[] {"I", "III"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("IV", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnDiffByTwoRomeDigitNegative() {
		String input = "I-III";
		
		when(actionParser.parse(input)).thenReturn("-");
		when(digitParser.parse(input)).thenReturn(new String[] {"I", "III"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("-II", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnDiffByTwoRomeDigitPosetive() {
		String input = "V-III";
		
		when(actionParser.parse(input)).thenReturn("-");
		when(digitParser.parse(input)).thenReturn(new String[] {"V", "III"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("II", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnMultByTwoRomeDigit() {
		String input = "II*III";
		
		when(actionParser.parse(input)).thenReturn("*");
		when(digitParser.parse(input)).thenReturn(new String[] {"II", "III"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("VI", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnDevByTwoRomeDigitMoreZero() {
		String input = "VI/III";
		
		when(actionParser.parse(input)).thenReturn("/");
		when(digitParser.parse(input)).thenReturn(new String[] {"VI", "III"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("II", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldReturnDevByTwoRomeDigitEqualsZero() {
		String input = "II/III";
		
		when(actionParser.parse(input)).thenReturn("/");
		when(digitParser.parse(input)).thenReturn(new String[] {"II", "III"});
		
		calculator = new CalculatorProviderImpl(actionParser, digitParser);
		
		assertEquals("", calculator.calculate(input));
	}
	
	@Test
	public void calculateShouldRizeExceptionWhenInputIsNull() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			calculator = new CalculatorProviderImpl(actionParser, digitParser);
			calculator.calculate(null);
		});
		
		assertEquals("Input data is null", exception.getMessage());
	}
	
	@Test
	public void calculateShouldRizeExceptionWhenAnyProviderIsNull() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			calculator = new CalculatorProviderImpl(null, null);
			calculator.calculate("");
		});
		
		assertEquals("Actions or Digits parsers is null", exception.getMessage());
	}
	
	@Test
	public void calculateShouldRizeExceptionWhenAnyProviderReturnNullResult() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			calculator = new CalculatorProviderImpl(actionParser, digitParser);
			calculator.calculate("");
		});
		
		assertEquals("Actions or Digits elements is null", exception.getMessage());
	}
	
	@Test
	public void calculateShouldRizeExceptionWhenDigitProviderReturnOneDigit() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			String input = "2+3";
			
			when(actionParser.parse(input)).thenReturn("+");
			when(digitParser.parse(input)).thenReturn(new String[] {"2"});
			
			calculator = new CalculatorProviderImpl(actionParser, digitParser);
			calculator.calculate(input);
		});
		
		assertEquals("Calculation possible with only two elements", exception.getMessage());
	}
}
