package app.filerepository.export.service.factory;

import app.fileclassification.cach.ClassifyCaching;
import app.model.DBFile;
import app.repository.DBFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static app.filerepository.export.service.enums.ExportType.*;

public class FactoryExport {

    private static final Logger logger = LoggerFactory.getLogger(FactoryExport.class);
    public static List<DBFile>  factory(String value, String name, DBFileRepository dbFileRepository){
        if(name.equals(NAME.name())){
            logger.info("Classify all files that has this Name : "+value);
            if(ClassifyCaching.getClassification().containsKey(name)){
                logger.info("Get files from caching");
                return ClassifyCaching.getClassification().get(name);
            }
            return dbFileRepository.findByName(value);
        }
        else if(name.equals(TYPE.name())){
            logger.info("Classify all files of Type "+value);
            if(ClassifyCaching.getClassification().containsKey(name)){
                logger.info("Get files from caching");
                return ClassifyCaching.getClassification().get(name);
            }
            logger.info("Get file from database");
            return dbFileRepository.findByType(value);
        }
        else if(name.equals(SIZE.name())){
            logger.info("Classify all files of Size "+value);
            if(ClassifyCaching.getClassification().containsKey(name)){
                logger.info("Get files from caching");
                return ClassifyCaching.getClassification().get(name);
            }
            logger.info("Get file from database");
            return dbFileRepository.findBySize(Integer.parseInt(value));
        }
        else if(name.equals(VERSION.name())){
            logger.info("Classify all files of Size "+value);
            return dbFileRepository.findByVersion(value);
        }
        else if(name.equals(DATE.name())){
            logger.info("Classify all files of Date "+value);
            if(ClassifyCaching.getClassification().containsKey(name)){
                logger.info("Get files from caching");
                return ClassifyCaching.getClassification().get(name);
            }
            logger.info("Get file from database");
            return dbFileRepository.findByDate(value);
        }
        return new ArrayList<>();
    }
}

