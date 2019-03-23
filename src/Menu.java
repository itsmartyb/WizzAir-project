import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

public class Menu {

	private static WizzAir wizz = WizzAir.createWizzAir();
	private User user;
	
		public static void startSite() {
			wizz.getUserStorage().loadUsersFromFile();
			wizz.getUserStorage().printUsers();
			System.out.println();
			System.out.println("=================================================================================");
			System.out.println("		Explore your travel opportunities with WIZZ!");
			System.out.println("=================================================================================");
			System.out.println("		Cheap flight offers** - 12 routes from your area");
			System.out.println("=================================================================================");
			System.out.println("		Hand pick the offers you like best");
			System.out.println("=================================================================================");
			System.out.println();
			System.out.println("MILAN from 29.90BGN  ====== BUDAPEST from 19.90BGN  ====== BOLOGNA from 19.90BGN ");
			System.out.println();
			System.out.println("=================================================================================");
			System.out.println();
			System.out.println("AIRPORT TRANSPORT ======== AIRPORT PARKING ========= GIFT VAUCHERS ==============");
			System.out.println();
			System.out.println("=================================================================================");
			System.out.println("Search Flights		Register		SignUp			FAQ  ");
			System.out.println("=================================================================================");
		}
	
		@SuppressWarnings("resource")
		public void printMenu() {
			System.out.println("Please enter command: ");
			boolean isRunning = true;
			startSite();
			while (isRunning) {
				System.out.println();
				printUsers();
				printCommands();
				System.out.println("Type command: ");
				Scanner sc = new Scanner(System.in);
				String command = sc.nextLine().trim().toLowerCase();
				switch (command) {
				case "signup":
					signUpUser();
					break;
				case "signin":
					signInUser();
					break;
				case "date":
					wizz.getFlightStorage().printFlightsByDate();
					break;
				case "price":
					wizz.getFlightStorage().printCataloguePrice();
					break;
				case "faq":
					wizz.printFAQ();
					break;
				case "exit":
					isRunning = false;
					wizz.getUserStorage().saveUsers();
					break;
				case "users":
					wizz.getFlightStorage().getUserStorage();
					break;
				case "location":
					System.out.println("Please type your location: ");
					Scanner scan = new Scanner(System.in);
					String loc = scan.next();
					wizz.getFlightStorage().searchFlightLocation(loc);
					break;
				case "search":
					scan = new Scanner(System.in);
					System.out.println("Please type your location: ");
					String location = scan.next();
					System.out.println("Please type your destination: ");
					String destination = scan.next();
					wizz.getFlightStorage().searchFlightLocationDestination(location, destination);
					break;
				default:
					break;
				}
			}
		}
	
		private void printUsers() {
			for (Entry<String, User> users : wizz.getUserStorage().getCatalogueUsers().entrySet()) {
				System.out.println(users);
			}
		}
		
		@SuppressWarnings("resource")
		private void userMenu() throws Exception {
			while (true) {
				System.out.println("1. List all flights by Date");
				System.out.println("2. Buy ticket by Date");
				System.out.println("3. List your tickets");
				System.out.println("4. Add money to your balance");
				System.out.println("5. Change Password");
				System.out.println("6. Search from your location: ");
				System.out.println("7. See info");
				System.out.println("8. Logout");
				System.out.println("9. Buy ticket from location to destination ");
				Scanner chooseOption = new Scanner(System.in);
				int command = chooseOption.nextInt();
				switch (command) {
				case 1:
					wizz.getFlightStorage().printFlightsByDate();
					break;
				case 2:
					Scanner scan = new Scanner(System.in);
					System.out.println("Pease type your location: ");
					String location = scan.next();
					System.out.println("Please type your destination: ");
					String destination = scan.next();
					System.out.println("Choose time: ");
					System.out.println("yyyy-MM-dd");
					String time = scan.next();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime date = LocalDateTime.parse(time, formatter);
					date.format(formatter);
					System.out.println("How many seats do you want: ");
					int numSeats = scan.nextInt();
					buyTicketByDate(date,location,destination,numSeats);
					break;
				case 3:
					user.listTickets();
					break;
				case 4:
					System.out.println("Choose amount to add");
					scan = new Scanner(System.in);
					int value = scan.nextInt();
					try {
						user.addMoneyBalance(value);
					} catch (Exception e) {
						System.out.println("Problem with adding money");
						e.printStackTrace();
					}
					break;
				case 5:
					scan = new Scanner(System.in);
					System.out.println("Enter old password: ");
					String oldPass = scan.nextLine();
					if(oldPass.equals(this.user.getPassword())) {
						System.out.println("Enter new password: ");
						String newPass = scan.nextLine();
						this.user = wizz.getUserStorage().updateUserPassword(this.user, newPass);
					} else {
						System.out.println("Wrong old password given!");
					}
					try {
					} catch (Exception e) {
						System.out.println("Problem with changing the password!");
						e.printStackTrace();
					}
					break;
				case 6:
					System.out.println("Please type your location: ");
					scan = new Scanner(System.in);
					String loc = scan.next();
					wizz.getFlightStorage().searchFlightLocation(loc);
					break;
				case 7:
					System.out.println(user);
					break;
				case 8:
					try {
						wizz.getUserStorage().logout(user);
						return;
					} catch (Exception e) {
						System.out.println("Problem with logout");
						e.printStackTrace();
					}
					break;
				case 9:
					Scanner sc = new Scanner(System.in);
					System.out.println("Please, type your location: ");
					String loca = sc.next();
					System.out.println("Type your destination: ");
					String dest = sc.next();
					System.out.println("How many seats do you want: ");
					int seats = sc.nextInt();
					if (seats > 0 && seats < 5) {
						buyTicketByDestination(loca, dest, seats);
					} else {
						System.out.println("How many seats do you want: ");
						seats = sc.nextInt();
					}
					break;
				default:
					System.out.println("Invalid command!");
					break;
				}
			}
		}
	
