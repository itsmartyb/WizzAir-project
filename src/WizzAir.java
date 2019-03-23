
public class WizzAir {

	private static WizzAir One = null;
	private final String name = "WizzAir Company";
	private int money = 10000;
	private UserStorage userStorage;
	private FlightStorage flightstorage;
	private Menu menu;

	private WizzAir() {
		this.userStorage = UserStorage.getUserStorageInstance();
		this.flightstorage = FlightStorage.getFlightStorage();
	}

	public synchronized static WizzAir createWizzAir() {
		if (WizzAir.One == null) {
			WizzAir.One = new WizzAir();
		}
		return WizzAir.One;
	}

	public void startMenu() {
		this.menu = new Menu();
		menu.printMenu();
	}

	public int getMoney() {
		return money;
	}

	protected void setMoney(int money) {
		this.money += money;
	}

	// ==================================================================
	// ==================================================================

	public void printFAQ() {
		System.out.println("We do our best to protect and safely store your personal data!");
		System.out.println("We also offer an online security service for VISA and MasterCard holders.");
	}


	@Override
	public String toString() {
		return "WizzAir [name=" + name + ", userStorage=" + userStorage + ", flightstorage=" + flightstorage + "]";
	}

	public UserStorage getUserStorage() {
		return userStorage;
	}

	public FlightStorage getFlightStorage() {
		return flightstorage;
	}

}
