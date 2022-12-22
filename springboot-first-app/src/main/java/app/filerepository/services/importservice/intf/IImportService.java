package app.filerepository.services.importservice.intf;

import app.filerepository.response.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImportService {
     ResponseFile store(MultipartFile file) throws IOException;
}
