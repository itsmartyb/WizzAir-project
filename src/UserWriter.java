import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class UserWriter {

	private static UserWriter instance = null;
	private Gson gson;
	private File file;

	private UserWriter() throws IOException {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		this.file = new File("Users.json");
		if (!this.file.exists()) {
			this.file.createNewFile();
		}
	}

	public static UserWriter getUserWriter() {
		if (instance == null) {
			try {
				instance = new UserWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
//	public void saveUsersToFile(Map<String, User> users) {
//		if (!users.isEmpty()) {
//			try (PrintWriter writer = new PrintWriter(new FileOutputStream(file), false)) {
//				this.gson.toJson(users, writer);
//			} catch (IOException e) {
//				return;
//			}
//		}
//	}

	public void saveUsersToFile(Map<String, User> users) {
		if (!users.isEmpty()) {
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))) {
				this.gson.toJson(users, writer);
				System.out.println("Written to file");
			} catch (IOException e) {
				System.out.println("Problems with file");
				return;
			}
		}
	}
	
}
