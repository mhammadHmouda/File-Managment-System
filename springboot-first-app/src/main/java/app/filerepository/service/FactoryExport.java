package app.filerepository.service;

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
            return dbFileRepository.findByName(val);
        }
        else if(name.equals(ClassificationExport.FILE_TYPE.name())){
            return dbFileRepository.findByType(val);
        }
        else if(name.equals(ClassificationExport.SIZE.name())){
            return dbFileRepository.findBySize(val);
        }
        else if(name.equals(ClassificationExport.VERSION.name())){
            return dbFileRepository.findByVersion(val);
        }
     return new ArrayList<>();
    }
}
