package app.fileclassification.service.impl;


import app.fileclassification.service.abs.AClassify;
import app.model.DBFile;
import app.response.ResponseMessage;
import app.fileclassification.cach.ClassifyCaching;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("name")
public class ClassifyName extends AClassify<String> {
    @Override
    public ResponseMessage classify(String name, String typeOfClassification) {
        logger.info("Service of name");
        List<DBFile> dbFiles = dbFileRepository.findByName(name);

        if (dbFiles == null)
            return ResponseMessage.getInstance("Invalid Name !");

        ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

        return ResponseMessage.getInstance("Classify Name done successfully!");
    }
}
