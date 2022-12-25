package app.filerepository.services.classify;

import app.filerepository.model.DBFile;
import app.filerepository.request.ClassifyDateRequest;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.classify.cach.ClassifyCaching;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("local")
public class ClassifyDate extends AClassify<ClassifyDateRequest>{


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