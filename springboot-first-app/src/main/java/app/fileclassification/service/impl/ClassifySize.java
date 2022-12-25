package app.fileclassification.service.impl;

import app.fileclassification.service.abs.AClassify;
import app.model.DBFile;
import app.response.ResponseMessage;
import app.fileclassification.cach.ClassifyCaching;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Qualifier("size")
public class ClassifySize extends AClassify<Integer> {

    @Override
    public ResponseMessage classify(Integer size, String typeOfClassification) {
        List<DBFile> dbFiles = dbFileRepository.findBySize(size);

        if (dbFiles == null)
            return ResponseMessage.getInstance("Invalid size !!");

        ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

        return ResponseMessage.getInstance("Classify size done successfully");
    }
}