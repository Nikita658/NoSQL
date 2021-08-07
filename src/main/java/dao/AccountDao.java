package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Account;
import org.bson.Document;
import utils.MongoUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AccountDao {
    private final MongoDatabase database = connect("aLevel");

    public void createData(Account... accounts) {
        MongoCollection<Document> accountList = database.getCollection("accounts");
        accountList.insertMany(Arrays.stream(accounts)
                .map(AccountDao::mapperFrom)
                .collect(Collectors.toList()));
    }

    public void bindAccToUser(String accId, String userId){
        MongoCollection<Document> accounts = database.getCollection("accounts");

        final Document filter = new Document();
        filter.append("id", accId);

        final Document update = new Document();
        update.append("userId", userId);

        final Document document = new Document();
        document.append("$set", update);

        accounts.updateOne(filter, document);
    }

    public void readAllData() {
        MongoCollection<Document> accounts = database.getCollection("accounts");
        FindIterable<Document> documents = accounts.find();
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void readAccount(Account account) {
        final Document filter = new Document();
        filter.append("accountName", account.getAccountName());
        MongoCollection<Document> accounts = database.getCollection("accounts");
        FindIterable<Document> documents = accounts.find(filter);
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void readAccountByName(String name) {
        final Document filter = new Document();
        filter.append("accountName", name);
        MongoCollection<Document> accounts = database.getCollection("accounts");
        FindIterable<Document> documents = accounts.find(filter);
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void updateData(Account accToUpdate, Account account) {
        final Document filter = new Document();
        filter.append("accountName", accToUpdate.getAccountName());

        final Document newData = new Document();
        newData.append("accountName", account.getAccountName());
        newData.append("moneyAmount", account.getMoneyAmount());

        final Document updateObject = new Document();
        updateObject.append("$set", newData);

        MongoCollection<Document> accounts = database.getCollection("accounts");
        accounts.updateOne(filter, updateObject);
    }

    public void deleteData(Account account) {
        final Document filter = new Document();
        filter.append("id", account.getId());

        MongoCollection<Document> accounts = database.getCollection("accounts");
        accounts.deleteOne(filter);
    }

    public void deleteAllData() {
        MongoCollection<Document> accounts = database.getCollection("accounts");
        accounts.deleteMany(new Document());
    }

    public static MongoDatabase connect(String databaseName) {
        return MongoUtils.getMongoClient(null).getDatabase(databaseName);
    }

    /*public static MongoDatabase connect(String databaseName, MongoCredential credential) {
        return MongoUtils.getMongoClient(credential).getDatabase(databaseName);
    }*/


    public static Document mapperFrom(Account account) {
        final Document document = new Document();
        document.append("id", account.getId());
        document.append("accountName", account.getAccountName());
        document.append("moneyAmount", account.getMoneyAmount());
        return document;
    }
}