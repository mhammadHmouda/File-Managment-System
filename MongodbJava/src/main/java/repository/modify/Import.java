package repository.modify;

import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static app.App.gridFSBucket;


public class Import {
    public static void importFile(String filePath) throws IOException {
        String type = filePath.split("\\.")[1];
        String name  = filePath.split("/")[filePath.split("/").length - 1];
        try (InputStream streamToUploadFrom = Files.newInputStream(Paths.get(filePath))) {
            GridFSUploadOptions options = new GridFSUploadOptions()
                    .metadata(new Document("type", type));

            ObjectId fileId = gridFSBucket.uploadFromStream(name , streamToUploadFrom, options);
            System.out.println("The file id of the uploaded file is: " + fileId.toHexString());
        }
    }
}