		@SuppressWarnings("resource")
		private void signInUser() {
			Scanner signIn = new Scanner(System.in);
			String emailInput;
			System.out.println("Enter your email: ");
			emailInput = signIn.nextLine();
			String passwordInput;
			System.out.println("Enter your password: ");
			passwordInput = signIn.nextLine();
			try {
				this.user = wizz.getUserStorage().login(emailInput, passwordInput);
				System.out.println(user);
				if (this.user != null) {
					userMenu();
					return;
				}
			} catch (Exception e) {
				System.out.println("Invalid username or password, please try again!");
				e.printStackTrace();
			}
		}
	
		@SuppressWarnings("resource")
		private void signUpUser() {
			Scanner sc = new Scanner(System.in);
			System.out.println("				=====REGISTER=====");
			System.out.println("	In the future you will be able to sign in with these details.");
			System.out.print("Email: ");
			String email = sc.nextLine();
			System.out.print("Password: ");
			String password = sc.nextLine();
			System.out.print("Name: ");
			String name = sc.nextLine();
			System.out.println("Gsm: ");
			String GSM = sc.nextLine();
			System.out.println("Nationality");
			String nationality = sc.nextLine();
			System.out.println("Gender: ");
			System.out.println("Choose between Male/Female");
			String sex = sc.nextLine();
			boolean isMale = false;
			if (sex.equalsIgnoreCase("male")) {
				isMale = true;
			} else {
				if (sex.equalsIgnoreCase("female")) {
					isMale = false;
				}
			}
			try {
				this.user = User.signUp(email, password, name, GSM, nationality, isMale);
				if (this.user != null) {
					System.out.println(this.user);
					printUsers();
					userMenu();
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			sc.close();
	
		}
	
		private void buyTicketByDestination(String location, String destination, int numberOfSeats) {
			boolean isLoged = user.isLogged();
			if (isLoged) {
				for (Entry<String, TreeSet<Flight>> entry : wizz.getFlightStorage().getCatalogueFligths().entrySet()) {
					for (Flight flight : entry.getValue()) {
						if (flight.getLocation().equalsIgnoreCase(location)
								&& flight.getDestination().equalsIgnoreCase(destination)) {
							for (int i = 0; i < numberOfSeats; i++) {
								Ticket t = flight.getRandomTicket();
								user.buyTicket(t);
								flight.removeTicket(t);
								System.out.println("This seats are no longer available" + t);
								wizz.setMoney((int) t.getPrize());
								System.out.println("You just bought " + t);
							}
						}
					}
				}
			} else {
				System.out.println("You have to log in first, before you can buy any tickets!");
			}
		}
		
		private void buyTicketByDate(LocalDateTime time, String location, String destination, int numberOfSeats) {
			boolean isLoged = user.isLogged();
			if (isLoged) {
				for (Entry<String, TreeSet<Flight>> entry : wizz.getFlightStorage().getCatalogueFligths().entrySet()) {
					for (Flight flight : entry.getValue()) {
						if (flight.getLocation().equalsIgnoreCase(location)
								&& flight.getDestination().equalsIgnoreCase(destination) && flight.getDate().equals(time)) {
							for (int i = 0; i < numberOfSeats; i++) {
								Ticket t = flight.getRandomTicket();
								user.buyTicket(t);
								flight.removeTicket(t);
								System.out.println("This seats are no longer available" + t);
								wizz.setMoney((int) t.getPrize());
								System.out.println("You just bought " + t);
							}
						}
					}
				}
			} else {
				System.out.println("You have to log in first, before you can buy any tickets!");
			}
		}
		
	
		private void printCommands() {
			System.out.println();
			System.out.println("To Sign in press: signin");
			System.out.println("To Sign up press: signup");
			System.out.println("To search flights by date press: date");
			System.out.println("To search flights by price press: price");
			System.out.println("If you want to choose your destination press: search");
			System.out.println("Search flights from your location now type: location");
			System.out.println("To see the FAQ press: faq");
			System.out.println("To exit site press: exit");
		}
}
