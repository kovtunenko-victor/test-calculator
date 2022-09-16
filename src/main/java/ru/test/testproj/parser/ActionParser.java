package ru.test.testproj.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.test.testproj.error.CalcAppError;

public class ActionParser implements Parser<String, String> {
	public static final int ACTION_COUNT = 1;
	
	@Override
	public String parse(String data) {
		if(data == null) {
			throw new CalcAppError("Input data is null. Parsing is not possible");
		}
		return getActions(data);
	}
	
	private String getActions(String line) {
		String actions = null;
		Pattern pattern = Pattern.compile(AVAILABLE_ACTIONS_PATTERN);
		Matcher matcher = pattern.matcher(line);
		int countMatches = 0;
		
		while (matcher.find()) {
			countMatches++;
			
			if(countMatches != ACTION_COUNT) {
				throw new CalcAppError("Calculation possible with only one action");
			}
			
			actions = line.substring(matcher.start(), matcher.end());
		}
		
		if(countMatches == 0) {
			throw new CalcAppError("No available action set");
		}
		
		return actions;
	}
}
