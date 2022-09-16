package ru.test.testproj.parser;

import ru.test.testproj.error.CalcAppError;
import ru.test.testproj.reader.Reader;

public class DoCalculate {
	private final Reader<String> reader;
	private final CalculatorProvider<String, String> calcProvider;
	
	public DoCalculate(Reader<String> reader, CalculatorProvider<String, String> calcProvider) {
		this.reader = reader;
		this.calcProvider = calcProvider;
	}
	public String doCalculate() {
		try {
			if(reader == null || calcProvider == null) {
				throw new CalcAppError("Reader or CalculatorProvider is null");
			}
			
			return calcProvider.calculate(reader.read());
		} catch (CalcAppError ex) {
			return ex.getMessage();
		}
	}
}
