package de.hfu;
import java.util.Scanner;

/**
 * Ein Programm zur Konvertierung von Textzeilen in Großbuchstaben.
 * Bei Ausfuehrung wird eine Aufforderung zur Texteingabe angezeigt.
 * Der konvertierte Text wird in der Kommandozeile zurueckgegeben.
 *
 * @author Christian Oberfoell
 * @version 1.0
 */
public class TextUpperCase{
	/**
     * Hauptprogramm.
     * 
     * @param args Kommandozeilenparameter
     */
	public static void main(String[] args){
		System.out.println("Bitte einen Text eingeben");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		input = input.toUpperCase();
		System.out.println(input);
	}
}
