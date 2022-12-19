package app.filerepository.model;

public class RollBackRequest {

    private String fileName;
    private int latestVersion;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getVersion() {
        return latestVersion;
    }

    public void setVersion(int version) {
        this.latestVersion = version;
    }
}
