import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class UserStorage {
	
	//email -> user
	private Map<String, User> catalogueUsers;
	private static UserStorage userStorage = null;
	private UserWriter writer = UserWriter.getUserWriter();
	
	
	private UserStorage() {
		this.catalogueUsers = new HashMap<String, User>();
	}
	
	public static UserStorage getUserStorageInstance() {
		if(userStorage == null) {
			UserStorage.userStorage = new UserStorage();
		}
		return UserStorage.userStorage;
	}
	
	public void saveUsers() {
		this.writer.saveUsersToFile(this.catalogueUsers);
	}
	
	public void printUsers() {
		for(Entry<String, User> entry : this.catalogueUsers.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
	
	public void loadUsersFromFile() {
		Gson gson = new GsonBuilder().create();
		File file = new File("Users.json");
		if(!file.exists()) {
			System.out.println("File does not exist");
			return;
		}
		try(BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
			Type setType = new TypeToken<Map<String, User>>() {}.getType();
			this.catalogueUsers = gson.fromJson(buff, setType);
		}
		catch (IOException e) {
			System.out.println("Problems with the file");
			e.printStackTrace();
		}
		
		
//      StringBuilder builder = new StringBuilder();
//		try (Scanner sc = new Scanner(file)) {
//			while (sc.hasNextLine()) {
//				builder.append(sc.nextLine());
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		if (builder.length() > 0) {
//			HashMap<String, User> getUsers = gson.fromJson(builder.toString(), setType);
//		} else {
//			System.err.println("NO OBJECTS YET!");
//		}
		
	}
	
	public void addUser(User user) throws Exception {
		if(user != null) {
			if(this.catalogueUsers.containsKey(user.getEmail())) {
				System.out.println("This user already exists");
			} else {
				this.catalogueUsers.put(user.getEmail(), user);
				System.out.println("Your registration is successful!");
			}
		} else {
			throw new Exception("User is null!");
		}
	}
	
	public void updateUser(User user) throws Exception {
		if(user != null) {
			if(this.catalogueUsers.containsKey(user.getEmail())) {
				this.catalogueUsers.put(user.getEmail(), user);
			} else {
				throw new Exception("There is no such user");
			}
		} else {
			throw new Exception("User is null");
		}
	}
	
	public User updateUserPassword(User user, String newPassword) throws Exception {
		if(user != null && isValid(newPassword))
		{
			if(this.catalogueUsers.containsKey(user.getEmail())){
					user.changePassword(user.getPassword(), newPassword);
					return user;
			} else {
				throw new Exception("User with such email does not exist!");
			}
		} else {
			throw new Exception("User is null or invalid data for new password!");
		}
	}
			
	
	public void updateUserBalance(User user, int value) throws Exception {
		if(user != null) {
			if(value > 0) {
				if(this.catalogueUsers.containsKey(user.getEmail())) {
					user.setMoneyBalance(user.getMoneyBalance() + value);
					System.out.println("You successfuly added " + value + " to your balance!");
				} else {
					throw new Exception("User with such email " + user.getEmail() + " does not exist!");
				}
			} else {
				System.out.println("You should add non negative value to your balance!");
			}
		} else {
			throw new Exception("User is null!");
		}
	}
	
	public User login(String email, String password) throws Exception {
		
			if(!loginInfo(email, password)) {
				
				System.out.println("Invalid username or password!");
				System.out.println("Try again!");
				return null;
			} else {
				System.out.println("You successfuly logged in! Now you can see your profile!");
				User user = this.catalogueUsers.get(email);
				user.changeState();
				this.catalogueUsers.put(user.getEmail(), user);
				System.out.println("You successfully signed in!");
				return user;
			}
		}
		
	
	public void logout(User user) throws Exception {
		if(user != null) {
			if(user.isLogged()) {
				user.changeState();
				updateUser(user);
				System.out.println("You successfuly log out");
			} else {
				System.out.println("You cannot log out if you're not logged in");
			}
		} else {
			throw new Exception("User is null!");
		}
	}
	
	
	public static boolean loginInfo(String email, String password) throws Exception {
		if(isValid(email) && isValid(password)) {
			if(userStorage.getCatalogueUsers().containsKey(email)) {
				User user = userStorage.getCatalogueUsers().get(email);
				if(user.getPassword().equals(password)) {
					return true;
				} 
			}
			return false;
		} else {
			throw new Exception("Email or password is null");
		}
	}

	
	public void changeUserBalance(User user, int value) throws Exception {
		if(user != null) {
			this.catalogueUsers.get(user.getEmail()).addMoneyBalance(value);
		} else {
			throw new Exception("User is null!");
		}
	}
	
	private static boolean isValid(String str) {
		return (str != null && str.trim().length() > 0);
	}
	
	public Map<String, User> getCatalogueUsers() {
		return Collections.unmodifiableMap(this.catalogueUsers);
	}
}
