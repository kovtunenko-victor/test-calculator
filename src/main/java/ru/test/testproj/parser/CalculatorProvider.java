package ru.test.testproj.parser;

public interface CalculatorProvider <T, V> {
	T calculate(V data);
}
