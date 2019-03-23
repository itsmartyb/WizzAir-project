
public class Admin extends User {
	
	private static Admin admin = null;
	
	private Admin(String email, String password, String name, String GSM, String nationality, boolean isMale)
			throws WrongRegistrationException, WrongEmailException {
		super(email, password, name, GSM, nationality, isMale);
		
	}
	
	public static Admin adminSetUp(String email, String password, String name, String GSM, String nationality, boolean isMale) throws Exception {
		if(Admin.admin == null) {
			Admin.admin = new Admin("admin@abv.bg", "admin", "Administrator", "0102030405", "bulgarian", true);
		}
		return admin;
	}
	
	public void addFlight(Flight flight) {
		
	}
}
