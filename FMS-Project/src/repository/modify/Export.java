package repository.modify;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;

import java.util.function.Consumer;

import static app.App.gridFSBucket;

public class Export {

    public static void exportFile(){
        Bson query = Filters.eq("metadata.type", "csv file");
        Bson sort = Sorts.ascending("filename");
        gridFSBucket.find(query)
                .sort(sort)
                .limit(5)
                .forEach(gridFSFile -> {
                    assert gridFSFile.getMetadata() != null;
                    System.out.println(gridFSFile.getMetadata().get("type"));
                });
        GridFSFile result = gridFSBucket.find(query).first();

        assert result != null;
        gridFSBucket.delete(result.getObjectId());

    }
}
