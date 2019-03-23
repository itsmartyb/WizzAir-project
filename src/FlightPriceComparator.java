import java.util.Comparator;

public class FlightPriceComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight flight1, Flight flight2) {
		return Double.compare(flight2.getPrice(), flight1.getPrice());
	}

}
