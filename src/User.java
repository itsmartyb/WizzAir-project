import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
	
	public class User extends Info{
		
		private int moneyBalance;
		private Set<Ticket> userTickets;
		private boolean isOnline;
		private static WizzAir system;
		
		
		protected User(String email, String password, String name, String GSM, String nationality, boolean isMale) throws WrongRegistrationException, WrongEmailException {
				super(email, password, name, GSM, nationality, isMale);
				User.system = WizzAir.createWizzAir();
				changeState();
				this.userTickets = new HashSet<Ticket>();
		}
		
		public static User signUp(String email, String password, String name, String GSM, String nationality, boolean isMale) throws Exception {
			User.system = WizzAir.createWizzAir();
			if(!(system.getUserStorage().getCatalogueUsers().containsKey(email))) {	
					try {
						system.getUserStorage().addUser(new User(email, password, name, GSM, nationality, isMale));
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception("Invalid data for user!", e);
					}
					return new User(email, password, name, GSM, nationality, isMale);
				} else {
					throw new WrongRegistrationException("User with such email already exists!");
			}
		}
		
		public void addMoneyBalance(int value) throws Exception {
			if(this.isOnline){
				System.out.println("Enter amount: ");
				if(value <= 0) {
					System.out.println("You cannot give non-positive value for balance!");
					System.out.println("Try again!");
				} else { 
					this.moneyBalance += value;
					system.getUserStorage().changeUserBalance(this, value);
					System.out.println("You successfuly added " +  value + " and now your balance is " +  this.moneyBalance + "!");	
				}
				
			} else {
				System.out.println("You have to log in to be able to change your money balance!");
			}
		}
		
	
		public void listAllFlights() {
			
			for(Entry<String, TreeSet<Flight>> entry : system.getFlightStorage().getCatalogueFligths().entrySet()) {
				System.out.println("LOCATION: " + entry.getKey());
				entry.getValue().forEach(flight -> System.out.println(flight));
			} 
		}
		
		public void listTickets() {
			this.userTickets.forEach(ticket -> System.out.println(ticket));
		}
		
		public void buyTicket(Ticket ticket) {
			if(ticket != null) {
				if(isOnline) {
					if(this.moneyBalance > ticket.getPrize()) {
						this.userTickets.add(ticket);
						this.moneyBalance -= ticket.getPrize();
						try {
							system.getUserStorage().updateUser(this);
						} catch (Exception e) {
							System.out.println("Invalid user!");
							e.printStackTrace();
						}
					} else {
						try {
							throw new NotEnoughMoneyException("Don't have enough money for buying this ticket on price " + ticket.getPrize());
						} catch (NotEnoughMoneyException e) {
							System.out.println("Not enough money for this ticket, you can add money to your balance!");
							e.printStackTrace();
						}
					}
				} else {
					System.out.println("You are not logged in!");
				}
			} else {
				System.out.println("Ticket is null!");
			}
		}
		
		public boolean isLogged() {
			return this.isOnline == true;
		}
	
		public void changeState() {
			this.isOnline = !this.isOnline;
		}
	
		public int getMoneyBalance() {
			return this.moneyBalance;
		}
		
		public void setMoneyBalance(int moneyBalance) {
			this.moneyBalance = moneyBalance;
		}

		
		@Override
		public String toString() {
			System.out.print(super.toString());
			return "Money balance: " + this.moneyBalance + ", Your flights: " + this.userTickets + ", Status: " + this.isOnline;
		}

		public Set<Ticket> getUserTickets() {
			return Collections.unmodifiableSet(this.userTickets);
		}

		public boolean isOnline() {
			return isOnline;
		}

		public static WizzAir getSystem() {
			return system;
		}


	
}
