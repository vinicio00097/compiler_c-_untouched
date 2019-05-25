package compiler.main;

import java.io.BufferedReader;
import java.io.FileReader;

import java_cup.runtime.Symbol;

import compiler.generated.Lexer;
import compiler.generated.Parser;

public class Main {

	private static final int MIN_INPUT_FILES = 1;

	public static void main(String[] args) {
		if (args.length < MIN_INPUT_FILES) {
			displayHelpMessage();

		} else {
			for (String filePath : args) {
				startCompilationFor(filePath);
			}
		}
	}

	private static void displayHelpMessage() {
		System.out
				.println("Usage: java -jar compiler.jar file [file2 file3...]");
	}

	private static void startCompilationFor(String filePath) {
		try {
			Lexer scanner = new Lexer(new BufferedReader(new FileReader(
					filePath)));

			Parser parser = new Parser(scanner);
			Symbol s = parser.parse();
			
			if (s.toString().equals("#0"))
				System.out.println("SUCCESSFULL COMPILATION");
			else
				System.out.println(s);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
