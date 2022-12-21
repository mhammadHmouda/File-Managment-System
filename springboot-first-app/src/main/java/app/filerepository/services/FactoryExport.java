package app.filerepository.services;

import app.filerepository.enums.ClassificationExport;
import app.filerepository.model.DBFile;
import app.filerepository.repository.DBFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FactoryExport {

    private static final Logger logger = LoggerFactory.getLogger(FactoryExport.class);
    public static List<DBFile>  factory(String val, String name, DBFileRepository dbFileRepository){
        if(name.equals(ClassificationExport.FILE_NAME.name())){
            logger.info("Classify all files that has this Name : "+val);
            return dbFileRepository.findByName(val);
        }
        else if(name.equals(ClassificationExport.FILE_TYPE.name())){
            logger.info("Classify all files of Type "+val);
            return dbFileRepository.findByType(val);
        }
        else if(name.equals(ClassificationExport.SIZE.name())){
            logger.info("Classify all files of Size "+val);
            return dbFileRepository.findBySize(val);
        }
        else if(name.equals(ClassificationExport.VERSION.name())){
            logger.info("Classify all files of Size "+val);
            return dbFileRepository.findByVersion(val);
        }
     return new ArrayList<>();
    }
}
