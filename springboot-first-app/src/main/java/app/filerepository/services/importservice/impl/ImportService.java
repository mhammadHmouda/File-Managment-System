package app.filerepository.services.importservice.impl;

import app.filerepository.model.DBFile;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.exportservice.ExportUtils;
import app.filerepository.services.importservice.intf.IImportService;
import app.filerepository.services.importservice.utils.ImportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImportService extends IImportService {
    private static final Logger logger = LoggerFactory.getLogger(ImportService.class);
    public ResponseMessage save(MultipartFile file) throws IOException {

        List<DBFile> files = fileDBRepository.findByName(file.getOriginalFilename());

        int version = 1;

        if (!files.isEmpty()) {
            version = ImportUtils.findLatestVersion(files) + 1;
            logger.info("New version of "+file.getOriginalFilename()+" file created");
        }

        String contentType = ExportUtils.getOriginalType(file.getOriginalFilename());
        DBFile FileDB = new DBFile(file.getOriginalFilename(), contentType, file.getSize(), version, file.getBytes(), ImportUtils.getCurrentTime());

        DBFile dbFile = fileDBRepository.save(FileDB);

        return ResponseMessage.getInstance("File imported successfully");
    }


}