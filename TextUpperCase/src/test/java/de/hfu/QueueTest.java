package de.hfu;

import org.junit.*;
import static org.junit.Assert.*;

public class QueueTest {
	private static Queue queue;
	private static Integer queuelength;
	
	@BeforeClass
	public static void testInit () {
		queuelength = 3;
		queue = new Queue(queuelength);
	}
	
	@Test
	public void testQueue () {
		try {
			assertEquals("Rückgabe bei leerer Warteschlange", 0 ,queue.dequeue());
			fail("Keine Exception geworfen, obwohl Warteschlange leer war.");
		}catch(IllegalStateException e) {				
		}
		queue.enqueue(1);
		assertEquals("Erster eingefügter Wert in der Warteschlange stimmt nicht", 1, queue.dequeue());
		for (int i = 0; i < queuelength; ++i) {
			queue.enqueue(i);			
		}
		assertEquals("Nachdem die Warteschlange beginnend bei 0 mit aufsteigenden ganzen Zahlen gefüllt aber nicht überfüllt wurde sollte 0 zurückgegeben werden.", 0, queue.dequeue());
		for (int i = 0; i < (queuelength*2); ++i) {
			queue.enqueue(i);		
		}
		assertEquals("Aktuell erster Wert nach überfüllen der Warteschlange",1, queue.dequeue());
		for (int i = 0; i < queuelength  - 2; ++i) {
			queue.dequeue();
		}
		assertEquals("Aktuell letzter Wert nach überfüllen der Warteschlange",(int)queuelength*2-1, queue.dequeue());
		
	}
}
