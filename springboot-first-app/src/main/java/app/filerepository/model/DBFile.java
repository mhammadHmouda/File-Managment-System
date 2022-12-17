package app.filerepository.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

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


    private int latestVersion;

    @Lob
    private byte[] data;

    public DBFile() { }

    public DBFile(String fileName, String fileType, Long size,int latestVersion, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.size = size;
        this.latestVersion = latestVersion;
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

    public int getLatestVersion() {return latestVersion;}

    public void setLatestVersion(int latestVersion) {this.latestVersion = latestVersion;}
}
