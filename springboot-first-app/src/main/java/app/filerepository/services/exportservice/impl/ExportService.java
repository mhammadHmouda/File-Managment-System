package app.filerepository.services.exportservice.impl;

import app.filerepository.model.DBFile;
import app.filerepository.repository.DBFileRepository;
import app.filerepository.response.ResponseFile;
import app.filerepository.services.exportservice.factory.FactoryExport;
import app.filerepository.services.exportservice.intf.IExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExportService implements IExportService {

    @Autowired
    private DBFileRepository fileDBRepository;

    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);

    public ResponseFile getFile(String val, String name) throws FileNotFoundException {
        List<DBFile> dbFiles = FactoryExport.factory(val, name, fileDBRepository);

        if(dbFiles.isEmpty()){
            logger.warn("This Classify don't match any file");
            throw new FileNotFoundException("File Not Found!");
        }

        DBFile dbFile = dbFiles.stream().max(Comparator.comparing(DBFile::getVersion)).get();
        logger.info("File is fetched successfully!!");

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/exportByName/").path(dbFile.getFileName()).toUriString();

        return ResponseFile.getInstance(dbFile.getFileName(), fileDownloadUri, dbFile.getFileType(), dbFile.getSize());
    }

    public List<ResponseFile> getAllFiles() {
        return fileDBRepository.findAll().stream().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/exportByName/").path(dbFile.getFileName()).toUriString();

            return ResponseFile.getInstance(
                    dbFile.getFileName(), fileDownloadUri,
                    dbFile.getFileType(), dbFile.getData().length);

        }).collect(Collectors.toList());
    }
}
