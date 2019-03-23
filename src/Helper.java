import java.util.Random;

public class Helper {

	public static int randomNumber(int min, int max) {
		if (max < min) {
			int copy = min;
			min = max;
			max = copy;
		}
		return new Random().nextInt(max - min + 1) + min;
	}

	public static String generateRandomDestination() {
		final String[] destinations = {"Barcelona","Bucharest","Budapest","Debrecen"
				,"Dubai","London","Milan","Paris","Skopje","Stockholm"
				,"Rome","Tenerife","Warsaw", "Lisabon"};

		return destinations[randomNumber(0, destinations.length - 1)];
	}
	
	
	

}
