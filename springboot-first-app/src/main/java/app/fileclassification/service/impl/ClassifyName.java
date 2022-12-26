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
import static app.fileclassification.constant.ImplementType.IMPLEMENTATION_NAME_CLASS;

@Service
@Qualifier(IMPLEMENTATION_NAME_CLASS)
public class ClassifyName extends AClassify<String> {
    @Override
    public ResponseMessage classify(String name, String typeOfClassification) throws ClassifyMethodNotFound {
        try {
            logger.info("Service of name");
            List<DBFile> dbFiles = dbFileRepository.findByName(name);

            if (dbFiles == null)
                return ResponseMessage.getInstance(INVALID_INPUT);

            ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

            return ResponseMessage.getInstance(SUCCESS);
        }catch (Exception e){
            throw new ClassifyMethodNotFound(NOT_ALLOWED);
        }
    }
}
