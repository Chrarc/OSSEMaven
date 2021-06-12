package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class ResidentRepositoryTest {
	private BaseResidentService service;
	private ResidentRepository stubAuflistung;
	private Resident filterResident;
	private List<Resident> filteredList;
	private Resident uniqueResident;
	

	@Before
	public void setupService (){
		this.service = new BaseResidentService();
		this.stubAuflistung = new ResidentRepositoryStub();
		service.setResidentRepository(stubAuflistung);
		this.filterResident = null;
		this.filteredList = null;
		this.uniqueResident = null;
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFilteredResidentsListFamilyName() {
		
		//Filter nach Nachname
		this.filterResident = new Resident("", "Mueller","","",null);
		this.filteredList = service.getFilteredResidentsList(filterResident);
		assertEquals(2, this.filteredList.size());
		assertEquals("Thomas", this.filteredList.get(0).getGivenName());
		assertEquals("Max", this.filteredList.get(1).getGivenName());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFilteredResidentsListGivenName() {	
		//Filter nach Vorname
		this.filterResident = new Resident("Ma*", "","","",null);
		this.filteredList = service.getFilteredResidentsList(filterResident);
		assertEquals(4, this.filteredList.size());
		assertEquals("Max", this.filteredList.get(0).getGivenName());
		assertEquals("Mustermann", this.filteredList.get(0).getFamilyName());
		assertEquals("Maria", this.filteredList.get(1).getGivenName());
		assertEquals("King", this.filteredList.get(1).getFamilyName());
		assertEquals("Max", this.filteredList.get(2).getGivenName());
		assertEquals("Mueller", this.filteredList.get(2).getFamilyName());
		assertEquals("Maria", this.filteredList.get(3).getGivenName());
		assertEquals("Maier", this.filteredList.get(3).getFamilyName());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFilteredResidentsListStreet() {	
		//Filter nach Straße Beet
		this.filterResident = new Resident("", "","Beeth*","",null);
		this.filteredList = service.getFilteredResidentsList(filterResident);
		assertEquals(2, this.filteredList.size());
		assertEquals("Beethofenstraße", this.filteredList.get(0).getStreet());
		assertEquals("Beethofenstraße", this.filteredList.get(1).getStreet());
	}
	
	/* Filterung nach Stadt offenbar nicht vorgesehen!?
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFilteredResidentsListCity() {	
		//Filter nach Straße Stadt lin
		this.filterResident = new Resident("", "", "", "Berlin",null);
		this.filteredList = service.getFilteredResidentsList(filterResident);
		assertEquals(2, this.filteredList.size());
		assertEquals("Berlin", this.filteredList.get(0).getCity());
		assertEquals("Berlin", this.filteredList.get(1).getCity());
	}*/
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFilteredResidentsListAll() {		
		//Filter nach allen
		this.filterResident = new Resident("", "","","",null);
		this.filteredList = service.getFilteredResidentsList(filterResident);
		assertEquals(this.stubAuflistung.getResidents().size(), this.filteredList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFilteredResidentsListDate() {	
		//Filter nach Geburtsdatum
		this.filterResident = new Resident("", "","","",new Date(2001,9,1));
		this.filteredList = service.getFilteredResidentsList(filterResident);
		assertEquals(2, this.filteredList.size());
		assertEquals("Anja", this.filteredList.get(0).getGivenName());
		assertEquals("Maria", this.filteredList.get(1).getGivenName());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFilteredResidentsListSingle() {	
		//Filter nach einem
		this.filterResident = this.stubAuflistung.getResidents().get(2);
		this.filteredList = service.getFilteredResidentsList(filterResident);
		assertEquals(1, this.filteredList.size());
		assertEquals(this.stubAuflistung.getResidents().get(2).getGivenName(), this.filteredList.get(0).getGivenName());
		assertEquals(this.stubAuflistung.getResidents().get(2).getStreet(), this.filteredList.get(0).getStreet());
		assertEquals(this.stubAuflistung.getResidents().get(2).getCity(), this.filteredList.get(0).getCity());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetUniqueResidentsList() throws ResidentServiceException {
		//Suche nach bestimmten Bürger
		this.filterResident = new Resident("Maria", "King", "Bahnhofsstraße", "Stuttgart", new Date(1945, 7, 22));
		this.uniqueResident = service.getUniqueResident(filterResident);
		assertEquals(this.stubAuflistung.getResidents().get(3).getGivenName(), this.uniqueResident.getGivenName());
		assertEquals(this.stubAuflistung.getResidents().get(3).getFamilyName(), this.uniqueResident.getFamilyName());
	}
	
	@Test(expected = ResidentServiceException.class)
	public void testGetUniqueResidentsListWildcardExc() throws ResidentServiceException {
		//Suche mit Wildcards
		this.filterResident = new Resident("*", "*","*","*",null);
		this.uniqueResident = service.getUniqueResident(filterResident);
		fail("Es wurde keine Exception geworfen, obwohl eine unerlaubte Wildcard genutzt wurde.");
	}
	
	@Test(expected = ResidentServiceException.class)
	public void testGetUniqueResidentsListNotUnique() throws ResidentServiceException {
		//Suche uneindeutig
		this.filterResident = new Resident("Anja", "","","",null);
		this.uniqueResident = service.getUniqueResident(filterResident);
		fail("Es wurde keine Exception geworfen, obwohl die Suche nicht eindeutig ist.");
	}
	
	
}
