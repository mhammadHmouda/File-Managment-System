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
import static app.fileclassification.constant.ImplementType.IMPLEMENTATION_SIZE_CLASS;

@Service
@Qualifier(IMPLEMENTATION_SIZE_CLASS)
public class ClassifySize extends AClassify<Integer> {
    @Override
    public ResponseMessage classify(Integer size, String typeOfClassification) throws ClassifyMethodNotFound {
        try {

            List<DBFile> dbFiles = dbFileRepository.findBySize(size);

            if (dbFiles == null)
                return ResponseMessage.getInstance(INVALID_INPUT);

            ClassifyCaching.getClassification().put(typeOfClassification, dbFiles);

            return ResponseMessage.getInstance(SUCCESS);

        }catch (Exception e){
            throw new ClassifyMethodNotFound(NOT_ALLOWED);
        }
    }
}