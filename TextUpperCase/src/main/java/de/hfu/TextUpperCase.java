package de.hfu;
import java.util.Scanner;

public class TextUpperCase{
	public static void main(String[] args){
		System.out.println("Bitte einen Text eingeben");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		input = input.toUpperCase();
		System.out.println(input);
	}
}
