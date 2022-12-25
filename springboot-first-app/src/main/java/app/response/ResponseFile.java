package app.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseFile {
    private static String name;
    private static String url;
    private static String type;
    private static long size;
    private static final Logger logger = LoggerFactory.getLogger(ResponseFile.class);

    private static ResponseFile responseFile;
    private ResponseFile(String newName, String newUrl, String newType, long newSize) {
        name = newName;
        url = newUrl;
        type = newType;
        size = newSize;
        logger.info(" response file created");
    }

    public static ResponseFile getInstance(String name, String url, String type, long size){
        if(responseFile == null) {
            responseFile = new ResponseFile(name, url, type, size);
            return responseFile;
        }
        setValue(name, url, type, size);
        return responseFile;
    }

    private static void setValue(String name, String url, String type, long size){
        setName(name);
        setUrl(url);
        setType(type);
        setSize(size);
    }

    public String getName() {
        return name;
    }

    private static void setName(String newName) {
        name = newName;
    }

    public String getUrl() {
        return url;
    }

    private static void setUrl(String newUrl) {
        url = newUrl;
    }

    public String getType() {
        return type;
    }

    private static void setType(String newType) {
        type = newType;
    }

    public long getSize() {
        return size;
    }

    private static void setSize(long newSize) {
        size = newSize;
    }
}