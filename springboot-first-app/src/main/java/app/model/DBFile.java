package app.model;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import static app.model.ConstantTable.*;


@Entity
@Table(name = TABLE_NAME)
public class DBFile {
    @Id
    @GeneratedValue(generator = ID_TYPE)
    @GenericGenerator(name = ID_TYPE, strategy = STRATEGY)
    private String id;

    private String fileName;

    private String fileType;

    private long size;

    private int version;

    private String importDate;

    @Lob
    private byte[] data;
    private static final Logger logger = LoggerFactory.getLogger(DBFile.class);
    public DBFile() {
        logger.info("DBFile has been created ");
    }

    public DBFile(String fileName, String fileType, Long size, int version, byte[] data, String date) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.size = size;
        this.version = version;
        this.importDate = date;
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

    public String getDate() {return importDate;}

    public void setDate(String date) {this.importDate = date;}
}
