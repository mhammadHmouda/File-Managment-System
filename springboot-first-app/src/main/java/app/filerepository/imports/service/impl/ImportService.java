package app.filerepository.imports.service.impl;

import app.exception.FileStorageException;
import app.filerepository.export.service.utils.ExportUtils;
import app.filerepository.imports.encrypt.EncryptionService;
import app.filerepository.imports.service.intf.IImportService;
import app.filerepository.imports.service.utils.ImportUtils;
import app.model.DBFile;
import app.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static app.filerepository.imports.constant.ImportConstant.IMPORT_SUCCESS;

@Service
public class ImportService extends IImportService {

    @Autowired
    private EncryptionService service;
    private static final Logger logger = LoggerFactory.getLogger(ImportService.class);

    public ImportService() throws NoSuchAlgorithmException {
    }

    public ResponseMessage save(MultipartFile file) throws Exception {

        try {

            List<DBFile> files = fileDBRepository.findByName(file.getOriginalFilename());

            int version = 1;

            if (!files.isEmpty()) {
                version = ImportUtils.findLatestVersion(files) + 1;
                logger.info("New version of " + file.getOriginalFilename() + " file created");
            }

            String contentType = ExportUtils.getOriginalType(file.getOriginalFilename());

            byte[] data = EncryptionService.encrypt(file.getBytes());

            DBFile FileDB = new DBFile(file.getOriginalFilename(), contentType, file.getSize(), version, data, ImportUtils.getCurrentTime());

            fileDBRepository.save(FileDB);

            return ResponseMessage.getInstance(IMPORT_SUCCESS);

        }catch (Exception e){
            throw new FileStorageException(e.getMessage());
        }
    }


}