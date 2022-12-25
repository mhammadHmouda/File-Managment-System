package app.filerepository.services.importservice.utils;

import app.filerepository.model.DBFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ImportUtils {

    public static int findLatestVersion(List<DBFile> files) {
        return files.stream()
                .max(Comparator.comparing(DBFile::getVersion))
                .get().getVersion();
    }
    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
