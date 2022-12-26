package app.filerepository.imports.service.impl;

import app.exception.ImportException;
import app.filerepository.export.service.utils.ExportUtils;
import app.filerepository.imports.encrypt.EncryptionService;
import app.filerepository.imports.service.intf.IImportService;
import app.filerepository.imports.service.utils.ImportUtils;
import app.model.DBFile;
import app.response.ResponseMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static app.filerepository.imports.constant.ImportConstant.INVALID_TYPE;
import static app.filerepository.imports.constant.ImportConstant.OVERWRITE_SUCCESS;


@Service
public class OverwriteService extends IImportService {


    public ResponseMessage save(MultipartFile file) throws Exception {

        try {

            List<DBFile> dbFiles = fileDBRepository.findByName(file.getOriginalFilename());

            int version = ImportUtils.findLatestVersion(dbFiles);
            DBFile dbFile = dbFiles.stream().filter(f -> f.getVersion() == version).collect(Collectors.toList()).get(0);

            fileDBRepository.delete(dbFile);

            String contentType = ExportUtils.getOriginalType(file.getOriginalFilename());

            byte[] data = EncryptionService.encrypt(file.getBytes());

            DBFile newFile = new DBFile(file.getOriginalFilename(), contentType, file.getSize(), version, data, ImportUtils.getCurrentTime());

            fileDBRepository.save(newFile);

            return ResponseMessage.getInstance(OVERWRITE_SUCCESS);

        }catch (Exception e){
            throw new ImportException(INVALID_TYPE);
        }
    }

}
