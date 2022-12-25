package app.filerepository.services.classify;

import app.filerepository.model.DBFile;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.classify.cach.ClassifyCaching;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Qualifier("s2")
public class ClassifySize extends AClassify<Integer>{

    @Override
    public ResponseMessage classify(Integer size, String typeOfClassification) {
        List<DBFile> dbFiles = dbFileRepository.findBySize(size);

        if (dbFiles == null)
            return ResponseMessage.getInstance("Invalid size !!");

        ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

        return ResponseMessage.getInstance("Classify size done successfully");
    }
}