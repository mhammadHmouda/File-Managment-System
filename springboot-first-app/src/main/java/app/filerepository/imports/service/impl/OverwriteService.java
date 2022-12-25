package app.filerepository.imports.service.impl;

import app.filerepository.imports.service.intf.IImportService;
import app.filerepository.imports.service.utils.ImportUtils;
import app.model.DBFile;
import app.response.ResponseMessage;
import app.filerepository.export.service.utils.ExportUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OverwriteService extends IImportService {


    public ResponseMessage save(MultipartFile file) throws IOException {
        List<DBFile> dbFiles = fileDBRepository.findByName(file.getOriginalFilename());

        int version = ImportUtils.findLatestVersion(dbFiles);
        DBFile dbFile = dbFiles.stream().filter(f -> f.getVersion() == version).collect(Collectors.toList()).get(0);

        fileDBRepository.delete(dbFile);

        String contentType = ExportUtils.getOriginalType(file.getOriginalFilename());

        DBFile newFile = new DBFile(file.getOriginalFilename(), contentType, file.getSize(), version, file.getBytes(), ImportUtils.getCurrentTime());
        fileDBRepository.save(newFile);

        return ResponseMessage.getInstance("File overwritten successfully");
    }

}
