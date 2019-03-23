import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class FlightWriter {

	private Set<Flight> flights;
	private static FlightWriter instance = null;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private File file;

	private FlightWriter() throws IOException {
		this.flights = new LinkedHashSet<Flight>();
		this.file = new File("Flights.json");
		if (!this.file.exists()) {
			new File("Flights.json").createNewFile();
		}

	}

	public static FlightWriter getFlightWriter() {
		if (instance == null) {
			try {
				instance = new FlightWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public void saveFlightsToFile() {
		if (!flights.isEmpty()) {
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
				writer.println(gson.toJson(flights));
			} catch (IOException e) {
				return;
			}
		}
	}

	public void upLoadUsersFromFile() throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				builder.append(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Type setType = new TypeToken<LinkedHashSet<User>>() {
		}.getType();
		if (builder.length() > 0) {
			Set<Flight> getFlights = gson.fromJson(builder.toString(), setType);
			this.flights.addAll(getFlights);
		} else {
			System.err.println("NO OBJECTS YET!");
		}
	}
}