import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Flight {

	private static final int NUMBER_OF_SEATS = 210;
	private static final int NUMBER_OF_FIRST_CLASS_TICKETS = 30;
	private static final int ONE_YEAR_IN_HOURS = 8760;

	private int id;
	private String location;
	private String destination;
	private double prize;
	private LocalDate date;
	// private Ticket ticket;
	private Set<Ticket> tickets;

	public Flight(String location, String destination, double prize, int id) {
		// FLIGHT will be from 1 hour after now till one year ahead
		this.date = LocalDate.now().plusDays(Helper.randomNumber(1, ONE_YEAR_IN_HOURS));
		if (destination != null && destination.trim().length() >= 2) {
			this.destination = destination;
		}
		if (location != null && location.trim().length() >= 2) {
			this.location = location;
		}
		if (prize > 0) {
			this.prize = prize;
		}
		this.id = id;
		this.tickets = new HashSet<Ticket>();
		for (int i = 0; i < NUMBER_OF_FIRST_CLASS_TICKETS; i++) {
			try {
				tickets.add(Ticket.getTicket(Ticket.TicketType.FIRST_CLASS, this));
			} catch (InvalidTypeOfTicketException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < NUMBER_OF_SEATS; i++) {
			try {
				tickets.add(Ticket.getTicket(Ticket.TicketType.STANDART_TICKET, this));
			} catch (InvalidTypeOfTicketException e) {
				e.printStackTrace();
			}
		}
	}

	//check
	public void removeTicket(Ticket ticket) {
		this.tickets.remove(ticket);
	}
	

	public void printTicketsOfFlight() {
		for (Ticket t : this.tickets) {
			System.out.println(t);
		}
	}
	

	public Set<Ticket> getTickets() {
		return Collections.unmodifiableSet(this.tickets);
	}
	
	public String printTickets() {
		return this.tickets.toString();
	}

	public double getPrice() {
		return this.prize;
	}

	public String getName() {
		return location;
	}

	public String getLocation() {
		return location;
	}

	public String getDestination() {
		return destination;
	}

	public double getPrize() {
		return prize;
	}

//	public DateTime getDate() {
//		return date;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public LocalDate getDate() {
		return date;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", location=" + location + ", destination=" + destination + ", prize=" + prize
				+ ", date=" + date + ", tickets=" + tickets + "]";
	}

	public Ticket getRandomTicket() {
		ArrayList<Ticket> tickets = new ArrayList<>(this.tickets);
		int random = Helper.randomNumber(0, tickets.size() - 1);
		return tickets.get(random);
	}

}
