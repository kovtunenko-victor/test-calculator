package ru.test.testproj.parser;

import ru.test.testproj.reader.Reader;

public class DoCalculate {
	private final Reader reader;
	private final CalculatorProvider<String, String> calcProvider;
	
	public DoCalculate(Reader reader, CalculatorProvider<String, String> calcProvider) {
		this.reader = reader;
		this.calcProvider = calcProvider;
	}
	public String doCalculate() {
		return calcProvider.calculate(reader.read());
	}
}
