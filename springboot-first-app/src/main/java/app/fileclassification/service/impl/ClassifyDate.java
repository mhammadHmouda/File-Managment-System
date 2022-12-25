package app.fileclassification.service.impl;

import app.fileclassification.service.abs.AClassify;
import app.model.DBFile;
import app.fileclassification.request.ClassifyDateRequest;
import app.response.ResponseMessage;
import app.fileclassification.cach.ClassifyCaching;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("date")
public class ClassifyDate extends AClassify<ClassifyDateRequest> {

    @Override
    public ResponseMessage classify(ClassifyDateRequest classifyDateRequest, String typeOfClassification){
        logger.info("Service of date");
        List<DBFile> dbFiles = dbFileRepository.getAllBetweenDates(classifyDateRequest.getStartDate(), classifyDateRequest.getEndDate());

        if (dbFiles == null)
            return ResponseMessage.getInstance("Invalid Date !");

        ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

        return ResponseMessage.getInstance("Classify date done successfully!");
    }

}