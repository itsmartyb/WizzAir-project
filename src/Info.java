
public abstract class Info {
	private final String email;
	private String password;
	private String name;
	private String GSM;
	private String nationality;
	private boolean isMale;
	
	
		public Info(String email, String password, String name, String GSM, String nationality, boolean isMale) throws WrongRegistrationException, WrongEmailException {
			if(isValid(email) && isValid(password) && isValid(name) && isValidGSM(GSM) && isValid(nationality)) {
				if(isValid(email) && isValidEmailAddress(email)) {
					this.email = email;
				} else {
					throw new WrongEmailException("Incorrect email!");
				}
				this.password = password;
				setName(name);
				setGSM(GSM);
				setNationality(nationality);
				setMale(isMale);
				
			} else {
				throw new WrongRegistrationException("Incorrect data for registration");
			}
		}
		
		public boolean changePassword(String oldPassword, String newPassword) throws WrongRegistrationException {
			if(isValid(oldPassword) && oldPassword.equals(this.password)) {
				setPassword(newPassword);
				System.out.println("Your password has been changed successfuly");
				return true;
			} else {
				System.out.println("Incorrect new password given!");
				return false;
			}
		}
	
		private boolean isValid(String str) {
			return (str != null && str.trim().length() > 0);
		}
		
		private boolean isValidGSM(String gsm) {
			return (isValid(gsm) && gsm.length() == 10 && gsm.startsWith("0"));
		}
	
		public String getName() {
			return this.name;
		}
	
		public String getGSM() {
			return this.GSM;
		}
	
		public String getEmail() {
			return this.email;
		}
	
		public String getPassword() {
			return this.password;
		}
	
		public boolean isMale() {
			return this.isMale;
		}
	
		public String getNationality() {
			return this.nationality;
		}
	
		public void setPassword(String password) throws WrongRegistrationException {
			
			if(isValid(password)) {
				this.password = password;
			} else {
				throw new WrongRegistrationException("Incorrect password!");
			}
		}
	
		public void setName(String name) throws WrongRegistrationException {
			if(isValid(name)) {
				this.name = name;
			} else {
				throw new WrongRegistrationException("Incorrect name!");
			}
		}
		
		private boolean isValidEmailAddress(String email) {
	           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
	           java.util.regex.Matcher m = p.matcher(email);
	           return m.matches();
	    }
	
		public void setGSM(String gsm) throws WrongRegistrationException {
			if(isValidGSM(gsm)) {
				this.GSM = gsm;
			} else {
				throw new WrongRegistrationException("Incorrect GSM!");
			}
		}
	
		public void setNationality(String nationality) throws WrongRegistrationException {
			if(isValid(nationality)) {	
				this.nationality = nationality;
			} else {
				throw new WrongRegistrationException("Incorrect nationality!");
			}
		}
	
		private void setMale(boolean isMale) {
			this.isMale = isMale;
		}
	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
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
			Info other = (Info) obj;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			String info;
			info = "Name: " + name + ", GSM: " + GSM + ", Email: " + email + ", Password: " + password + ", Nationality: " + nationality + ", ";
			if(isMale) {
				info = info + "Sex: " + "Male, ";
			} else {
				info = info + "Sex: " + "Female, ";
			}
			return info;
		}

	}
