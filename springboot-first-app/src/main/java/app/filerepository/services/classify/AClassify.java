package app.filerepository.services.classify;

import app.filerepository.repository.DBFileRepository;
import app.filerepository.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AClassify<T> {
    @Autowired
    DBFileRepository dbFileRepository;
    static final Logger logger = LoggerFactory.getLogger(AClassify.class);

    public abstract ResponseMessage classify(T value, String typeOfClassification);

}