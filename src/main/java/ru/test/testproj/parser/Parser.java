package ru.test.testproj.parser;

public interface Parser <T, V> {
	public static final int ELEMENT_COUNT = 2;
	public static final String AVAILABLE_ACTIONS_PATTERN = "[/\\*\\-\\+]";	
	public static final int[] AVAILABLE_DIGITS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	T parse(V data);
}
