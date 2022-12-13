package repository.modify;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import static app.App.gridFSBucket;

public class Delete {

    public static void deleteFile(String filename){
        String name  = filename.split("/")[filename.split("/").length - 1];
        Bson query = Filters.eq("filename", name);

        GridFSFile result = gridFSBucket.find(query).first();

        assert result != null;
        gridFSBucket.delete(result.getObjectId());

        System.out.println("The file deleted successfully!");

    }
}
