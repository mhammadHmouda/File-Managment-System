package app.filerepository.service;

import app.filerepository.model.DBFile;
import app.filerepository.repository.DBFileRepository;

import java.util.ArrayList;
import java.util.List;

public class FactoryExport {
    public static List<DBFile>  factory(String val, String name, DBFileRepository dbFileRepository){
        if(name.equals("fileName")){
            return dbFileRepository.findByName(val);
        }
        else if(name.equals("fileType")){
            return dbFileRepository.findByType(val);
        }
        else if(name.equals("size")){
            return dbFileRepository.findBySize(val);
        }
        else if(name.equals("latestVersion")){
            return dbFileRepository.findByVersion(val);
        }
     return new ArrayList<>();
    }
}
