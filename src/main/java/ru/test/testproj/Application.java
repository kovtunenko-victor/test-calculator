package ru.test.testproj;

import ru.test.testproj.parser.CalculatorProviderImpl;
import ru.test.testproj.parser.DigitParser;
import ru.test.testproj.parser.DoCalculate;
import ru.test.testproj.reader.ConsoleReader;
import ru.test.testproj.parser.ActionParser;

public class Application {
	public static void main(String[] args) {
		DoCalculate calc = new DoCalculate(new ConsoleReader(System.in), new CalculatorProviderImpl(new ActionParser(), new DigitParser()));
		System.out.println(calc.doCalculate());
	}
}
