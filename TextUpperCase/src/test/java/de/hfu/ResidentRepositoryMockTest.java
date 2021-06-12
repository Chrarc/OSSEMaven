package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.easymock.EasyMock.*;

public class ResidentRepositoryMockTest {
	private BaseResidentService service;
	private ResidentRepository mockAuflistung;
	private Resident filterResident;
	private List<Resident> filteredList;
	private Resident uniqueResident;
	
	@Before
	public void setupService (){
		this.service = new BaseResidentService();
		this.mockAuflistung = createMock(ResidentRepository.class);
		
		this.filterResident = null;
		this.filteredList = null;
		this.uniqueResident = null;
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMockGetGetFilteredResidentsList () {
		expect(this.mockAuflistung.getResidents()).andReturn(Arrays.asList(
				new Resident("Thomas", "Mueller", "Beethofenstraße", "Berlin", new Date(1990, 1, 25)),
				new Resident("Anja", "Maier", "Blumenweg", "Hamburg", new Date(2001, 9, 1)),
				new Resident("Max", "Mustermann", "Schulgasse", "München", new Date(1966, 3, 20))));
		replay(mockAuflistung);
		service.setResidentRepository(mockAuflistung);
		this.filterResident = new Resident("Ma*", "","","",null);
		this.filteredList = service.getFilteredResidentsList(filterResident);
		verify(mockAuflistung);
		assertEquals(1, this.filteredList.size());
		assertEquals("Max", this.filteredList.get(0).getGivenName());
		assertEquals("Mustermann", this.filteredList.get(0).getFamilyName());		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMockGetGetUniqueResidents () throws ResidentServiceException {
		expect(this.mockAuflistung.getResidents()).andReturn(Arrays.asList(
				new Resident("Thomas", "Mueller", "Beethofenstraße", "Berlin", new Date(1990, 1, 25)),
				new Resident("Anja", "Maier", "Blumenweg", "Hamburg", new Date(2001, 9, 1)),
				new Resident("Max", "Mustermann", "Schulgasse", "München", new Date(1966, 3, 20))));
		replay(mockAuflistung);
		service.setResidentRepository(mockAuflistung);
		this.filterResident = new Resident("Anja", "Maier", "Blumenweg", "Hamburg", new Date(2001, 9, 1));
		this.uniqueResident = service.getUniqueResident(this.filterResident);
		verify(mockAuflistung);
		assertEquals("Anja", this.uniqueResident.getGivenName());
		assertEquals("Maier", this.uniqueResident.getFamilyName());
	}
}
