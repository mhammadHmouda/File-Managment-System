package app.filerepository.services.exportservice.intf;

import app.filerepository.response.ResponseFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface IExportService {
    ResponseFile getFile(String val, String name) throws FileNotFoundException;
    List<ResponseFile> getAllFiles();
}
