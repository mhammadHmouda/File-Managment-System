package app.filerepository.services.importservice.intf;

import app.filerepository.repository.DBFileRepository;
import app.filerepository.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public abstract class IImportService {

    @Autowired
    public DBFileRepository fileDBRepository;

    public abstract ResponseMessage save(MultipartFile file) throws IOException;
}