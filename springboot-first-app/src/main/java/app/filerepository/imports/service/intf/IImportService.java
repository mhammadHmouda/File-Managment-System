package app.filerepository.imports.service.intf;

import app.repository.db.DBFileRepository;
import app.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


public abstract class IImportService {

    @Autowired
    public DBFileRepository fileDBRepository;

    public abstract ResponseMessage save(MultipartFile file) throws Exception;
}