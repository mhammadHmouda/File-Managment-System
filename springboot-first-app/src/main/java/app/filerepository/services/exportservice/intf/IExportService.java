package app.filerepository.services.exportservice.intf;

import app.filerepository.response.ResponseFile;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IExportService {
    ResponseEntity<?> getFile(String val, String name);
    List<ResponseFile> getAllFiles();
}
