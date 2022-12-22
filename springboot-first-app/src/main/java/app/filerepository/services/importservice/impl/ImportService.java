package app.filerepository.services.importservice.impl;

import app.filerepository.model.DBFile;
import app.filerepository.response.ResponseFile;
import app.filerepository.services.importservice.intf.IImportService;
import app.filerepository.services.importservice.utils.ImportUtils;
import app.filerepository.repository.DBFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.util.*;

@Service
public class ImportService implements IImportService {

    @Autowired
    private DBFileRepository fileDBRepository;
    private static final Logger logger = LoggerFactory.getLogger(ImportService.class);
    public ResponseFile store(MultipartFile file) throws IOException {

        List<DBFile> files = fileDBRepository.findByName(file.getOriginalFilename());

        int version = 1;

        if (!files.isEmpty()) {
            version = ImportUtils.findCorrectVersion(files);
            logger.info("New version of "+file.getOriginalFilename()+" file created");
        }

        String fileName = file.getOriginalFilename();
        DBFile FileDB = new DBFile(fileName, file.getContentType(), file.getSize(), version, file.getBytes());

        DBFile dbFile = fileDBRepository.save(FileDB);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/exportByName/")
                .path(dbFile.getFileName())
                .toUriString();

        return ResponseFile.getInstance(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }


}
