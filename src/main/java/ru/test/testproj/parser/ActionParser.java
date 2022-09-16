package ru.test.testproj.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionParser implements Parser<String, String> {
	
	@Override
	public String parse(String data) {
		if(data == null) {
			throw new IllegalArgumentException("Input data is null. Parsing is not possible");
		}
		
		if(data.split(AVAILABLE_ACTIONS_PATTERN).length != ELEMENT_COUNT) {
			throw new IllegalArgumentException("Calculation possible with only two elements");
		}
		
		return getActions(data);
	}
	
	private String getActions(String line) {
		String actions = null;
		Pattern pattern = Pattern.compile(AVAILABLE_ACTIONS_PATTERN);
		Matcher matcher = pattern.matcher(line);

		while (matcher.find()) {
			actions = line.substring(matcher.start(), matcher.end());
		}
		
		if(actions == null) {
			throw new IllegalArgumentException("No available action set");
		}
		
		return actions;
	}
}
