import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Ticket {

	public enum TicketType {
		STANDART_TICKET, FIRST_CLASS, CHILD_TICKET
	};

	private static final int COEFF_FIRST_CLASS = 250 / 100;
	private static final int COEFF_CHILD_TICKET = 50 / 100;

	private Flight flight;
	private double prize;
	private TicketType type;
	private int serialId;
	
	private Seat seat;

	public TicketType getType() {
		return type;
	}

	public Seat getSeat() {
		return seat;
	}

	public Flight getFlight() {
		return flight;
	}

	public double getPrize() {
		return prize;
	}

	public  LocalDate getDate() {
		return this.flight.getDate();
	}

	private Ticket(double prize, Flight flight, TicketType type) {
		if (prize > 0) {
			this.prize = prize;
		}
		// SET FLIGT ? - TODO
		if (flight != null) {
			this.flight = flight;
		}
		this.seat = new Seat();
		this.type = type;
		this.serialId++;
	}

	public static Ticket getTicket(TicketType type, Flight flight) throws InvalidTypeOfTicketException {
		switch (type) {
		case STANDART_TICKET:
			return new Ticket(Helper.randomNumber(10, 100), flight, type);
		case FIRST_CLASS:
			return new Ticket(Helper.randomNumber(10, 100) * COEFF_FIRST_CLASS, flight,type);
		case CHILD_TICKET:
			return new Ticket(Helper.randomNumber(10, 100) * COEFF_CHILD_TICKET, flight,type);
		}
		throw new InvalidTypeOfTicketException("No such type of ticket!");

	}
	
	

	// inner class
	private class Seat {

		private static final int NUMBER_OF_ROWS = 40;
		//private static final int NUMBER_OF_FIRST_CLASS_ROWS = 5;

		private String[] TYPES_OF_SEAT_ID = { "A", "B", "C", "D", "E", "F" };

		private int row;
		private String seat;

		private Seat() {
			this.seat = setSeat();
		}


		private String setSeat() {
			return (Helper.randomNumber(1, NUMBER_OF_ROWS)
					+ getTYPES_OF_SEAT_ID()[Helper.randomNumber(0, TYPES_OF_SEAT_ID.length - 1)]);
		}

		public String[] getTYPES_OF_SEAT_ID() {
			return TYPES_OF_SEAT_ID;
		}

		@Override
		public String toString() {
			return this.seat;
		}
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seat == null) ? 0 : seat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (seat == null) {
			if (other.seat != null)
				return false;
		} else if (!seat.equals(other.seat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [prize=" + prize + " Type: " + this.type   +", seat=" + seat + "Date: " + getDate() + "]";
	}

}
