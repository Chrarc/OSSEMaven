package de.hfu;

import java.util.*;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.*;

public class ResidentRepositoryStub implements ResidentRepository{

	public List<Resident> resident;
	@SuppressWarnings("deprecation")
	@Override
	public List<Resident> getResidents() {
		resident = new ArrayList<Resident>();
		resident.add(new Resident("Thomas", "Mueller", "Beethofenstraße", "Berlin", new Date(1990, 1, 25)));
		resident.add(new Resident("Anja", "Maier", "Blumenweg", "Hamburg", new Date(2001, 9, 1)));
		resident.add(new Resident("Max", "Mustermann", "Schulgasse", "München", new Date(1966, 3, 20)));
		resident.add(new Resident("Maria", "King", "Bahnhofsstraße", "Stuttgart", new Date(1945, 7, 22)));
		resident.add(new Resident("Max", "Mueller", "Blumenweg", "Stuttgart", new Date(1990, 1, 25)));
		resident.add(new Resident("Maria", "Maier", "Beethofenstraße", "München", new Date(2001, 9, 1)));
		resident.add(new Resident("Thomas", "Mustermann", "Bahnhofsstraße", "Hamburg", new Date(1966, 3, 20)));
		resident.add(new Resident("Anja", "King", "Schulgasse", "Berlin", new Date(1945, 7, 22)));
		return resident;
	}

}
