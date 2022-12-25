package app.filerepository.services.importservice.impl;
import app.filerepository.model.DBFile;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.exportservice.ExportUtils;
import app.filerepository.services.importservice.intf.IImportService;
import app.filerepository.services.importservice.utils.ImportUtils;
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