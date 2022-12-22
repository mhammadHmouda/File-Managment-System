package app.filerepository.services.rollback.utils;

import app.filerepository.model.DBFile;

import java.util.List;
import java.util.stream.Collectors;

public class RollBackUtils {


    public static int getLatestVersion(List<DBFile> dbFiles){
        int latestVersion = 0;
        for (DBFile dbFile : dbFiles) {
            if(dbFile.getVersion() > latestVersion)
                latestVersion = dbFile.getVersion();
        }
        return latestVersion;
    }

    public static List<DBFile> getTheFileWantDelete(List<DBFile> dbFiles, int version){
        return dbFiles.stream()
                .filter(file -> file.getVersion() > version)
                .collect(Collectors.toList());
    }

}
