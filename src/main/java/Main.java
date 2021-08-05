import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dao.UserDao;
import model.User;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        User user1 = new User("Ex1", "a", 20, "mac", "Kiev");
        User user2 = new User("Ex2", "b", 21, "company", "New-York");
        User user3 = new User("Ex3", "c", 22, "Epam", "Lvov");
        User user = new User("Ex4", "d", 23, "NIX", "America");
        System.out.println("Create");
        userDao.createData(user1, user2, user3);
        System.out.println("Read");
        userDao.readAllData();
        userDao.readUserByName("Forrest");
        userDao.readUserByAge(44);
        System.out.println("CRUD / Update");
        userDao.readUser(user2);
        userDao.updateData(user2, user);
        userDao.readUser(user);
        System.out.println("Delete");
        userDao.readAllData();
        userDao.deleteData(user1);
        userDao.readAllData();
        userDao.deleteAllData();



    }

}