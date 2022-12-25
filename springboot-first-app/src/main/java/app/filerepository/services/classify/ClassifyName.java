package app.filerepository.services.classify;


import app.filerepository.model.DBFile;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.classify.cach.ClassifyCaching;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("s3")
public class ClassifyName extends AClassify<String>{
    @Override
    public ResponseMessage classify(String name, String typeOfClassification) {
        logger.info("Service of name");
        List<DBFile> dbFiles = dbFileRepository.findByName(name);

        if (dbFiles == null)
            return ResponseMessage.getInstance("Invalid Name !");

        ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

        return ResponseMessage.getInstance("Classify date done successfully!");
    }
}
