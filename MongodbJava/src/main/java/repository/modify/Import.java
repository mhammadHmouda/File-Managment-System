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
        try (InputStream streamToUploadFrom = Files.newInputStream(Paths.get(filePath))) {
            GridFSUploadOptions options = new GridFSUploadOptions()
                    .chunkSizeBytes(1048576)
                    .metadata(new Document("type", "txt file"));

            ObjectId fileId = gridFSBucket.uploadFromStream("file.txt", streamToUploadFrom, options);
            System.out.println("The file id of the uploaded file is: " + fileId.toHexString());
        }
    }
}
