package app.filerepository.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "files3")
public class DBFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    private long size;


    private int version;

    @Lob
    private byte[] data;
    private static final Logger logger = LoggerFactory.getLogger(DBFile.class);
    public DBFile() {
        logger.info("DBFile has been created ");
    }

    public DBFile(String fileName, String fileType, Long size,int version, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.size = size;
        this.version = version;
        logger.info("created "+fileName+" file ");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getSize() {return size;}

    public void setSize(long size) {this.size = size;}

    public int getVersion() {return version;}

    public void setVersion(int version) {this.version = version;}
}
