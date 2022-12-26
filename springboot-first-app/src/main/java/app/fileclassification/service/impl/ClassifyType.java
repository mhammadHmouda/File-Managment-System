package app.fileclassification.service.impl;

import app.exception.ClassifyMethodNotFound;
import app.fileclassification.cach.ClassifyCaching;
import app.fileclassification.service.abs.AClassify;
import app.model.DBFile;
import app.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

import static app.fileclassification.constant.ClassifyException.*;
import static app.fileclassification.constant.ImplementType.IMPLEMENTATION_TYPE_CLASS;

@Service
@Qualifier(IMPLEMENTATION_TYPE_CLASS)
public class ClassifyType extends AClassify<String> {
    @Override
    public ResponseMessage classify(String typeValue, String typeOfClassification) throws ClassifyMethodNotFound {
        try {
            List<DBFile> dbFiles = dbFileRepository.findByType(typeValue);

            if (dbFiles == null)
                return ResponseMessage.getInstance(INVALID_INPUT);

            ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

            return ResponseMessage.getInstance(SUCCESS);
        }catch (Exception e){
            throw new ClassifyMethodNotFound(NOT_ALLOWED);
        }
    }
}