import java.util.Comparator;

public class FlightNameComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight flight1, Flight fligth2) {
		return fligth2.getName().compareTo(flight1.getName())*-1;
	}

}
