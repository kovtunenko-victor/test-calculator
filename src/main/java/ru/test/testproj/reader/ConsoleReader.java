package ru.test.testproj.reader;

import java.io.FilterInputStream;
import java.util.Scanner;

public class ConsoleReader implements Reader {
	
	@Override
	public String read() {
		Scanner scan = new Scanner(new FilterInputStream(System.in){ public void close(){ } });
		String result = scan.nextLine();
		scan.close();
		
		return result;
	}
}
