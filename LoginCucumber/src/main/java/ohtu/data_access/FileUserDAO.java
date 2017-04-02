package ohtu.data_access;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import ohtu.domain.User;

/**
 *
 * @author Ilja Häkkinen
 */
public class FileUserDAO implements UserDao {

    private List<User> users;
    private final String filename;

    public FileUserDAO(String filename) {
        this.users = new ArrayList<>();
        this.filename = filename;
        readFromFile();
    }

    @Override
    public List<User> listAll() {
        readFromFile();
        return users;
    }

    @Override
    public User findByName(String name) {
        readFromFile();
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
        writeToFile();
    }

    private void readFromFile() {
        try (FileInputStream input = new FileInputStream(filename)) {
            addUsersFromFileToUserList(
                    new BufferedReader(new InputStreamReader(input, "UTF-8")));
        } catch (FileNotFoundException f) {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addUsersFromFileToUserList(
            BufferedReader reader) throws IOException {
        List<User> tempList = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            String[] parts = line.split(" ");
            tempList.add(new User(parts[0], parts[1]));
            line = reader.readLine();
        }
        users = tempList;
    }

    private void writeToFile() {
        try (OutputStream output = new FileOutputStream(filename)) {
            String content = createStringFromUserList();

            byte[] contentInBytes = content.getBytes();
            output.write(contentInBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createStringFromUserList() {
        StringBuilder content = new StringBuilder();

        for (User user : users) {
            content.append(user.getUsername()).append(" ")
                    .append(user.getPassword()).append("\n");
        }
        return content.toString();
    }
}
