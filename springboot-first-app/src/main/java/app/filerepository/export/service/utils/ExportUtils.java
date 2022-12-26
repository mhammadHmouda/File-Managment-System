package app.filerepository.export.service.utils;

import java.util.Objects;

public class ExportUtils {
    public static String getOriginalType(String originalFilename) {
        return Objects.requireNonNull(originalFilename)
                .substring(originalFilename.lastIndexOf(".") + 1);
    }

}
