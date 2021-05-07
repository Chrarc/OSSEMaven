package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilTest {
	@Test
	public void testIstErstesHalbjahr () {
		final Integer[] monat = {1,6,7,12};
		final Boolean[] ergebnis = {true, true, false, false};
		for (int i = 0; i < monat.length; ++i) {
			assertEquals("Monat " + monat[i] + ":", ergebnis[i], Util.istErstesHalbjahr(monat[i]));
		}
		try {
			assertEquals(true, Util.istErstesHalbjahr(0));
			fail("Monat 0 wurde nicht als illegales Argument erkannt.");
		}catch (IllegalArgumentException e) {
			
		}
		try {
			assertEquals(false, Util.istErstesHalbjahr(13));
			fail("Monat 13 wurde nicht als illegales Argument erkannt.");
		}catch (IllegalArgumentException e) {
			
		}
		
	}
}
