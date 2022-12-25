package app.filerepository.services.exportservice.factory;

import app.filerepository.services.classify.cach.ClassifyCaching;
import app.filerepository.services.exportservice.enums.ExportType;
import app.filerepository.model.DBFile;
import app.filerepository.repository.DBFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class FactoryExport {

    private static final Logger logger = LoggerFactory.getLogger(FactoryExport.class);
    public static List<DBFile>  factory(String value, String name, DBFileRepository dbFileRepository){
        if(name.equals(ExportType.FILE_NAME.name())){
            logger.info("Classify all files that has this Name : "+value);
            if(ClassifyCaching.getClassification().containsKey(name)){
                logger.info("Get files from caching");
                return ClassifyCaching.getClassification().get(name);
            }
            return dbFileRepository.findByName(value);
        }
        else if(name.equals(ExportType.FILE_TYPE.name())){
            logger.info("Classify all files of Type "+value);
            if(ClassifyCaching.getClassification().containsKey(name)){
                logger.info("Get files from caching");
                return ClassifyCaching.getClassification().get(name);
            }
            logger.info("Get file from database");
            return dbFileRepository.findByType(value);
        }
        else if(name.equals(ExportType.SIZE.name())){
            logger.info("Classify all files of Size "+value);
            if(ClassifyCaching.getClassification().containsKey(name)){
                logger.info("Get files from caching");
                return ClassifyCaching.getClassification().get(name);
            }
            logger.info("Get file from database");
            return dbFileRepository.findBySize(Integer.parseInt(value));
        }
        else if(name.equals(ExportType.VERSION.name())){
            logger.info("Classify all files of Size "+value);
            return dbFileRepository.findByVersion(value);
        }
        else if(name.equals(ExportType.DATE.name())){
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

