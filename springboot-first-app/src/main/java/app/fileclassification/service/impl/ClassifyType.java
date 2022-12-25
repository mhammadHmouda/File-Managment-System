package app.fileclassification.service.impl;

import app.fileclassification.cach.ClassifyCaching;
import app.fileclassification.service.abs.AClassify;
import app.model.DBFile;
import app.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Qualifier("type")
public class ClassifyType extends AClassify<String> {

    @Override
    public ResponseMessage classify(String typeValue, String typeOfClassification) {
        List<DBFile> dbFiles = dbFileRepository.findByType(typeValue);

        if(dbFiles == null)
            return ResponseMessage.getInstance("Invalid type!");

        ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

        return ResponseMessage.getInstance("Classify type done successfully");
    }
}