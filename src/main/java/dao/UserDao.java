package dao;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.User;
import org.bson.Document;
import utils.MongoUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {
    private final MongoDatabase database = connect("aLevel");

    public void createData(User... users) {
        MongoCollection<Document> userList = database.getCollection("users");
        userList.insertMany(Arrays.stream(users)
                .map(UserDao::mapperFrom)
                .collect(Collectors.toList()));
    }

    public void readAllData() {
        MongoCollection<Document> users = database.getCollection("users");
        FindIterable<Document> documents = users.find();
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void readUser(User user) {
        final Document filter = new Document();
        filter.append("firstName", user.getFirstName());
        MongoCollection<Document> users = database.getCollection("users");
        FindIterable<Document> documents = users.find(filter);
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void readUserByName(String name) {
        final Document filter = new Document();
        filter.append("firstName", name);
        MongoCollection<Document> users = database.getCollection("users");
        FindIterable<Document> documents = users.find(filter);
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void readUserByAge(int age){
        BasicDBObject query = new BasicDBObject("age", new BasicDBObject("$lte", age));
        MongoCollection<Document> users = database.getCollection("users");
        FindIterable<Document> documents = users.find(query);
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void updateData(User userToUpdate, User user) {
        final Document filter = new Document();
        filter.append("firstName", userToUpdate.getFirstName());

        final Document newData = new Document();
        newData.append("firstName", user.getFirstName());
        newData.append("lastName", user.getLastName());
        newData.append("age", user.getAge());
        newData.append("workplace", user.getWorkplace());
        newData.append("city", user.getCity());

        final Document updateObject = new Document();
        updateObject.append("$set", newData);

        MongoCollection<Document> users = database.getCollection("users");
        users.updateOne(filter, updateObject);
    }

    public void deleteData(User user) {
        final Document filter = new Document();
        filter.append("id", user.getId());

        MongoCollection<Document> users = database.getCollection("users");
        users.deleteOne(filter);
    }

    public void deleteAllData() {
        MongoCollection<Document> users = database.getCollection("users");
        users.deleteMany(new Document());
    }

    public void bindUserToAcc(String userId, List<String> accounts){
        MongoCollection<Document> users = database.getCollection("users");

        final Document filter = new Document();
        filter.append("id", userId);

        final Document update = new Document();
        update.append("accounts", accounts);

        final Document document = new Document();
        document.append("$set", update);

        users.updateOne(filter, document);
    }

    public void findUserByManyAcc(int amount){
        MongoCollection<Document> userList = database.getCollection("users");
        BasicDBObject query = new BasicDBObject("accounts", new BasicDBObject("$size", amount));
        FindIterable<Document> documents = userList.find(query);
        for (Document documentList : documents) {
            System.out.println(documentList);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static MongoDatabase connect(String databaseName) {
        return MongoUtils.getMongoClient(null).getDatabase(databaseName);
    }

    /*public static MongoDatabase connect(String databaseName, MongoCredential credential) {
        return MongoUtils.getMongoClient(credential).getDatabase(databaseName);
    }*/


    public static Document mapperFrom(User user) {
        final Document document = new Document();
        document.append("id", user.getId());
        document.append("firstName", user.getFirstName());
        document.append("lastName", user.getLastName());
        document.append("age", user.getAge());
        document.append("workplace", user.getWorkplace());
        document.append("city", user.getCity());
        return document;
    }

    public static User mapperTo(Document document) {
        final User user = new User(
                document.get("firstName", String.class),
                document.get("lastName", String.class),
                document.get("age", Integer.class),
                document.get("workplace", String.class),
                document.get("city", String.class)
        );
        user.setId(document.get("_id", String.class));
        return user;
    }
}