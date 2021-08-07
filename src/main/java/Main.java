import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dao.AccountDao;
import dao.UserDao;
import model.Account;
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

        AccountDao accountDao = new AccountDao();

        Account acc1 = new Account("Object1", "99999");
        Account acc2 = new Account("Object2", "3245678");
        Account acc3 = new Account("Object3", "100");
        Account acc4 = new Account("Object3", "999999999");
        Account acc5 = new Account("Object4", "5000");

        userDao.deleteAllData();
        accountDao.deleteAllData();

        System.out.println("Create");
        userDao.createData(user1, user2, user3);
        accountDao.createData(acc1, acc2, acc3, acc4);

        System.out.println("Read");
        userDao.readAllData();
        userDao.readUserByName("Object2");
        userDao.readUserByAge(44);
        accountDao.readAllData();
        accountDao.readAccountByName("Object3");

        System.out.println("Update");
        userDao.readUser(user2);
        userDao.updateData(user2, user1);
        userDao.readUser(user1);
        accountDao.readAccount(acc3);
        accountDao.updateData(acc3, acc5);
        accountDao.readAccount(acc5);

        System.out.println(" Delete");
        userDao.readAllData();
        userDao.deleteData(user1);
        userDao.readAllData();
        accountDao.readAllData();
        accountDao.deleteData(acc4);
        accountDao.readAllData();

        accountDao.bindAccToUser(acc2.getId(), user3.getId());
        accountDao.bindAccToUser(acc1.getId(), user3.getId());
        accountDao.bindAccToUser(acc3.getId(), user1.getId());


        userDao.bindUserToAcc(user3.getId(), Arrays.asList(acc2.getId(), acc1.getId()));
        userDao.bindUserToAcc(user1.getId(), Arrays.asList(acc3.getId()));

        userDao.findUserByManyAcc(2);
    }
}