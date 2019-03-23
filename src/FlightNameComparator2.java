import java.util.Comparator;

public class FlightNameComparator2 implements Comparator<Flight>{

	@Override
	public int compare(Flight flight1, Flight fligth2) {
		return fligth2.getName().compareTo(flight1.getName());
	}

}