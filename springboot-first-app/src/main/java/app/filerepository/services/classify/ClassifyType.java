package app.filerepository.services.classify;

import app.filerepository.model.DBFile;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.classify.cach.ClassifyCaching;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassifyType extends AClassify<String>{

    @Override
    public ResponseMessage classify(String typeValue, String typeOfClassification) {
        List<DBFile> dbFiles = dbFileRepository.findByType(typeValue);

        if(dbFiles == null)
            return ResponseMessage.getInstance("Invalid type!");

        ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

        return ResponseMessage.getInstance("Classify type done successfully");
    }
}