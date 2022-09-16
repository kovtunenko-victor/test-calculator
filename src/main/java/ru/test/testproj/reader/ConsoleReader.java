package ru.test.testproj.reader;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.Scanner;

import ru.test.testproj.error.CalcAppError;

public class ConsoleReader implements Reader<String> {
	private final InputStream input;
	
	public ConsoleReader(InputStream input) {
		this.input = input;
	}
	
	@Override
	public String read() {
		if(input == null) {
			throw new CalcAppError("Input stream is null");
		}
		
		Scanner scan = new Scanner(new FilterInputStream(input){ public void close(){ } });
		String result = scan.nextLine();
		scan.close();
		
		return result;
	}
}
