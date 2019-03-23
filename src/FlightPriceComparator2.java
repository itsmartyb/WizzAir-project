import java.util.Comparator;

public class FlightPriceComparator2 implements Comparator<Flight> {

	@Override
	public int compare(Flight flight1, Flight flight2) {
		return Double.compare(flight1.getPrice(), flight2.getPrice());
	}

}
