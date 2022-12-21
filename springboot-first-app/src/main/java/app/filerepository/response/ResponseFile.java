package app.filerepository.response;

import app.filerepository.model.DBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
    private static final Logger logger = LoggerFactory.getLogger(ResponseFile.class);
    public ResponseFile(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        logger.info(" response file created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}