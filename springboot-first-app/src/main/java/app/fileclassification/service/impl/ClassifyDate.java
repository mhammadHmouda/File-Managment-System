package app.fileclassification.service.impl;

import app.exception.ClassifyMethodNotFound;
import app.fileclassification.cach.ClassifyCaching;
import app.fileclassification.request.ClassifyDateRequest;
import app.fileclassification.service.abs.AClassify;
import app.model.DBFile;
import app.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static app.fileclassification.constant.ClassifyException.*;
import static app.fileclassification.constant.ImplementType.IMPLEMENTATION_DATE_CLASS;

@Service
@Qualifier(IMPLEMENTATION_DATE_CLASS)
public class ClassifyDate extends AClassify<ClassifyDateRequest> {

    @Override
    public ResponseMessage classify(ClassifyDateRequest classifyDateRequest, String typeOfClassification) throws ClassifyMethodNotFound {
        try {

            logger.info("Service of date");
            List<DBFile> dbFiles = dbFileRepository.getAllBetweenDates(classifyDateRequest.getStartDate(), classifyDateRequest.getEndDate());

            if (dbFiles == null)
                return ResponseMessage.getInstance(INVALID_INPUT);

            ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

            return ResponseMessage.getInstance(SUCCESS);

        }catch (Exception e){
            throw new ClassifyMethodNotFound(NOT_ALLOWED);
        }
    }

}