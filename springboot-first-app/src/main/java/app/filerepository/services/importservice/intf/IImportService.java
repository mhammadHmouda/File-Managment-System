package app.filerepository.services.importservice.intf;

import app.filerepository.uploadfile.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImportService {
     UploadFileResponse store(MultipartFile file) throws IOException;
}
