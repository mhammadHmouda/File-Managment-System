package app.filerepository.services.importservice.utils;

import app.filerepository.model.DBFile;

import java.util.Comparator;
import java.util.List;

public class ImportUtils {

    public static int findCorrectVersion(List<DBFile> files) {
        return files.stream()
                .max(Comparator.comparing(DBFile::getVersion))
                .get().getVersion() + 1;
    }
}
