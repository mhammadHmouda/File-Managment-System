package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Database {
    public static MongoDatabase getConnection(){
        String uri = "mongodb://localhost:27017";
        MongoDatabase database;

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            database = mongoClient.getDatabase("FMS");
        }

        return database;
    }
}
