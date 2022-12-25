package app.fileclassification.service.abs;

import app.repository.DBFileRepository;
import app.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AClassify<T> {
    @Autowired
    public DBFileRepository dbFileRepository;

    public static final Logger logger = LoggerFactory.getLogger(AClassify.class);

    public abstract ResponseMessage classify(T value, String typeOfClassification);

}