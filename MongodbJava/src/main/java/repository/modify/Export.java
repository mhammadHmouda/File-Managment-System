package repository.modify;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;

import java.util.function.Consumer;

import static app.App.gridFSBucket;

public class Export {

    public static void exportFile(){
        Bson query = Filters.eq("metadata.type", "txt file");
        Bson sort = Sorts.ascending("filename");
        gridFSBucket.find(query)
                .sort(sort)
                .limit(5)
                .forEach(new Consumer<GridFSFile>() {
                    @Override
                    public void accept(final GridFSFile gridFSFile) {
                        assert gridFSFile.getMetadata() != null;
                        System.out.println(gridFSFile.getMetadata().get("type"));
                    }
                });
    }
}
