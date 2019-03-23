import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

public class FlightStorage {

	private static final int NUMBER_OF_FLIGHTS = 50;
	private Map<String, TreeSet<Flight>> catalogueFligths;
	private Flight flight;
	private static FlightStorage flightInstance = null;
	private UserStorage userStorage;

	private FlightStorage() {
		this.userStorage =UserStorage.getUserStorageInstance();
		this.catalogueFligths = new HashMap<String, TreeSet<Flight>>();
		int id = 1000;
		for (int i = 0; i < NUMBER_OF_FLIGHTS; i++) {
			Flight f = new Flight(Helper.generateRandomDestination(), Helper.generateRandomDestination(),
					Helper.randomNumber(5, 100), id);
			addFlight(f);
			id++;
		}
	}

	public UserStorage getUserStorage() {
		return userStorage;
	}

	public Map<String, TreeSet<Flight>> getCatalogueFligths() {
		return Collections.unmodifiableMap(this.catalogueFligths);
	}

	public void addFlight(Flight flight) {
		if (flight != null && this.catalogueFligths != null) {
			//location
			String flightDestination = flight.getName();
			if (!this.catalogueFligths.containsKey(flightDestination)) {
				this.catalogueFligths.put(flightDestination, new TreeSet<Flight>(new FlightPriceComparator()));
			}
			this.catalogueFligths.get(flightDestination).add(flight);
		}
	}

	public static FlightStorage getFlightStorage() {
		if (flightInstance == null) {
			return new FlightStorage();
		}
		return flightInstance;
	}

	public void searchFlightLocationDestination(String location, String destination) {
		for (Entry<String, TreeSet<Flight>> entry : this.catalogueFligths.entrySet()) {
			for (Flight flight : entry.getValue()) {
				if (entry.getKey().equalsIgnoreCase(location)
						&& flight.getDestination().equalsIgnoreCase(destination)) {
					System.out.println("-----------------");
					System.out.println(flight);
				}
			}
		}
	}

	public void searchByDate(LocalDateTime time) {
		for (Entry<String, TreeSet<Flight>> entry : this.catalogueFligths.entrySet()) {
			for (Flight flight : entry.getValue()) {
				if (flight.getDate().equals(time)) {
					System.out.println(flight);
				}
			}
		}
	}
	
	public void searchByLocationAndDate(String destination, LocalDateTime time) {
		for (Entry<String, TreeSet<Flight>> entry : this.catalogueFligths.entrySet()) {
			for (Flight flight : entry.getValue()) {
				if (this.catalogueFligths.containsKey(destination) && entry.getKey().equals(destination)
						&& flight.getDate().equals(time)) {
					System.out.println(flight);
				}
			}
		}
	}
	
	public void printFlightsByDate() {
		for (Entry<String, TreeSet<Flight>> entry : this.catalogueFligths.entrySet()) {
			for (Flight flight : entry.getValue()) {
					System.out.println("LOCATION:  " + flight.getLocation() + 
										"   DESTINATION: " +  flight.getDestination()   + 
										"   DATE:   " + flight.getDate());
				}
			}
		}

	public void searchFlightLocation(String location) {
		for (Entry<String, TreeSet<Flight>> entry : this.catalogueFligths.entrySet()) {
			for (Flight flight : entry.getValue()) {
				if (flight.getLocation().equalsIgnoreCase(location)) {
					System.out.println(flight);
				}
			}
		}
	}

	
	public void printCataloguePrice() {
		for (Entry<String, TreeSet<Flight>> entry : this.catalogueFligths.entrySet()) {
			for (Flight flight : entry.getValue()) {
					System.out.println("LOCATION: " + flight.getLocation() + 
							"   DESTINATION: " + flight.getDestination() +
							"  PRICE: " + flight.getPrice());
				}
			}
		}
	

	public Flight getFlight() {
		return flight;
	}


}
