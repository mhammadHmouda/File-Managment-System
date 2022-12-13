package app;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import repository.modify.*;


public class App {

    static String uri = "mongodb://localhost:27017";

    static MongoClient mongoClient = MongoClients.create(uri);

    static MongoDatabase database = mongoClient.getDatabase("FMS");

    public static GridFSBucket gridFSBucket = GridFSBuckets.create(database);

    public static void main(String[] args) throws IOException {
        Export.exportFile();
    }
}
